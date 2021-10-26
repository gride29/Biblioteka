package com.gride29;

import java.io.Serializable;

public class Book implements Serializable {

    int id;
    private int isbn;
    private String bookName;
    private String authorName;
    private int bookQty;
    private double price;

    public Book(int id, int isbn, String bookName, String authorName, int bookQty, double price) {
        this.id = id;
        this.isbn = isbn;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookQty = bookQty;
        this.price = price;
    }

    public Book(Book book) {
        this.id = book.id;
        this.isbn = book.isbn;
        this.bookName = book.bookName;
        this.authorName = book.authorName;
        this.bookQty = book.bookQty;
        this.price = book.price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getBookQty() {
        return bookQty;
    }

    public void setBookQty(int bookQty) {
        this.bookQty = bookQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Nazwa książki: " + bookName +
                ", Numer ISBN: " + isbn +
                ", Autor: " + authorName +
                ", Ilość książki: " + bookQty +
                ", Cena: " + price + " PLN";

    }
}
