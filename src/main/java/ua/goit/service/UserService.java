package ua.goit.service;



import lombok.SneakyThrows;
import ua.goit.model.ApiResponse;
import ua.goit.model.User;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserService extends HttpService<User>{

    private static final String CREATE_USER = "/user";
    private static final String CREATE_WITH_LIST = "/user/createWithList";
    private static final String READ_USER = "/user/";
    private static final String UPDATE_USER = "/user/";
    private static final String DELETE_USER = "/user/";


    @SneakyThrows
    public static ApiResponse createUser(User user)  {
        HttpRequest request = HttpService.requestWithBody("POST", String.format("%s%s", HOST, CREATE_USER), user);
        HttpResponse<String> response = HttpService.getResponse(request);
        return GSON.fromJson(response.body(), ApiResponse.class);
    }

    @SneakyThrows
    public static ApiResponse createUserArray(List<User> users)  {
        HttpRequest request = HttpService.requestWithBody("POST", String.format("%s%s", HOST, CREATE_WITH_LIST), users);
        HttpResponse<String> response = HttpService.getResponse(request);
        return GSON.fromJson(response.body(), ApiResponse.class);
    }

    @SneakyThrows
    public static User getUserByUserName(String userName) {
        HttpRequest request = HttpService.sendGet(String.format("%s%s%s", HOST, READ_USER, userName));
        HttpResponse<String> response = HttpService.getResponse(request);
        return GSON.fromJson(response.body(), User.class);
    }

    @SneakyThrows
    public static ApiResponse updateUser(String userName, User newUser)  {
        HttpRequest request = HttpService.requestWithBody("PUT", String.format("%s%s%s", HOST, UPDATE_USER,
                userName), newUser);
        HttpResponse<String> response = HttpService.getResponse(request);
        return GSON.fromJson(response.body(), ApiResponse.class);
    }

    @SneakyThrows
    public static ApiResponse delete(String userName)  {
        HttpRequest request = sendDelete(String.format("%s%s%s", HOST, DELETE_USER, userName));
        HttpResponse<String> response = HttpService.getResponse(request);
        return GSON.fromJson(response.body(), ApiResponse.class);
    }
}
