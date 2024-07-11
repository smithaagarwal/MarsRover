package com.techreturners.controller;

import com.techreturners.Plateau;
import com.techreturners.RoverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RoverController {

    @Autowired
    private RoverManager roverManager;
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Mars!";
    }

    @PostMapping("/plateau/default")
    public ResponseEntity<String> createDefaultPlateau() {
        roverManager.createDefaultPlateau();
        Plateau plateau = roverManager.getPlateau();
        return new ResponseEntity<>("Plateau dimensions: " + plateau.getMaxX() + " x " + plateau.getMaxY(), HttpStatus.CREATED);
    }

}