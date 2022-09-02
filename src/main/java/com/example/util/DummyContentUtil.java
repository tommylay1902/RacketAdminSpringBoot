package com.example.util;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import com.example.repos.OrderRepository;
import com.example.repos.RacketAdminRepository;
import com.example.service.OrderService;
import com.example.service.RacketAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;

@Component
public class DummyContentUtil {

    @Autowired
    private RacketAdminService raService;

    @Autowired
    private OrderService oService;

    @PostConstruct
    private void postConstruct() {
        RacketAdmin ad = new RacketAdmin(01,"rootAdmin","1234","ADMIN");
        RacketAdmin usr = new RacketAdmin(02,"rootUser","1234","USER");

        raService.insert(ad);
        raService.insert(usr);


        Date d = new Date(2022-8-22);
        Date d2 = new Date(2022-8-29);
        //y m d
        Order a = new Order(11,20,"Babolat", "16x19",
                "50-60","Natural Gut","60",
                "Tommy LAYY", "1234567890",d,d2,ad);

        Date d1 = new Date(2022-9-13);
        //y m d
        Order b = new Order(22,30,"Diadem", "16x20",
                "45-55","Multifilament","45",
                "Tam NUGGET","1323211111",d1,d2,ad);

        Date d3 = new Date(2022-4-20);
        //y m d
        Order c = new Order(33,40,"Wilson", "18x20",
                "50-60","Polyester","55",
                "Chuang Huang", "8971321222",d2,d3,ad);

        oService.insert(a);
        oService.insert(b);
        oService.insert(c);
    }
}
