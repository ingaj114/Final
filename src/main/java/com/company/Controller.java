package com.company;

import static com.company.DbConnection.getConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Controller {

    static Scanner scanner = new Scanner(System.in);
    static PreparedStatement ps;
    static ResultSet rs;

    public static void addTask() {
        System.out.print("Enter to do list's task: ");
        String task = scanner.nextLine();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter date to accomplish (yyyy-mm-dd): ");
        String deadline= scanner.nextLine();
        System.out.print("Finished or not (yes/no)? ");
        String accomplish = scanner.nextLine();

        try {
            ps = getConnection().prepareStatement("INSERT INTO todolist(username, task, deadline, acomplishment) VALUES('" + username + "','" + task + "','" + deadline + "','" + accomplish + "')");
            ps.execute();
            System.out.println("Task added successfully");
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
    public static void editDate() {
        System.out.print("Enter the id to change: ");
        int id = scanner.nextInt();
        System.out.print("Enter a date (yyyy-mm-dd): ");
        String task = scanner.next();

        try {
            ps = getConnection().prepareStatement("UPDATE todolist SET deadline='" + task + "'WHERE id=" + id);
            ps.execute();
            System.out.println("Changed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Object pendingTasks() {
        System.out.print("Enter the username: ");
        String username = scanner.nextLine();

        Date today = Date.valueOf(LocalDate.now());
        LocalDate today2 = LocalDate.now();

        try {
            ps = getConnection().prepareStatement("SELECT id, task, deadline, acomplishment FROM todolist WHERE username='" + username + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate(3);
                LocalDate date2 = rs.getDate(3).toLocalDate();
                String task = rs.getString(2);
                Period diff = Period.between(today2, date2);
                if (date.equals(today) && rs.getString(4).equals("f")) {
                    System.out.println("Task " + task.toUpperCase() + " have to be done today.");
                } else if (date.after(today) && rs.getString(4).equals("f")) {
                    System.out.println("Task " + task.toUpperCase() + " have to be done within " + diff.getDays() + " day/-s " + "(" + date +").");
                } else if (date.before(today) && rs.getString(4).equals("f")) {
                    System.out.println("Task " + task.toUpperCase() + " have been passed " + "in " + Math.abs(diff.getDays()) + " day/-s" + " (" + date + ").");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
