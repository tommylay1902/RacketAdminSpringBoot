package com.example.repos;

import com.example.entities.Order;
import com.example.entities.User;
import com.example.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);


   // Optional<Order> OrdersByMonth(Month month);


}
