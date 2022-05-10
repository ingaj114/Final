package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose an option: ");
        System.out.println("\t1. Login");
        System.out.print("\t2. Sign up ");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                if (ToDoAuth.login()) {
                    ToDoMenu.menu();
                }
                else System.out.println("Invalid login credentials. Try again!");
                break;
            case 2:
                ToDoAuth.signUp();
                break;
        }
//        ToDoMenu.menu();
    }
}
