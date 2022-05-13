package com.company;

import java.util.Scanner;

public class Menu {
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show tasks.");
        System.out.println("2. Add task.");
        System.out.println("3. Edit task.");
        System.out.println("4. Change date.");
        //System.out.println("5. Edit accomplish.");
        System.out.println("6. Delete task.");
        System.out.println("7. Tasks due date.");

        System.out.print("Choose an option: ");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                Controller.showTasks();
                break;
            case 2:
                Controller.addTask();
                break;
            case 3:
                Controller.editTask();
                break;
            case 4:
                Controller.editDate();
                break;
            //case 5:
                //ToDoController.editAccomplish();
                //break;
            case 6:
                Controller.deleteTask();
                break;
            case 7:
                Controller.pendingTasks();
                break;
            default:
                System.out.println("Invalid option, try again!");
        }
    }

}
