package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    //This bean will override the default security filter
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("default hit");
        http
                //allow for testing api endpoints in postman
                .csrf(c -> c.disable())
                .authorizeRequests((auth) -> {
                    //any path you try to access will need to make sure you are logged in first
                    auth.antMatchers("/*").authenticated();

                    auth.antMatchers("/api/employee/order/*").hasRole("EMPLOYEE");
                    //admin or user should have access to all orders
                    auth.antMatchers("/api/manager/order/*").hasAnyRole( "MANAGER");
                    //admin only has access to racketadmins CRUD operations
                    auth.antMatchers("/api/racketadmin/*").hasRole("MANAGER");
                })
                //
                .formLogin()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderTest();
    }


}