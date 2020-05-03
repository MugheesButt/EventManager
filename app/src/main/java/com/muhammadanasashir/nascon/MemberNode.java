package com.muhammadanasashir.nascon;

import java.io.Serializable;

public class MemberNode implements Serializable{

    String name , cnic , phone ;
    boolean lodging , food ;

    public MemberNode() {
        this.name = "default";
        this.cnic = "default";
        this.phone = "default";
        this.lodging = false;
        this.food = false;
    }

    public MemberNode(String name, String cnic, String phone, boolean lodging, boolean food) {
        this.name = name;
        this.cnic = cnic;
        this.phone = phone;
        this.lodging = lodging;
        this.food = food;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLodging() {
        return lodging;
    }

    public void setLodging(boolean lodging) {
        this.lodging = lodging;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }
}
