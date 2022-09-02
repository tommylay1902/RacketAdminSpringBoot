package com.example.entities;

import com.example.security.RacketAdminPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name="Racket_Admin")
@Entity
public class RacketAdmin implements UserDetails {

    private static final Logger logger = LoggerFactory.getLogger(RacketAdminPrincipal.class);
    @Id
    private int id;
    private String username;
    private String password;
    private String roles;

    public RacketAdmin() {
    }

    public RacketAdmin(int id, String username, String password, String roles) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        logger.info("in the Entity class " + this.roles);
        if(this.roles.equals("USER")){
            list.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else if(this.roles.equals("ADMIN")){
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return list;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
}
