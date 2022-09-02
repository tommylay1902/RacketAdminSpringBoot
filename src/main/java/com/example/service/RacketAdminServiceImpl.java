package com.example.service;


import com.example.entities.RacketAdmin;
import com.example.repos.RacketAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RacketAdminServiceImpl implements RacketAdminService {
    @Autowired
    RacketAdminRepository repo;
    @Override
    public List<RacketAdmin> getAll() {

        //Gather all the entries for department from the database and return as a list
        try{
            List<RacketAdmin> rList = repo.findAll();
            if(!rList.isEmpty())
                return rList;
        }catch(Exception exc){
            System.out.println(exc);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> insert(RacketAdmin rd) {
        //Search if the primary key/dept already exists in the database
        //insert the new entry if the primary key is not taken
        try{
            Optional<RacketAdmin> dept = repo.findById(rd.getId());
            if(dept.isEmpty()) {
                repo.save(rd);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }

    @Override
    public ResponseEntity<String> update(RacketAdmin d, int id) {

        //Find the entry in the database using deptno
        //update the information
        try{
            Optional<RacketAdmin> rd = repo.findById(id);
            rd.get().setId(d.getId());
            rd.get().setPassword(d.getPassword());
            rd.get().setRoles(d.getRoles());
            rd.get().setUsername(d.getUsername());
            repo.save(rd.get());
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
            Optional<RacketAdmin> rd = repo.findById(id);
            if(!rd.isEmpty()) {
                repo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }

        }catch(Exception exc){
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public Optional<RacketAdmin> getRacketAdminById(int id) {

        //Search the entry by id and return it
        //return optional.empty if not exist
        try{
            Optional<RacketAdmin> rd =  repo.findById(id);
            if(!rd.isEmpty())
            {
                return rd;
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
            List<RacketAdmin>rd = repo.findAll();
            if(rd!= null) {
                repo.deleteAll();
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<RacketAdmin> popRAadmin() {
        RacketAdmin ad = new RacketAdmin(01,"root","1234","ADMIN");
        RacketAdmin usr = new RacketAdmin(02,"root","1234","USER");

        this.insert(ad);
        this.insert(usr);

        return this.getAll();
    }


}