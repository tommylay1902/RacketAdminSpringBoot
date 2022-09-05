package com.example.security;

import com.example.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private static final Logger logger = LoggerFactory.getLogger(UserPrincipal.class);

    private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        logger.info("user principal hit!");
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        logger.info("in the principal class " + this.user.getRoles());
        if(this.user.getRoles().equals("EMPLOYEE")){
            list.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        }
        else if(this.user.getRoles().equals("MANAGER")){
            list.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }

        logger.info(list.get(0).getAuthority());

        return list;
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

    public User getAppUser() {
        return user;
    }

}
