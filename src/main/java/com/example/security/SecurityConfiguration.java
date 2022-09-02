package com.example.security;

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

    //This bean will override the default security filter
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //allow for testing api endpoints in postman
                .csrf(c -> c.disable())
                .authorizeRequests((auth) -> {
                    //any path you try to access will need to make sure you are logged in first
                    auth.antMatchers("/*").authenticated();
                    //from test controller, will permit any logged in user to access
                    auth.antMatchers("/hello").permitAll();
                    auth.antMatchers("/dashboard").hasRole("USER");
                    //admin or user should have access to all orders
                    auth.antMatchers("/api/order/*").hasAnyRole("USER", "ADMIN");
                    //admin only has access to racketadmins CRUD operations
                    auth.antMatchers("/api/racketadmin/*").hasRole("ADMIN");
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