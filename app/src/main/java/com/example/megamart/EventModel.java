package com.example.megamart;

import com.google.gson.annotations.SerializedName;

public class EventModel {

    @SerializedName("event_id") // Match JSON key names
    private int eventId;

    @SerializedName("name")
    private String name;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;

    @SerializedName("address")
    private String address;

    // Constructor
    public EventModel(int eventId, String name, String date, String time, String address) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.address = address;
    }

    // Getters
    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getDate()      {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }
}