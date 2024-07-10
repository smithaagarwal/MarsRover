package com.techreturners;

import com.techreturners.constants.Direction;

import static com.techreturners.constants.Direction.*;

public class Rover {
    private int posX;
    private int posY;
    private Direction orientation;

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
}
