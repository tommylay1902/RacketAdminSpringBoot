package com.example.service;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAll();
    ResponseEntity<String> insert(Order rd);
    ResponseEntity<String> update(Order r, int id);
    ResponseEntity<String> delete(int id);
    Optional<Order> getOrderById(int id);
    ResponseEntity<String> deleteAll();
}
