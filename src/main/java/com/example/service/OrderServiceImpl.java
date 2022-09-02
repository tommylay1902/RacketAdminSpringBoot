package com.example.service;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import com.example.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository repo;
    @Override
    public List<Order> getAll() {

        //Gather all the entries for department from the database and return as a list
        try{
            List<Order> rList = repo.findAll();
            if(!rList.isEmpty())
                return rList;
        }catch(Exception exc){
            System.out.println(exc);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> insert(Order o) {
        //Search if the primary key/dept already exists in the database
        //insert the new entry if the primary key is not taken
        try{
            Optional<Order> order = repo.findById(o.getId());
            if(order.isEmpty()) {
                repo.save(o);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }

    @Override
    public ResponseEntity<String> update(Order o, int id) {

        //Find the entry in the database using deptno
        //update the information
        try{
            Optional<Order> order = repo.findById(id);
            order.get().setId(o.getId());
            order.get().setCustomerName(o.getCustomerName());
            order.get().setAdminID(o.getAdminID());
            order.get().setCustomerPhoneNum(o.getCustomerPhoneNum());
            order.get().setDesiredTension(o.getDesiredTension());
            order.get().setReturnDay(o.getReturnDay());
            order.get().setPrice(o.getPrice());
            order.get().setReceivedDay(o.getReceivedDay());
            order.get().setRacketBrand(o.getRacketBrand());
            order.get().setRecTension(o.getRecTension());
            order.get().setStringPattern(o.getStringPattern());
            order.get().setStringType(o.getStringType());
            repo.save(order.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception exc )
        {
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        //Search if the entry existed in the database
        //If so, delete it
        try{
            Optional<Order> order = repo.findById(id);
            if(!order.isEmpty()) {
                repo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }

        }catch(Exception exc){
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public Optional<Order> getOrderById(int id) {

        //Search the entry by id and return it
        //return optional.empty if not exist
        try{
            Optional<Order> order =  repo.findById(id);
            if(!order.isEmpty())
            {
                return order;
            }
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        return Optional.empty();
    }

    @Override
    public ResponseEntity<String> deleteAll() {

        //Check if the database is empty
        //if now, delete all the entries in the table
        try{
            List<Order>order = repo.findAll();
            if(order!= null) {
                repo.deleteAll();
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @Override
    public List<Order> popOrder() {
        RacketAdmin ad = new RacketAdmin(01,"root","1234","ADMIN");
        RacketAdmin usr = new RacketAdmin(02,"root","1234","USER");

        Date d = new Date(2022-8-22);
        Date d2 = new Date(2022-8-29);
        //y m d
        Order a = new Order(11,20,"Maserati", "Squares",
                "Low","Nylon","Medium",
                "6216662121", "Tommy LAYY",d,d2,ad.getId());

        Date d1 = new Date(2022-9-13);
        //y m d
        Order b = new Order(22,30,"Honda", "Rectangle",
                "Medium","Nylon","High",
                "1323211111", "Tam NUGGET",d1,d2,ad.getId());

        Date d3 = new Date(2022-4-20);
        //y m d
        Order c = new Order(33,40,"Leux", "Squares",
                "Low","Nylon","High",
                "8971321222", "Chaung JUAN",d2,d3,ad.getId());

        this.insert(a);
        this.insert(b);
        this.insert(c);

        return this.getAll();
    }
}


