package com.muhammadanasashir.nascon;

public class MainEvent {
    String date , description;

    public MainEvent() {
        this.date = "End Of March";
        this.description = "Yet to be updated";
    }

    public MainEvent(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
