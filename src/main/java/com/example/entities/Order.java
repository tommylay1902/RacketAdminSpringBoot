package com.example.entities;

import java.sql.Date;

public class Order {

    int id;

    String racketBrand;
    String stringPatern;
    String recTension;
    String stringType;
    String desiredTension;
    String customerName;
    String customerPhoneNum;

    Date Duedate;

    int adminID;



    public Order() {
    }

    public Order(int id, String racketBrand, String stringPatern, String recTension, String stringType,
                 String desiredTension, String customerName, String customerPhoneNum, Date duedate,
                 int adminID) {
        this.id = id;
        this.racketBrand = racketBrand;
        this.stringPatern = stringPatern;
        this.recTension = recTension;
        this.stringType = stringType;
        this.desiredTension = desiredTension;
        this.customerName = customerName;
        this.customerPhoneNum = customerPhoneNum;
        Duedate = duedate;
        this.adminID = adminID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRacketBrand() {
        return racketBrand;
    }

    public void setRacketBrand(String racketBrand) {
        this.racketBrand = racketBrand;
    }

    public String getRecTension() {
        return recTension;
    }

    public void setRecTension(String recTension) {
        this.recTension = recTension;
    }

    public String getStringType() {
        return stringType;
    }

    public void setStringType(String stringType) {
        this.stringType = stringType;
    }

    public String getDesiredTension() {
        return desiredTension;
    }

    public void setDesiredTension(String desiredTension) {
        this.desiredTension = desiredTension;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNum() {
        return customerPhoneNum;
    }

    public void setCustomerPhoneNum(String customerPhoneNum) {
        this.customerPhoneNum = customerPhoneNum;
    }

    public Date getDuedate() {
        return Duedate;
    }

    public void setDuedate(Date duedate) {
        Duedate = duedate;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }


    public String getStringPatern() {
        return stringPatern;
    }

    public void setStringPatern(String stringPatern) {
        this.stringPatern = stringPatern;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", racketBrand='" + racketBrand + '\'' +
                ", stringPatern='" + stringPatern + '\'' +
                ", recTension='" + recTension + '\'' +
                ", stringType='" + stringType + '\'' +
                ", desiredTension='" + desiredTension + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNum='" + customerPhoneNum + '\'' +
                ", Duedate=" + Duedate +
                ", adminID=" + adminID +
                '}';
    }
}
