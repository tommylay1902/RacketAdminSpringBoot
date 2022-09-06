package com.example.repos;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.Month;
import java.util.Optional;

@Repository
public interface RacketAdminRepository extends JpaRepository<RacketAdmin, Integer> {
    public RacketAdmin findByUsername(String username);

    @Query(value = "SELECT * " +
            "FROM orders " +
            "WHERE received_day = ?1", nativeQuery = true)
    Optional<Order> ordersByDay(Date start);


    @Query(value = "SELECT * " +
            "FROM orders " +
            "WHERE received_day = ?1 OR return_day <= ?2", nativeQuery = true)
    Optional<Order> ordersByWeek(Date start, Date end);


    @Query(value = "SELECT *" +
            "FROM orders" +
            "WHERE MONTH(received_day = ?1", nativeQuery = true)
    Optional<Order> OrdersByMonth(Month month);


}
