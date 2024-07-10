package com.techreturners;

import com.techreturners.constants.Direction;
import com.techreturners.constants.Instruction;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static boolean isInstructionValid(String instruction) {
        try {
            Instruction.valueOf(instruction);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static boolean isInstructionSetValid(String instructions) {
        String[] instructionArray = instructions.split("");
        return Arrays.stream(instructionArray).allMatch(RoverManager::isInstructionValid);
    }

    public boolean isInitialPositionValid(int x, int y, String dir) {
        if(x <= plateau.getMaxX() && x >= plateau.getMinX() &&
                y <= plateau.getMaxY() && y >= plateau.getMinY()){
            try {
                Direction.valueOf(dir);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return false;
    }
}
