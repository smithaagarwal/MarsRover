package com.techreturners;

import com.techreturners.constants.Direction;
import com.techreturners.constants.Instruction;

import java.util.List;
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

    public boolean moveRover(Plateau plateau, List<Rover> roverList) {
        int newX=posX;
        int newY = posY;
        switch (orientation) {
            case E -> newX = (posX == plateau.getMaxX())? plateau.getMinX(): posX+1;
            case W -> newX = (posX == plateau.getMinX())? plateau.getMaxX(): posX-1;
            case N -> newY = (posY == plateau.getMaxY())? plateau.getMinY(): posY+1;
            case S -> newY = (posY == plateau.getMinY())? plateau.getMaxY(): posY-1;
        }
        int finalNewX = newX;
        int finalNewY = newY;
        boolean isNotPresentAtPos = roverList.stream()
                .noneMatch(rover -> rover.getPosX()== finalNewX && rover.getPosY()==finalNewY);
        if (isNotPresentAtPos) {
            posX = newX;
            posY = newY;
            return true;
        }
        return false;
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

    public void executeInstructionSet(Plateau plateau, List<Rover> roverList) {
        String[] instructionArray = instructions.split("");
        boolean moveSuccess = true;
        for(String singleInstruction: instructionArray) {
            switch (Instruction.valueOf(singleInstruction)) {
                case L -> turnLeft();
                case R -> turnRight();
                case M ->  moveSuccess = moveRover(plateau, roverList);
            }
            if(!moveSuccess)
                break;
        }
    }
}
