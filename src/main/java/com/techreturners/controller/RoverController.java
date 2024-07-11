package com.techreturners.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoverController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Mars!";
    }
}