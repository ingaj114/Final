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
        System.out.print("\t\t Enter your password: ");
        String password = scanner.next();

        try {
            ps = getConnection().prepareStatement("INSERT INTO users(username, password) " +
                    "VALUES('" + username + "','" + password + "')");
            ps.execute();
            System.out.println("Sign up was successful!");
            return true;
        } catch (SQLException e) {
            System.out.println("Username already exists. Try again!");
            e.getMessage();
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

            String urn = null, psw = null;

            while (rs.next()) {

                urn = rs.getString("username");
                psw = rs.getString("password");

                if (urn.equalsIgnoreCase(username) && psw.equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return false;
    }


}
