package com.car_service.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {

        log.info("====>>>> showLoginPage() execution.");
        return "/application/login-page";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {

        log.info("====>>>> showAccessDeniedPage() execution.");
        return "/application/access-denied";
    }

}
