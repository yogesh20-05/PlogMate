package com.example.megamart;

public class Book {

    private String author;
    private String title;
    private String notes;

    // Constructor
    public Book(String author, String title) {
        this.author = author;
        this.title = title;
        this.notes = notes;
    }

    // Getters and Setters for author, title, and notes

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}