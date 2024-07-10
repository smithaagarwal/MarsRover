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

}