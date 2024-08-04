package com.allstock.app.controllers.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello, world";
    }

    @GetMapping(value = "/helloSecurity")
    public String helloSecureted() {
        return "Hello, world";
    }
}
