package com.allstock.app.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping(value = "/sign-up")
    public String viewSignUp() {
        return "sign-up2";
    }

    @GetMapping(value = "/log-in")
    public String viewLogin() {
        return "Login";
    }

    @GetMapping(value = "/demo")
    public String viewDemo() {
        return "demo";
    }
}
