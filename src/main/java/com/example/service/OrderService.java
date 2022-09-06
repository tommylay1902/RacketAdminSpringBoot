package com.example.service;

import com.example.entities.Order;
import com.example.entities.views.EmployeeReport;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    ResponseEntity<String> insert(Order rd);
    ResponseEntity<String> update(Order r, int id);
    ResponseEntity<String> delete(int id);

    ResponseEntity<String> deleteAll();

    Optional<List<Order>> receivedOrdersByDay(String start);

    List<Order> getOrders(Optional<Integer> id, Optional<Boolean> completed);

    Optional<List<EmployeeReport>> getProfitReportByEmployee(Date start, Date end);


    Optional<Order> getOrderById(int id);
}
