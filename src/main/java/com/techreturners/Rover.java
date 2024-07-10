package com.techreturners;

import com.techreturners.constants.Direction;

public class Rover {
    private int posX;
    private int posY;
    private Direction direction;

    public Rover(int x, int y, Direction direction) {
        posX = x;
        posY = y;
        this.direction = direction;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void moveRover() {
        switch (direction) {
            case E -> posX++;
            case W -> posX--;
            case N -> posY++;
            case S -> posY--;
        }
    }
}
