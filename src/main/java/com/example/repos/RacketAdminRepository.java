package com.example.repos;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface RacketAdminRepository extends JpaRepository<RacketAdmin, Integer> {
    public RacketAdmin findByUsername(String username);

    @Query("SELECT COUNT(o.id) AS Orders " +
            "FROM orders o " +
            "WHERE received_day >= ?1 OR return_day <= ?2")
    int ordersByDay(Date start, Date end);
}
