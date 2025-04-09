package com.example.megamart;


public class Event {
    private String name;
    private String status;
    private String date;

    public Event(String name, String status, String date) {
        this.name = name;
        this.status = status;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}
