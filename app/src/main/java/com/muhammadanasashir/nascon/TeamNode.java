package com.muhammadanasashir.nascon;

import java.io.Serializable;

public class TeamNode implements Serializable {

    String ename , institute , tname ;
    int members , fee;

    public TeamNode() {

        this.ename = "default";
        this.institute = "default";
        this.members = 0;
        this.tname = "default";
        this.fee = 0;
    }

    public TeamNode(String ename, String institute, int members, String tname, int fee) {

        this.ename = ename;
        this.institute = institute;
        this.members = members;
        this.tname = tname;
        this.fee = fee ;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

}
