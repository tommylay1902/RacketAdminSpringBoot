package com.example.controller;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class OrderController {

    @Autowired
    OrderService service;


    @GetMapping("/show")
    @ResponseBody
    public List<Order> getRacketAdmin()
    {
        return service.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> insert(@RequestBody Order or)
    {
        //Check if there is information pass in from the post request
        //if yes, call insert service to do the insertion
        try{
            if(or != null) {
                return service.insert(or);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> update(@RequestBody Order or, @PathVariable int id)
    {
        //Check if there is information pass in from the post request
        //if yes, call update service to update the information
        try{
            if(or != null)
                return service.update(or, id);
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Optional<Order> getById(@PathVariable int id)
    {
        return service.getOrderById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable int id)
    {
        return service.delete(id);
    }

    @DeleteMapping("/deleteAll")
    @ResponseBody
    public ResponseEntity<String> deleteAll()
    {
        return service.deleteAll();
    }
}
