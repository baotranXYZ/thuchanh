package com.example.tuan5_ketnoicsdl.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class HomeController {
@RequestMapping
    public String home(){return "home/index";}
}
