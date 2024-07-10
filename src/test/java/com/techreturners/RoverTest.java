package com.techreturners;

import com.techreturners.constants.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    @Test
    public void createRoverWithInitialCoordinatesAndDirection() {
        int initialXPosForRover = 3;
        int initialYPosForRover = 4;
        Direction dir = Direction.S;
        Rover marsRover = new Rover(initialXPosForRover,initialYPosForRover,dir);
        assertEquals(initialXPosForRover,marsRover.getPosX());
        assertEquals(initialYPosForRover,marsRover.getPosY());
        assertEquals(dir,marsRover.getDirection());
    }

    @Test
    public void shouldIncreaseXbyOne_whenRoverDirectionIsE_moveRover() {
        Rover r = new Rover(3,4, Direction.E);
        r.moveRover();
        assertEquals(4,r.getPosX());
        assertEquals(4,r.getPosY());
    }

    @Test
    public void shouldDecreaseXbyOne_whenRoverDirectionIsW_moveRover() {
        Rover r = new Rover(3,4, Direction.W);
        r.moveRover();
        assertEquals(2,r.getPosX());
        assertEquals(4,r.getPosY());
    }

    @Test
    public void shouldIncreaseYbyOne_whenRoverDirectionIsN_moveRover() {
        Rover r = new Rover(3,4, Direction.N);
        r.moveRover();
        assertEquals(3,r.getPosX());
        assertEquals(5,r.getPosY());
    }

    @Test
    public void shouldDecreaseYbyOne_whenRoverDirectionIsN_moveRover() {
        Rover r = new Rover(3,4, Direction.S);
        r.moveRover();
        assertEquals(3,r.getPosX());
        assertEquals(3,r.getPosY());
    }

}