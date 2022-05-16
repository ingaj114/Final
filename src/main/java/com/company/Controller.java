package com.company;

import static com.company.DbConnection.getConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    static Scanner scanner = new Scanner(System.in);
    static PreparedStatement ps;
    static ResultSet rs;
    static String username = Authorization.username;

    public static void addTask() {
        System.out.print("Enter to do list's task: ");
        String task = scanner.nextLine();
        System.out.print("Enter date to accomplish (yyyy-mm-dd): ");
        String deadline = scanner.nextLine();
        System.out.print("Finished or not finished (yes/no) ? ");
        String accomplish = scanner.nextLine();

        try {
            ps = getConnection().prepareStatement("INSERT INTO todolist(username, task, deadline, acomplishment) VALUES('" + username + "','" + task + "','" + deadline + "','" + accomplish + "')");
            ps.execute();
            System.out.println("Task added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean editTask() {
        System.out.println("Enter the id of the task that you want to edit: ");
        int id = scanner.nextInt();
        System.out.print("Enter what you want to update in the table: ");
        System.out.println("\n\t1. Task name: \n" + "\t2. Task deadline: \n" + "\t3. Task accomplishment status: \n" + "\t4. All of the above:");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.print("Enter the new name of the task: ");
                String task = scanner.next(); // doesn't work with .nextLine()
                try {
                    ps = getConnection().prepareStatement("UPDATE todolist SET task='" + task + "' WHERE id=" + id);
                    ps.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.print("Enter the deadline for a task (format: YYYY-MM-DD): ");
                String taskDate = scanner.next();
                try {
                    ps = getConnection().prepareStatement("UPDATE todolist SET deadline=DATE '"+ taskDate + "' WHERE id=" + id);
                    System.out.println("Deadline updated");
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("Task deadline not updated.");
                }
                break;
            case 3:
                System.out.print("Enter the task accomplishment status (yes/no): ");
                String taskStatus = scanner.next();
                try {
                    ps = getConnection().prepareStatement("UPDATE todolist SET acomplishment='" + taskStatus + "' WHERE id=" + id);
                    ps.execute();
                    System.out.println("Status updated");
                } catch (SQLException e) {
                    System.out.println("Task status not updated.");
                }
                break;
            case 4:
                System.out.print("Enter the new name of the task: ");
                String taskAll = scanner.next();
                System.out.print("Enter the deadline for a task (format: YYYY-MM-DD): ");
                String taskDateAll = scanner.next();
                System.out.print("Enter the task accomplishment status (yes/no): ");
                String taskStatusAll = scanner.next();
                try {
                    ps = getConnection().prepareStatement("UPDATE todolist SET task='" + taskAll + "', deadline=DATE '"+ taskDateAll + "', acomplishment='" + taskStatusAll + "' WHERE id=" + id);
                    ps.execute();
                    System.out.println("Successfully updated");
                } catch (SQLException e) {
                    System.out.println("The task data is not updated.");
                }
                break;
            default:
                System.out.println("Invalid option. Try again");
                editTask();
        }
        return true;
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

    public static TaskObject getTask() {
        System.out.print("Enter task's id: ");
        int id = scanner.nextInt();

        try {
            ps = getConnection().prepareStatement("SELECT * FROM todolist WHERE id =" + id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new TaskObject(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("task"),
                        rs.getDate("deadline"),
                        rs.getBoolean("acomplishment"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TaskObject pendingTasks() {

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
                    System.out.println("Task " + task.toUpperCase() + " has to be done today.");
                } else if (date.after(today) && rs.getString(4).equals("f")) {
                    System.out.println("Task " + task.toUpperCase() + "has to be done within " + diff.getDays() + " day/-s " + "(" + date +").");
                } else if (date.before(today) && rs.getString(4).equals("f")) {
                    System.out.println("Task " + task.toUpperCase() + " passed " + Math.abs(diff.getDays()) + " day/-s ago" + " (" + date + ").");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<TaskObject> getPendingTasks() {

        try {
            ps = getConnection().prepareStatement("SELECT * FROM todolist WHERE deadline >= NOW() AND acomplishment is false;");
            rs = ps.executeQuery();

            List<TaskObject> pendingTasks = new ArrayList<>();

            while (rs.next()) {
                pendingTasks.add(new TaskObject(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("task"),
                        rs.getDate("deadline"),
                        rs.getBoolean("acomplishment")));
            }
            return pendingTasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void printPendingTasks(List<TaskObject> tasks) {
        System.out.println("Pending tasks: ");
        for (TaskObject task : tasks) {
            System.out.printf("Task ID = %s, username = %s, taskname = %s, deadline = %s, acomplishment = %s\n",
                    task.getId(), task.getUsername(), task.getTask(), task.getDeadline(), task.isAcomplishment());
        }
    }

}
