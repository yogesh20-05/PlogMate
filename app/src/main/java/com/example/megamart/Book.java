package com.example.megamart;


public class Book {
    private String name;
    private boolean isBookmarked;

    public Book(String name, boolean isBookmarked) {
        this.name = name;
        this.isBookmarked = isBookmarked;
    }

    public String getName() { return name; }
    public boolean isBookmarked() { return isBookmarked; }
    public void setBookmarked(boolean bookmarked) { isBookmarked = bookmarked; }
}
