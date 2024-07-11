package com.techreturners.controller;

import com.techreturners.Plateau;
import com.techreturners.Rover;
import com.techreturners.RoverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return new ResponseEntity<>("Plateau dimensions: (" + plateau.getMinX() + ", " + plateau.getMinY() +") (" +plateau.getMaxX() + " , " + plateau.getMaxY()+")", HttpStatus.CREATED);
    }

    @PostMapping("/plateau/max-coordinates")
    public ResponseEntity<String> createPlateauWithMaxCoordinates(@RequestParam int maxX, @RequestParam int maxY) {
        boolean success = roverManager.createPlateauWithUserProvidedMaxCoordinates(maxX, maxY);
        if (success) {
            Plateau plateau = roverManager.getPlateau();
            return new ResponseEntity<>("Plateau dimensions: (" + plateau.getMinX() + ", " + plateau.getMinY() +") (" +plateau.getMaxX() + " , " + plateau.getMaxY()+")", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid coordinates passed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/plateau/min-max-coordinates")
    public ResponseEntity<String> createPlateauWithMinAndMaxCoordinates(@RequestParam int minX, @RequestParam int minY, @RequestParam int maxX, @RequestParam int maxY) {
        boolean success = roverManager.createPlateauWithUserProvidedMinAndMaxCoordinates(minX, minY, maxX, maxY);
        if (success) {
            Plateau plateau = roverManager.getPlateau();
            return new ResponseEntity<>("Plateau dimensions: (" + plateau.getMinX() + ", " + plateau.getMinY() +") (" +plateau.getMaxX() + " , " + plateau.getMaxY()+")", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid coordinates passed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/plateau")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getPlateau() {
        Plateau plateau = roverManager.getPlateau();
        if (plateau != null) {
            return new ResponseEntity<>("Plateau dimensions: " + plateau.getMaxX() + " x " + plateau.getMaxY(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No plateau has been created yet.", HttpStatus.OK);
        }
    }

    @PostMapping("/rovers")
    public ResponseEntity<String> addRover(@RequestParam int x, @RequestParam int y, @RequestParam String dir, @RequestParam String instructionSet) {
        try {
            roverManager.addRoverToBeManaged(x, y, dir, instructionSet);
            return new ResponseEntity<>("Rover successfully added", HttpStatus.CREATED);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/rovers")
    public ResponseEntity<List<Rover>> getRoverList() {
        List<Rover> roverList = roverManager.getRoverList();
        return new ResponseEntity<>(roverList, HttpStatus.OK);
    }

    @PostMapping("/rovers/execute")
    public ResponseEntity<String> executeInstructionsForAllRovers() {
        roverManager.executeInstructionsForAllRovers();
        return new ResponseEntity<>("Executed instructions for all the rovers", HttpStatus.OK);
    }
}