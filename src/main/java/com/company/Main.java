package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome in the ToDo App! What do you want to do?");
        System.out.println("1. Login");
        System.out.println("2. Sign up ");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                if (Authorization.login()) {
                    Menu.menu();
                }
                else System.out.println("Invalid login credentials. Try again!");
                break;
            case 2:
                Authorization.signUp();
                break;
        }
    }
}
