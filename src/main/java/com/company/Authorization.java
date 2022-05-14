package com.company;

import static com.company.DbConnection.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authorization {
    static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean signUp() {
        System.out.print("Sign up! Enter your username: ");
        String username = scanner.next();
        System.out.print("\t     Enter your password: ");
        String password = scanner.next();

        try {
            ps = getConnection().prepareStatement("INSERT INTO users(username, password) " +
                    "VALUES('" + username + "','" + password + "')");
            ps.execute();
            System.out.println("Sign up was successful!");
            Authorization.login();
            return true;
        } catch (SQLException e) {
            System.out.println("Username already exists. Try again!");
            Authorization.signUp();
            return false;
        }
    }


    public static boolean login() {
        System.out.print("Login! Enter your username: ");
        String username = scanner.next();
        System.out.print("\t   Enter your password: ");
        String password = scanner.next();

        try {
            ps = getConnection().prepareStatement("SELECT username, password FROM users WHERE password IN('" + username + "','" + password + "')");
            rs = ps.executeQuery();

            String usernameObj, passwordObj;

            while (rs.next()) {

                usernameObj = rs.getString("username");
                passwordObj = rs.getString("password");

                if (usernameObj.equalsIgnoreCase(username) && passwordObj.equals(password)) {
                    System.out.println("Login successful!");
                    TaskMenu.menu();
                    return true;
                } else if (usernameObj.equals(username) && !passwordObj.equals(password)) {
                    System.out.println("Password does not match the username. Try again!");
                    Authorization.login();
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Invalid login credentials. Try again!");
        Authorization.login();
        return false;
    }

}
