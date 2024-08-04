package com.allstock.app.controllers;

import com.allstock.app.persistence.entities.UserEntity;
import com.allstock.app.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createdUser(@Validated @RequestBody UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }
}
