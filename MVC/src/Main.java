import Controller.UserController;
import model.UserService;
import view.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = UserController.login()) {
            UserService userService = new UserServiceImpl(connection);

            UserController userController = new UserController(userService);

            userController.run();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


