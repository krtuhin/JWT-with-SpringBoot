package com.jwt.controllers;

import com.jwt.models.JwtRequest;
import com.jwt.models.JwtResponse;
import com.jwt.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    //handler for login by user-name & password and generate token
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        //user-name & password authentication
        this.doAuthenticate(request.getEmail(), request.getPassword());

        //getting user-details by user-name
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());

        //generating token
        String token = this.helper.generateToken(userDetails);

        //setting values of jwt response
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();

        //JwtResponse response = new JwtResponse(token,userDetails.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //method for user-name & password authentication
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);

        try {
            this.authenticationManager.authenticate(authentication);

        } catch (BadCredentialsException e) {

            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    //authentication exception handling
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}