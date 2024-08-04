package com.allstock.app.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secure/view")
public class SecureViewController {

    @GetMapping(value = "/dash")
    public String viewDashboard() {
        return "dashboard";
    }
}
