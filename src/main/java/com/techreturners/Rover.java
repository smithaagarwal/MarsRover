package com.techreturners;

import com.techreturners.constants.Direction;
import com.techreturners.constants.Instruction;

import static com.techreturners.constants.Direction.*;

public class Rover {
    private int posX;
    private int posY;
    private Direction orientation;
    private String instructions;

    public Rover(int x, int y, Direction direction) {
        posX = x;
        posY = y;
        this.orientation = direction;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void moveRover(Plateau plateau) {
        switch (orientation) {
            case E -> posX = (posX == plateau.getMaxX())? plateau.getMinX(): posX+1;
            case W -> posX = (posX == plateau.getMinX())? plateau.getMaxX(): posX-1;
            case N -> posY = (posY == plateau.getMaxY())? plateau.getMinY(): posY+1;
            case S -> posY = (posY == plateau.getMinY())? plateau.getMaxY(): posY-1;
        }
    }

    public void turnLeft() {
        switch (orientation) {
            case N -> orientation = W;
            case W -> orientation = S;
            case S -> orientation = E;
            case E -> orientation = N;
        }
    }

    public void turnRight() {
        switch (orientation) {
            case N -> orientation = E;
            case E -> orientation = S;
            case S -> orientation = W;
            case W -> orientation = N;
        }
    }

    public void executeInstructionSet(Plateau plateau) {
        String[] instructionArray = instructions.split("");
        for(String singleInstruction: instructionArray) {
            switch (Instruction.valueOf(singleInstruction)) {
                case L -> turnLeft();
                case R -> turnRight();
                case M -> moveRover(plateau);
            }
        }
    }
}
