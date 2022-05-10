package com.company;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.ChronoLocalDate;
import java.util.Locale;
import java.util.Scanner;
import static com.company.DbConnection.getConnection;
public class ToDoController {

    static Scanner scanner = new Scanner(System.in);
    static PreparedStatement ps;
    static ResultSet rs;

    public static void showTasks() {
        System.out.print("Enter the username: ");
        String username = scanner.nextLine();

        try {
            ps = getConnection().prepareStatement("SELECT id, task, date FROM todolist WHERE username='" + username + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addTask() {
        System.out.print("Enter to do list's task: ");
        String task = scanner.nextLine();
        System.out.print("Your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter date to accomplish (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        try {
            ps = getConnection().prepareStatement("INSERT INTO todolist(username, task, date) VALUES('" + username + "','" + task + "','" + date + "')");
            ps.execute();
            System.out.println("Task added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTask() {
        System.out.print("Delete task by id: ");
        int id = scanner.nextInt();

        try {
            ps = getConnection().prepareStatement("DELETE FROM todolist WHERE id=" + id);
            ps.execute();
            System.out.println("Task deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void editTask() {
        System.out.print("Enter the id to change: ");
        int id = scanner.nextInt();
        System.out.print("Enter a task: ");
        String task = scanner.next();

        try {
            ps = getConnection().prepareStatement("UPDATE todolist SET task='" + task + "'WHERE id=" + id);
            ps.execute();
            System.out.println("Changed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ToDoObject pendingTasks() {
        System.out.print("Enter the username: ");
        String username = scanner.nextLine();

        Date today = Date.valueOf(LocalDate.now());
        try {
            ps = getConnection().prepareStatement("SELECT id, task, date FROM todolist WHERE username='" + username + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate(3);
                String task = rs.getString(2);
                if (date.equals(today)) {
                    System.out.println(task  + " task HAVE TO BE DONE today ");
                } else if (date.after(today)) {
                    System.out.println(task + " task HAVE TO BE DONE within " + date);
                } else System.out.println(task + " task is due date " + date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
