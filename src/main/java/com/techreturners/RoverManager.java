package com.techreturners;

import com.techreturners.constants.Direction;
import com.techreturners.constants.Instruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.techreturners.constants.GridConstants.DEFAULT_LEFT_LOWER_CORNER_X;
import static com.techreturners.constants.GridConstants.DEFAULT_LEFT_LOWER_CORNER_Y;

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

    /**
     * Checks if instruction is one of L, R or M
     * @param instruction Single instruction
     * @return true if valid instruction
     */
    public static boolean isInstructionValid(String instruction) {
        try {
            Instruction.valueOf(instruction);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks validity of the instructions sent
     * @param instructions String containing the entire instruction
     * @return true if the instructions are valid
     */
    public static boolean isInstructionSetValid(String instructions) {
        String[] instructionArray = instructions.split("");
        return Arrays.stream(instructionArray).allMatch(RoverManager::isInstructionValid);
    }

    /**
     * Checks if the position is within the plateau and also that there is no other rover at that position
     * @param x initial x coordinate
     * @param y initial y coordinate
     * @param dir initial direction
     * @return true if the position is valid else false
     */
    public boolean isInitialPositionValid(int x, int y, String dir) {
        if(x <= plateau.getMaxX() && x >= plateau.getMinX() &&
                y <= plateau.getMaxY() && y >= plateau.getMinY()){
            boolean isNotPresentAtPos = roverList.stream()
                    .noneMatch(rover -> rover.getPosX()== x && rover.getPosY()==y);
            if (isNotPresentAtPos) {
                try {
                    Direction.valueOf(dir);
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Create Plateau with default min and max coordinates. This can be used whe the user wants to use the default plateau
     */
    public void createDefaultPlateau() {
        plateau = new Plateau();
    }

    /**
     * Creates plateau with default min coordinates and uses the max coordinates provided by the user
     * @param maxX right rop corner
     * @param maxY right rop corner
     * @return true when plateau is successfully created when the coordinates are valid
     */
    public boolean createPlateauWithUserProvidedMaxCoordinates(int maxX, int maxY) {
        if(maxX>DEFAULT_LEFT_LOWER_CORNER_X && maxY>DEFAULT_LEFT_LOWER_CORNER_Y) {
            plateau = new Plateau(maxX, maxY);
            return true;
        }
        return false;
    }

    /**
     * Creates plateau with all the coordinates provided by the user
     * @param minX left bottom corner
     * @param minY left bottom corner
     * @param maxX right rop corner
     * @param maxY right top corner
     * @return True when plateau is successfully created when the coordinates are valid
     */
    public boolean createPlateauWithUserProvidedMinAndMaxCoordinates(int minX, int minY, int maxX, int maxY) {
        if(maxX > minX && maxY> minY) {
            plateau = new Plateau(minX, minY, maxX, maxY);
            return true;
        }
        return false;

    }

    /**
     * Use this method to create a rover with instructions and add it to the list of rovers
     * @param x initial x coordinate of rover
     * @param y initial y coordinate of rover
     * @param dir initial orientation of rover
     * @param instructionSet instructions to move the rover
     * @return true when the all the input is valid and rover is successfully created else return false
     */
    public boolean addRoverToBeManaged(int x, int y, String dir, String instructionSet) {
        if(isInitialPositionValid(x, y, dir) && isInstructionSetValid(instructionSet)) {
            Rover rover = new Rover(x,y, Direction.valueOf(dir));
            rover.setInstructions(instructionSet);
            roverList.add(rover);
            return true;
        }
        return false;
    }

    /**
     * Use this method to execute the instruction for all the rovers that have been added
     */
    public void executeInstructionsForAllRovers() {
        for(Rover rover:roverList){
            rover.executeInstructionSet(plateau, roverList);
        }
    }
}
