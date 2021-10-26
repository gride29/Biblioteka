package com.gride29;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DB_Handler {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:./src/db/library";

    static void createTables() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USERS (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "USERNAME VARCHAR NOT NULL," +
                    "PASSWORD VARCHAR NOT NULL" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS BOOKS (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "ISBN INT NOT NULL," +
                    "BOOKNAME VARCHAR NOT NULL," +
                    "AUTHORNAME VARCHAR NOT NULL," +
                    "BOOKQTY INT NOT NULL," +
                    "PRICE DOUBLE NOT NULL" +
                    ");";

            stmt.executeUpdate(sql);
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();
        } catch(Exception se) {
            se.printStackTrace();
        }
        finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println();
    }

    static void register(String username, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {
                connection.setAutoCommit(true);

                try (Statement statement = connection.createStatement()) {
                    String sql = "INSERT INTO USERS(USERNAME, PASSWORD) VALUES('" + username + "','" + password + "');";
                    statement.executeUpdate(sql);
                    System.out.println("Użytkownik został zarejestrowany!");
                    System.out.println();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean login(String username, String password) {
        boolean isLoginCorrect = false;

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)){
                connection.setAutoCommit(true);

                try (Statement statement = connection.createStatement()){
                    String countQuery = "SELECT COUNT(*) FROM USERS u WHERE u.USERNAME = '" + username + "' AND u.PASSWORD = '" + password + "'";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    if (count > 0) {
                        isLoginCorrect = true;
                    } else {
                        isLoginCorrect = false;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return isLoginCorrect;
    }

    static void loadBooks(String sql) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)) {
                connection.setAutoCommit(true);

                try (Statement statement = connection.createStatement()) {
                    String deleteStatement = "DELETE FROM Books;";
                    statement.executeUpdate(deleteStatement);
                    String insertStatement = sql;
                    statement.executeUpdate(insertStatement);
                    System.out.println();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Book> getBookList() {
        ArrayList<Book> bookList = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)){
                connection.setAutoCommit(true);

                try (Statement statement = connection.createStatement()){
                    String countQuery = "SELECT COUNT(*) FROM BOOKS";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    // If there are companies in database, add them to list
                    if (count > 0) {
                        String listQuery = "SELECT * FROM BOOKS";
                        res = statement.executeQuery(listQuery);

                        while (res.next()) {
                            Book book = new Book(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getInt(5), res.getDouble(6));
                            bookList.add(book);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    static ArrayList<Account> getUserList() {
        ArrayList<Account> userList = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(DB_URL)){
                connection.setAutoCommit(true);

                try (Statement statement = connection.createStatement()){
                    String countQuery = "SELECT COUNT(*) FROM USERS";
                    var res = statement.executeQuery(countQuery);
                    int count = -1;

                    while (res.next()) {
                        count = res.getInt(1);
                    }

                    // If there are companies in database, add them to list
                    if (count > 0) {
                        String listQuery = "SELECT * FROM USERS";
                        res = statement.executeQuery(listQuery);

                        while (res.next()) {
                            Account account = new Account(res.getInt(1), res.getString(2), res.getString(3));
                            userList.add(account);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}