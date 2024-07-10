package com.techreturners;

import com.techreturners.constants.Instruction;

import java.util.ArrayList;
import java.util.List;

public class RoverManager {
    private Plateau plateau;
    private List<Rover> roverList;

    public RoverManager() {
        roverList = new ArrayList<>();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public List<Rover> getRoverList() {
        return roverList;
    }

    public void setRoverList(List<Rover> roverList) {
        this.roverList = roverList;
    }

    public boolean isInstructionValid(String instruction) {
        try {
            Instruction.valueOf(instruction);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
