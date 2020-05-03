package com.muhammadanasashir.nascon;

public class EventNode {

    String name , details , category , date , start_time , end_time ;
    int fee , first , second, min_members , max_members  ;

    public EventNode() {
        this.name = "default";
        this.details = "default";
        this.category = "default";
        this.date = "default";
        this.start_time = "default";
        this.end_time = "default";
        this.fee = 0;
        this.first = 0;
        this.second = 0;
        this.min_members = 0;
        this.max_members = 0;
    }

    public EventNode(String name, String details, String category, String date, String start_time, String end_time, int fee, int first, int second, int min_members, int max_members) {
        this.name = name;
        this.details = details;
        this.category = category;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.fee = fee;
        this.first = first;
        this.second = second;
        this.min_members = min_members;
        this.max_members = max_members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMin_members() {
        return min_members;
    }

    public void setMin_members(int min_members) {
        this.min_members = min_members;
    }

    public int getMax_members() {
        return max_members;
    }

    public void setMax_members(int max_members) {
        this.max_members = max_members;
    }
}
