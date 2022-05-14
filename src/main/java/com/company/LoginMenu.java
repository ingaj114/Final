package com.company;

import java.util.Scanner;

public class LoginMenu {
    public static void menu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome in the ToDo App! Choose an option:");
        System.out.println("\t1. Login: ");
        System.out.print("\t2. Sign up: ");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                Authorization.login();
                break;
            case 2:
                Authorization.signUp();
                break;
            default:
                System.out.println("Invalid option! Try again!");
                LoginMenu.menu();
                break;
        }
    }
}
