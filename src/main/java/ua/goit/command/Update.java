package ua.goit.command;

import ua.goit.model.ApiResponse;
import ua.goit.model.Pet;
import ua.goit.model.PetStatus;
import ua.goit.model.User;
import ua.goit.service.PetService;
import ua.goit.service.UserService;
import ua.goit.view.View;

import java.io.File;

public class Update extends AbstractCommand implements Command {
    private static final String MENU = """
            Please, enter the number according to list below
            1 - to update user
            2 - to update pet
            3 - add photos for pet
            return - go back to main menu
            """;
    private final View view;

    public Update(View view) {
        super(view);
        this.view = view;
    }

    @Override
    public String commandName() {
        return "update";
    }

    @Override
    public void process() {
        boolean running = true;
        while (running) {
            view.write(MENU);
            String section = view.read();
            switch (section) {
                case "1" -> updateUser();
                case "2" -> updatePet();
                case "3" -> addPhotos();
                case "return" -> running = false;
                default -> view.write("Please, enter the correct command\n");
            }
        }
    }

    private void updateUser() {
        view.write("Enter user name you would like to update");
        String userName = view.read();
        UserService.getUserByUserName(userName);
        User user = readUserFromConsole();
        ApiResponse apiResponse = UserService.updateUser(userName, user);
        resultOutput(apiResponse);
    }

    private void updatePet() {
        int id = readIntegerFromConsole("Enter pet id you would like to update");
        Pet petToUpdate = PetService.getPetById(id);
        view.write("Enter pet new name");
        String newName = view.read();
        PetStatus newStatus = readPetStatusFromConsole();
        petToUpdate.setName(newName);
        petToUpdate.setStatus(newStatus);
        ApiResponse apiResponse = PetService.updatePet(id, petToUpdate);
        resultOutput(apiResponse);
    }

    private void addPhotos() {
        int id = readIntegerFromConsole("Enter pet id you would like to update");
        view.write("Enter description to photo");
        String metaData = view.read();
        File image = readFileFromConsole();
        ApiResponse apiResponse = PetService.uploadImage(id, metaData, image);
        resultOutput(apiResponse);
    }
}
