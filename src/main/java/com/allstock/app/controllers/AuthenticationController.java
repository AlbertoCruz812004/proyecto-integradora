package com.allstock.app.controllers;

import com.allstock.app.controllers.dto.AuthCreatorUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allstock.app.controllers.dto.AuthLoginRequest;
import com.allstock.app.controllers.dto.AuthResponse;
import com.allstock.app.services.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> loging(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreatorUserRequest authCreatorUser) {
        return new ResponseEntity<>(this.userDetailsService.createUser(authCreatorUser), HttpStatus.OK);
    }
}
