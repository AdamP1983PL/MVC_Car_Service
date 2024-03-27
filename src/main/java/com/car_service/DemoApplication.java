package com.car_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:database.properties")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

// todo add swagger documentation???
// todo email notification
// todo scheduler
// todo second design pattern (observer?)
// todo user login
// todo add AOP
// todo pagination and sorting
// todo find all customers vehicles
// todo find all customer service-orders
// todo find all service-orders by status
// todo find all service-orders by registration (show history)
