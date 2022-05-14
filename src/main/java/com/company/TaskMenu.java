package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class TaskMenu {
    public static void menu() throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do? ");
        System.out.println("\t1. Add task: ");
        System.out.println("\t2. Edit task by id: ");
        System.out.println("\t3. Delete task by id: ");
        System.out.println("\t4. Get task by id: ");
        System.out.println("\t5. Tasks that are pending or passed without fulfilment (by username):");
        System.out.println("\t6. Tasks that are pending or passed without fulfilment (all of them by list):");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                Controller.addTask();
                break;
            case 2:
                Controller.editTask();
                break;
            case 3:
                Controller.deleteTask();
                break;
            case 4:
                System.out.println(Controller.getTask());
                break;
            case 5:
                Controller.pendingTasks();
                break;
            case 6:
                Controller.printPendingTasks(Controller.getPendingTasks());
                break;
            default:
                System.out.println("Invalid option, try again!");
        }
    }

}
