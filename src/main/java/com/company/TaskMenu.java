package com.company;

import java.util.Scanner;

public class TaskMenu {
    public static void menu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do? ");
        System.out.println("\t1. Add task: ");
        System.out.println("\t2. Edit task by id: ");
        System.out.println("\t3. Delete task by id: ");
        System.out.println("\t4. Get task by id: ");
        System.out.println("\t5. Tasks that are pending or passed without fulfilment (by username):");
        System.out.println("\t6. Tasks that are pending (all of them by list):");
        System.out.println("\t7. Log off.");

        int option = sc.nextInt();

        switch (option) {
            case 1:
                Controller.addTask();
                System.out.println();
                System.out.println("Do you want to do something else?");
                TaskMenu.menu();
                break;
            case 2:
                Controller.editTask();
                System.out.println();
                System.out.println("Do you want to do something else?");
                TaskMenu.menu();
                break;
            case 3:
                Controller.deleteTask();
                System.out.println();
                System.out.println("Do you want to do something else?");
                TaskMenu.menu();
                break;
            case 4:
                System.out.println(Controller.getTask());
                System.out.println();
                System.out.println("Do you want to do something else?");
                TaskMenu.menu();
                break;
            case 5:
                Controller.pendingTasks();
                System.out.println();
                System.out.println("Do you want to do something else?");
                TaskMenu.menu();
                break;
            case 6:
                Controller.printPendingTasks(Controller.getPendingTasks());
                System.out.println();
                System.out.println("Do you want to do something else?");
                TaskMenu.menu();
                break;
            case 7:
                return;
            default:
                System.out.println("Invalid option, try again!");
                TaskMenu.menu();
        }
    }

}
