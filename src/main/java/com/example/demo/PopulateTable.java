package com.example.demo;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;

import java.sql.Date;

public class PopulateTable {

    public static void main(String[] args) {
        RacketAdmin ad = new RacketAdmin(01,"root","1234","ADMIN");
        RacketAdmin usr = new RacketAdmin(02,"root","1234","USER");

        Date d = new Date(2022-8-22);
        //y m d
        Order a = new Order(11,"Maserati", "Squares",
                "Low","Nylon","Medium",
                "6216662121", "Tommy LAYY",d,ad.getId());

        Date d1 = new Date(2022-9-13);
        //y m d
        Order b = new Order(22,"Honda", "Rectangle",
                "Medium","Nylon","High",
                "1323211111", "Tam NUGGET",d1,ad.getId());

        Date d2 = new Date(2022-4-20);
        //y m d
        Order c = new Order(22,"Leux", "Squares",
                "Low","Nylon","High",
                "8971321222", "Chaung JUAN",d2,ad.getId());
    }



}
