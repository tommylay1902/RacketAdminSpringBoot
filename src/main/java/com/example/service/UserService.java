package com.example.service;

import com.example.entities.Order;
import com.example.entities.User;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    ResponseEntity<String> insert(User rd);
    ResponseEntity<String> update(User rd, int id);
    ResponseEntity<String> delete(int id);
    Optional<User> getUserById(int id);
    ResponseEntity<String> deleteAll();
    Optional<Order> orderByDay(Date start);
    Optional<Order> orderByWeek(Date start, Date end);
    Optional<Order> orderByMonth(Month month);



}
