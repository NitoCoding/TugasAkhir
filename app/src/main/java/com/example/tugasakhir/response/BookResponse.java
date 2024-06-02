package com.example.tugasakhir.response;

import com.example.tugasakhir.models.Book;

import java.util.List;

public class BookResponse {
    private int status;
    private String message;
    private List<Book> books;

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
