package com.example.service;


import com.example.entities.Order;
import com.example.entities.User;
import com.example.repos.UserRepository;
import com.example.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository repo;
    @Override
    public List<User> getAll() {

        //Gather all the entries for department from the database and return as a list
        try{
            List<User> rList = repo.findAll();
            if(!rList.isEmpty())
                return rList;
        }catch(Exception exc){
            System.out.println(exc);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> insert(User rd) {
        //Search if the primary key/dept already exists in the database
        //insert the new entry if the primary key is not taken
        try{
            Optional<User> dept = repo.findById(rd.getId());
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
    public ResponseEntity<String> update(User d, int id) {

        //Find the entry in the database using deptno
        //update the information
        try{
            Optional<User> rd = repo.findById(id);
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
            Optional<User> rd = repo.findById(id);
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
    public Optional<User> getUserById(int id) {

        //Search the entry by id and return it
        //return optional.empty if not exist
        try{
            Optional<User> rd =  repo.findById(id);
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
            List<User>rd = repo.findAll();
            if(rd!= null) {
                repo.deleteAll();
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



//    @Override
//    public Optional<Order> orderByDay(Date start) {
//        return repo.ordersByDay(start);
//    }

//    @Override
//    public Optional<Order> orderByWeek(Date start, Date end) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Order> orderByMonth(Month month) {
//        return Optional.empty();
//    }


    @Override
    public UserDetails loadUserByUsername(final String username) {
        final User appUser = repo.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(appUser);
    }
}