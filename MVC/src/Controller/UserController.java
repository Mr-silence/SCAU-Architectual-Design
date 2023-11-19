package Controller;

import model.t_sys_user;
import model.UserService;
import view.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public static Connection login() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter database username: ");
        String dbUser = scanner.next();
        System.out.print("Enter database password: ");
        String dbPassword = scanner.next();
        return UserServiceImpl.getConnection(dbUser, dbPassword);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user details:");

        t_sys_user newUser = new t_sys_user();

        System.out.print("Enter student number (fNumber): ");
        newUser.setFNumber(scanner.next());

        System.out.print("Enter login name (fLoginName): ");
        newUser.setFLoginName(scanner.next());

        System.out.print("Enter name (fName): ");
        newUser.setFName(scanner.next());

        System.out.print("Enter password (fPassword): ");
        newUser.setFPassword(scanner.next());

        System.out.print("Enter college (fCollege): ");
        newUser.setFCollege(scanner.next());

        System.out.print("Enter major class (fMajorClass): ");
        newUser.setFMajorClass(scanner.next());

        System.out.print("Enter sex (fSex): ");
        newUser.setFSex(scanner.next());

        System.out.print("Enter phone (fPhone): ");
        newUser.setFPhone(scanner.next());

        System.out.print("Enter short phone number (fPhoneShortNum): ");
        newUser.setFPhoneShortNum(scanner.next());

        System.out.print("Enter email (fEmail): ");
        newUser.setFEmail(scanner.next());

        System.out.print("Enter QQ (fQQ): ");
        newUser.setFQQ(scanner.next());

        System.out.print("Enter address (fAddress): ");
        newUser.setFAddress(scanner.next());

        System.out.print("Enter remark (fRemark): ");
        newUser.setFRemark(scanner.next());

        System.out.print("Enter last login date (format: yyyy-MM-dd): ");
        String lastLoginDateInput = scanner.next();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date lastLoginDate = dateFormat.parse(lastLoginDateInput);

            java.sql.Date lastLoginSqlDate = new java.sql.Date(lastLoginDate.getTime());
            newUser.setFLastLoginTime(lastLoginSqlDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Using current date as last login date.");
            newUser.setFLastLoginTime(new java.sql.Date(System.currentTimeMillis()));
        }

        boolean addUserResult = userService.addUser(newUser);
        if (addUserResult) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }

        System.out.println("Enter query condition:");
        String queryCondition = scanner.next();

        try {
            t_sys_user[] queryResult = userService.queryUsers("fName LIKE '%" + queryCondition + "%'");
            for (t_sys_user user : queryResult) {
                System.out.println("User ID: " + user.getFid());
                System.out.println("Name: " + user.getFName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Enter user ID to delete:");
        int userIdToDelete = scanner.nextInt();

        boolean deleteUserResult = userService.deleteUser(userIdToDelete);
        if (deleteUserResult) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
}
