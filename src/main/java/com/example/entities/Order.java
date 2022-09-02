package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Table(name="orders")
@Entity(name="orders")
public class Order {

    @Id
    int id;
    int price;
    String description;

    String racketBrand;
    String stringPattern;
    String recTension;
    String stringType;
    String desiredTension;
    String customerName;
    String customerPhoneNum;

    Date receivedDay;
    Date returnDay;

    @ManyToOne
    @JoinColumn( name = "racket_admin")
    @JsonIgnore
    private RacketAdmin racketAdmin;

    public RacketAdmin getRacketAdmin() {
        return racketAdmin;
    }

    public void setRacketAdmin(RacketAdmin racketAdmin) {
        this.racketAdmin = racketAdmin;
    }

    public Order(int id, int price, String racketBrand, String stringPattern, String recTension, String stringType, String desiredTension, String customerName, String customerPhoneNum, Date receivedDay, Date returnDay, RacketAdmin racketAdmin) {
        this.id = id;
        this.price = price;
        this.racketBrand = racketBrand;
        this.stringPattern = stringPattern;
        this.recTension = recTension;
        this.stringType = stringType;
        this.desiredTension = desiredTension;
        this.customerName = customerName;
        this.customerPhoneNum = customerPhoneNum;
        this.receivedDay = receivedDay;
        this.returnDay = returnDay;
        this.racketAdmin = racketAdmin;
    }

    public Order() {
    }



    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getReceivedDay() {
        return receivedDay;
    }

    public void setReceivedDay(Date receivedDay) {
        this.receivedDay = receivedDay;
    }

    public Date getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(Date returnDay) {
        this.returnDay = returnDay;
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
    public String getStringPattern() {
        return stringPattern;
    }

    public void setStringPattern(String stringPatern) {
        this.stringPattern = stringPatern;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", racketBrand='" + racketBrand + '\'' +
                ", stringPattern='" + stringPattern + '\'' +
                ", recTension='" + recTension + '\'' +
                ", stringType='" + stringType + '\'' +
                ", desiredTension='" + desiredTension + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNum='" + customerPhoneNum + '\'' +
                ", receivedDay=" + receivedDay +
                ", returnDay=" + returnDay +
                ", adminID=" + racketAdmin +
                '}';
    }
}
