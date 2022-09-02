package com.example.service;

import javax.annotation.PostConstruct;

import com.example.entities.RacketAdmin;
import com.example.repos.RacketAdminRepository;
import com.example.security.RacketAdminPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private WebApplicationContext applicationContext;

    private RacketAdminRepository userRepository;

    @PostConstruct
    public void completeSetup() {
        userRepository = applicationContext.getBean(RacketAdminRepository.class);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final RacketAdmin appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new RacketAdminPrincipal(appUser);
    }
}