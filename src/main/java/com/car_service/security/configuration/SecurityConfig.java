package com.car_service.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN", "USER")
                .build();

        UserDetails user1 = User.builder()
                .username("user1")
                .password("{noop}user1")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user1);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config ->
                        config
                                .requestMatchers("/").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticate-the-user")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                );
        return http.build();

    }

}
