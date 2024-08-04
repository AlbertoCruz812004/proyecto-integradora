package com.allstock.app.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String viewHome() {
        return "index";
    }

    @GetMapping(value = "/404")
    public String view404(){
        return "404";
    }
}
