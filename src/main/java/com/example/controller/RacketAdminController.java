package com.example.controller;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import com.example.service.RacketAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/racketadmin")
public class RacketAdminController {

    @Autowired
    RacketAdminService service;


    @GetMapping("/show")
    @ResponseBody
    public List<RacketAdmin> getRacketAdmin()
    {
        return service.getAll();
    }

    /**
     * @author Chuang Huang
     * Post mehthod for url baseUrl/api/order/add
     * @param rd represents the ReqeustBody Object of RacketAdmin that will be passed in
     * @return ResponseEntity that returns the response status
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> insert(@RequestBody RacketAdmin rd)
    {
        //Check if there is information pass in from the post request
        //if yes, call insert service to do the insertion
        try{
            if(rd != null) {
                return service.insert(rd);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> update(@RequestBody RacketAdmin rd, @PathVariable int id)
    {
        //Check if there is information pass in from the post request
        //if yes, call update service to update the information
        try{
            if(rd != null)
                return service.update(rd, id);
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Optional<RacketAdmin> getById(@PathVariable int id)
    {
        return service.getRacketAdminById(id);
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

    @GetMapping("/showByDay/{start}/{end}")
    @ResponseBody
    public int orderByDay(@PathVariable Date start, @PathVariable Date end)
    {
        return service.orderByDay(start, end);
    }


}

