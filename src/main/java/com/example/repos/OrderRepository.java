package com.example.repos;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
