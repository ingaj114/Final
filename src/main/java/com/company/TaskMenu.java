package com.company;

import java.util.Scanner;

public class TaskMenu {
    public static void menu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("\t1. Add task: ");
        System.out.println("\t2. Edit task by id: ");
        System.out.println("\t3. Delete task by id: ");
        System.out.println("\t4. Edit or add date: ");
        System.out.println("\t5. Show tasks that are pending or passed: ");
        System.out.print("What do you want to do? ");
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
                Controller.editDate();
                break;
            case 5:
                Controller.pendingTasks();
                break;
            default:
                System.out.println("Invalid option, try again!");
        }
    }

}
