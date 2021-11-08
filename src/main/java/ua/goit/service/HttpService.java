package ua.goit.service;


import com.google.gson.Gson;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import ua.goit.model.ApiResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpService <E>{
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final CloseableHttpClient DEFAULT_CLIENT = HttpClients.createDefault();
    private static final ResponseHandler RESPONSE_HANDLER = new ResponseHandler();

    protected static final Gson GSON = new Gson();

    protected static final String HOST = "https://petstore.swagger.io/v2";

    protected static HttpRequest sendGet(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    protected static HttpRequest sendDelete(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
    }

    protected static <Е> HttpRequest requestWithBody(String methodName, String url, Е entity) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-type", "application/json")
                .method(methodName, HttpRequest.BodyPublishers.ofString(GSON.toJson(entity)))
                .build();
    }

    protected static HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    protected static ApiResponse sendMultipartEntity(String url, HttpEntity entity) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        String execute = DEFAULT_CLIENT.execute(post, RESPONSE_HANDLER);
        return GSON.fromJson(execute, ApiResponse.class);
    }

    protected static ApiResponse sendPostEncoded(String url, List<NameValuePair> form) throws IOException {
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
        HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        String execute = DEFAULT_CLIENT.execute(post, RESPONSE_HANDLER);
        return GSON.fromJson(execute, ApiResponse.class);
    }
}
