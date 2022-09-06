package com.example.service;

import com.example.entities.Order;
import com.example.entities.views.EmployeeReport;
import com.example.repos.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    OrderRepository repo;

    @Override
    public ResponseEntity<String> insert(Order o) {
        //Search if the primary key/dept already exists in the database
        //insert the new entry if the primary key is not taken
        try {
            Optional<Order> order = repo.findById(o.getId());
            if (order.isEmpty()) {
                repo.save(o);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }

    @Override
    public ResponseEntity<String> update(Order o, int id) {

        //Find the entry in the database using deptno
        //update the information
        try {
            Optional<Order> order = repo.findById(id);
            order.get().setId(o.getId());
            order.get().setCustomerName(o.getCustomerName());
            order.get().setUser(o.getUser());
            order.get().setDescription(o.getDescription());
            order.get().setCustomerPhoneNum(o.getCustomerPhoneNum());
            order.get().setDesiredTension(o.getDesiredTension());
            order.get().setReturnDay(o.getReturnDay());
            order.get().setPrice(o.getPrice());
            order.get().setCompleted(o.isCompleted());
            order.get().setReceivedDay(o.getReceivedDay());
            order.get().setRacketBrand(o.getRacketBrand());
            order.get().setRecTension(o.getRecTension());
            order.get().setStringPattern(o.getStringPattern());
            order.get().setStringType(o.getStringType());
            repo.save(order.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception exc) {
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        //Search if the entry existed in the database
        //If so, delete it
        try {
            Optional<Order> order = repo.findById(id);
            if (!order.isEmpty()) {
                repo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }

        } catch (Exception exc) {
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteAll() {

        //Check if the database is empty
        //if now, delete all the entries in the table
        try {
            List<Order> order = repo.findAll();
            if (order != null) {
                repo.deleteAll();
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public Optional<List<Order>> receivedOrdersByDay(String start) {
        logger.info((start));
        return repo.receivedOrdersByDay(start);
    }

    @Override
    public List<Order> getOrders(Optional<Integer> userId, Optional<Boolean> completed) {

        return repo.getAllOrdersByOptionalUserAndOptionalCompleted(userId, completed);
    }

    @Override
    public Optional<List<EmployeeReport>> getProfitReportByEmployee(Date start, Date end) {
        return repo.generateProfitReportByEmployee(start, end);
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return repo.findById(id);
    }
}