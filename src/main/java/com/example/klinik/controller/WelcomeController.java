package com.example.klinik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Selamat Datang di Klinik Tarumanagara!";
    }
    //Mengirim ke welcome.html
    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }
}