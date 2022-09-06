package com.example.util;

import com.example.entities.Order;
import com.example.entities.User;
import com.example.service.OrderService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class DummyContentUtil {

    @Autowired
    private UserService raService;

    @Autowired
    private OrderService oService;

    @PostConstruct
    private void postConstruct() {
        User ad = new User(01,"rootManager","1234","MANAGER");
        User usr = new User(02,"rootEmployee","1234","EMPLOYEE");
        User usr2 = new User(03,"rootEmployee2","1234","EMPLOYEE");

        raService.insert(ad);
        raService.insert(usr);
        raService.insert(usr2);


        LocalDate localDate = LocalDate.of(2014, 9, 11);
        Date d =  Date.valueOf(localDate);


        LocalDate localDate2 = LocalDate.of(2022, 8, 29);
        Date d2 = Date.valueOf(localDate2);
        //y m d
        Order a = new Order(11,20,"Restring racket","Babolat", "16x19",
                "50-60","Natural Gut","60",
                "Tommy LAYY", "1234567890", true, d,d2,usr);


        Date d1 = Date.valueOf(LocalDate.of(2022,9,13));
        //y m d
        Order b = new Order(22,30,"Restring racket","Diadem", "16x20",
                "45-55","Multifilament","45",
                "Tam NUGGET","1323211111",true, d1,d2,usr);

        Date d3 = Date.valueOf(LocalDate.of(2022,4,20));
        //y m d
        Order c = new Order(33,40,"Restring racket","Wilson", "18x20",
                "50-60","Polyester","55",
                "Chuang Huang", "8971321222",false, d2,d3,usr);


        //y m d
        Order aa = new Order(44,18,"Restring racket","Babolat", "16x19",
                "50-60","Natural Gut","60",
                "Tommy LAYY", "1234567890", true, d,d2,usr2);


        //y m d
        Order bb = new Order(55,12,"Restring racket","Diadem", "16x20",
                "45-55","Multifilament","45",
                "Tam NUGGET","1323211111",true, d1,d2,usr2);


        //y m d
        Order cc = new Order(66,25,"Restring racket","Wilson", "18x20",
                "50-60","Polyester","55",
                "Chuang Huang", "8971321222",false, d2,d3,usr2);


        oService.insert(a);
        oService.insert(b);
        oService.insert(c);
        oService.insert(aa);
        oService.insert(bb);
        oService.insert(cc);
    }
}
