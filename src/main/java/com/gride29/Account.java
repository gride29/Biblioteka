package com.gride29;

import java.util.ArrayList;
import java.util.Scanner;

public class Account {

    int id;
    String username;
    String password;

    public Account(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Account(Account account) {
        this.id = account.id;
        this.username = account.username;
        this.password = account.password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void printUserMenu() {
        System.out.println("" +
                "1. Wypożycz książke \n" +
                "2. Oddaj książke \n" +
                "0. Wróć");
    }

    static void printBooks(ArrayList<Book> books) {
        System.out.println("Wybierz książke: ");

        int id = 1;

        for (Book book : books) {
            System.out.println(id + ". " + book.getBookName() + " " + book.getAuthorName());
            id++;
        }
        System.out.println("0. Wróć");
    }

    void controlAccount() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Book> borrowedBooks = new ArrayList<>();

        while (true) {
            printUserMenu();
            int option = Integer.parseInt(scan.nextLine());
            System.out.println();

            switch (option) {
                case 1:
                    ArrayList<Book> books = DB_Handler.getBookList();

                    if (books.size() == 0) {
                        System.out.println("Nie ma książek do wypożyczenia");
                        System.out.println();
                    } else {
                        printBooks(books);

                        int selectedBook = Integer.parseInt(scan.nextLine());
                        System.out.println();

                        if (selectedBook == 0) {
                            break;
                        }

                        Book currentBook = new Book(books.get(selectedBook-1));
                        System.out.println("Wypożyczono książke: " + currentBook.getBookName());
                        borrowedBooks.add(currentBook);
                    }
                    break;
                case 2:
                    if (borrowedBooks.size() == 0) {
                        System.out.println("Nie wypożyczyłeś żadnych książek");
                        System.out.println();
                        break;
                    }

                    printBooks(borrowedBooks);

                    int selectedBorrowedBook = Integer.parseInt(scan.nextLine());
                    System.out.println();

                    if (selectedBorrowedBook == 0) {
                        break;
                    }

                    int index = 0;
                    Book currentBorrowedBook = new Book(borrowedBooks.get(selectedBorrowedBook - 1));

                    for (Book book : borrowedBooks) {
                        if (book.getBookName().equals(currentBorrowedBook.getBookName())) {
                            index = borrowedBooks.indexOf(book);
                        }
                    }

                    borrowedBooks.remove(index);
                    System.out.println("Oddano książke: " + currentBorrowedBook.getBookName());
                    break;
                case 0:
                    return;
            }
        }
    }
}
