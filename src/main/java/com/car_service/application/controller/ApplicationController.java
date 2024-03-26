package com.car_service.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ApplicationController {

    @GetMapping("/")
    public String showHome() {

        log.info("====>>>> showHome() execution.");
        return "/application/home";
    }
}
