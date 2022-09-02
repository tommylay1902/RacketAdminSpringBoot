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
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .authorizeRequests((auth) -> {
                    auth.antMatchers("/api/racketadmin/showRA").permitAll();
                    auth.antMatchers("/*").authenticated();
                    auth.antMatchers("/hello").permitAll();
                    auth.antMatchers("/dashboard").hasRole("USER");
                    auth.antMatchers("/api/order/*").hasAnyRole("USER", "ADMIN");
                    auth.antMatchers("/api/racketadmin/*").hasRole("ADMIN");
                })
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