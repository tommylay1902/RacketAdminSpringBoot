package com.example.repos;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByRacketAdminId(int id);
}
