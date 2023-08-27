package com.jwt.controllers;

import com.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class MainController {

    @Autowired
    private UserService userService;

    //handler to get all user details
    @GetMapping("/user")
    public ResponseEntity<?> getUser() {

        System.out.println("Getting user...!");

        return ResponseEntity.ok(this.userService.getUser());
    }

    //handler to get logged-in user-name
    @GetMapping("/current-user")
    public String getCurrentUser(Principal principal) {

        return principal.getName();
    }
}
