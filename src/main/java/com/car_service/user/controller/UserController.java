package com.car_service.user.controller;

import com.car_service.user.service.user_service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public String showAdminPage() {

        log.info("====>>>> showAdminPage() execution.");
        return "/user/admin-page";
    }

    @GetMapping("all-users")
    public String showAllUsers() {

        log.info("====>>>> showAllUsers() execution.");
        return "/user/all-users";
    }

}
