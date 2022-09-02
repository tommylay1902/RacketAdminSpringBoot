package com.example.security;

import com.example.entities.RacketAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RacketAdminPrincipal implements UserDetails {
    private static final Logger logger = LoggerFactory.getLogger(RacketAdminPrincipal.class);

    private final RacketAdmin user;

    public RacketAdminPrincipal(RacketAdmin user) {
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
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        logger.info("in the principal class " + this.user.getRoles());
        if(this.user.getRoles().equals("USER")){
            list.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else if(this.user.getRoles().equals("ADMIN")){
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
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

    public RacketAdmin getAppUser() {
        return user;
    }

}
