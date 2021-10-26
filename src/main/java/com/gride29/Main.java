package com.gride29;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static void printMenu() {
        System.out.println("1. Zaloguj się\n" +
                "2. Zarejestruj się\n" +
                "0. Wyjdź"
        );
    }

    public static void main(String[] args) throws IOException {
        Scanner scan =  new Scanner(System.in);
        DB_Handler.createTables();

        while (true) {
            printMenu();
            int option = Integer.parseInt(scan.nextLine());
            System.out.println();

            switch (option) {
                case 1:
                    System.out.println("Wprowadź nazwę użytkownika: ");
                    String loginUsername = scan.nextLine();
                    System.out.println();
                    System.out.println("Wprowadź hasło: ");
                    String loginPassword = scan.nextLine();
                    if (DB_Handler.login(loginUsername, loginPassword)) {
                        System.out.println("Zalogowano pomyślnie");
                        new User().startWorking(loginUsername);
                        break;
                    } else {
                        System.out.println("Niepoprawny login lub hasło");
                    }
                    break;
                case 2:
                    System.out.println("Wprowadź nazwę użytkownika: ");
                    String registerUsername = scan.nextLine();
                    System.out.println();
                    System.out.println("Wprowadź hasło: ");
                    String registerPassword = scan.nextLine();
                    DB_Handler.register(registerUsername, registerPassword);
                    System.out.println("Zarejestrowano pomyślnie");
                    break;
                case 0:
                    return;
            }
        }
    }
}
