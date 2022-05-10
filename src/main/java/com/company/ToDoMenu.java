package com.company;

import java.util.Scanner;

public class ToDoMenu {
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show tasks.");
        System.out.println("2. Add task.");
        System.out.println("3. Edit task.");
        System.out.println("4. Delete task.");
        System.out.println("5. Tasks due date.");

        System.out.print("Choose an option: ");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                ToDoController.showTasks();
                break;
            case 2:
                ToDoController.addTask();
                break;
            case 3:
                ToDoController.editTask();
                break;
            case 4:
                ToDoController.deleteTask();
                break;
            case 5:
                ToDoController.pendingTasks();
                break;
            default:
                System.out.println("Invalid option, try again!");
        }
    }

}
