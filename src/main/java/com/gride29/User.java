package com.gride29;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class User {

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    void startWorking(String username) throws IOException {
        String content = Files.readString(Path.of("G:\\Piąty Semestr\\Podstawy Javy\\Biblioteka\\src\\sql.txt"), StandardCharsets.UTF_8);
        DB_Handler.loadBooks(content);
        ArrayList<Account> users = DB_Handler.getUserList();
        Account currentUser = null;

        for (Account account : users) {
            if (account.username.equals(username)) {
                int index = users.indexOf(account);
                currentUser = new Account(users.get(index));
            }
        }

        currentUser.controlAccount();
    }
}
