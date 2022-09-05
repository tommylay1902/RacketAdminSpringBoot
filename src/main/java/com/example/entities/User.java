package com.example.entities;

import com.example.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

@Table(name="user")
@Entity(name="user")
public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);
    @Id
    private int id;
    private String username;
    private String password;
    private String roles;

    @OneToMany(fetch = FetchType.LAZY,mappedBy= "user")
    private Set<Order> orders;

    public User() {
    }

    public User(int id, String username, String password, String roles) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
