package com.techreturners;

import com.techreturners.constants.Direction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    private static Plateau plateau;
    @BeforeAll
    static void setUpBeforeAll()  {
        plateau= new Plateau();
    }
    @Test
    public void createRoverWithInitialCoordinatesAndDirection() {
        int initialXPosForRover = 3;
        int initialYPosForRover = 4;
        Direction dir = Direction.S;
        Rover marsRover = new Rover(initialXPosForRover,initialYPosForRover,dir);
        assertEquals(initialXPosForRover,marsRover.getPosX());
        assertEquals(initialYPosForRover,marsRover.getPosY());
        assertEquals(dir,marsRover.getOrientation());
    }

    @Test
    public void shouldIncreaseXbyOne_whenRoverDirectionIsE_moveRover() {
        Rover r = new Rover(3,4, Direction.E);
        r.moveRover(plateau);
        assertEquals(4,r.getPosX());
        assertEquals(4,r.getPosY());
    }

    @Test
    public void shouldDecreaseXbyOne_whenRoverDirectionIsW_moveRover() {
        Rover r = new Rover(3,4, Direction.W);
        r.moveRover(plateau);
        assertEquals(2,r.getPosX());
        assertEquals(4,r.getPosY());
    }

    @Test
    public void shouldIncreaseYbyOne_whenRoverDirectionIsN_moveRover() {
        Rover r = new Rover(3,4, Direction.N);
        r.moveRover(plateau);
        assertEquals(3,r.getPosX());
        assertEquals(5,r.getPosY());
    }

    @Test
    public void shouldDecreaseYbyOne_whenRoverDirectionIsN_moveRover() {
        Rover r = new Rover(3,4, Direction.S);
        r.moveRover(plateau);
        assertEquals(3,r.getPosX());
        assertEquals(3,r.getPosY());
    }

    @Test
    public void shouldSetXtoMinXCoordinatesOfPlateau_whenRoverDirectionIsEAndPosXIsMaxXOfPlateau_moveRover() {
        Rover r = new Rover(plateau.getMaxX(),4, Direction.E);
        r.moveRover(plateau);
        assertEquals(plateau.getMinX(),r.getPosX());
        assertEquals(4,r.getPosY());
    }

    @Test
    public void shouldSetXtoMaxXCoordinatesOfPlateau_whenRoverDirectionIsWAndPosXIsMinXOfPlateau_moveRover() {
        Rover r = new Rover(plateau.getMinX(),4, Direction.W);
        r.moveRover(plateau);
        assertEquals(plateau.getMaxX(),r.getPosX());
        assertEquals(4,r.getPosY());
    }

    @Test
    public void shouldSetYtoMinYCoordinatesOfPlateau_whenRoverDirectionIsSAndPosYIsMaxYOfPlateau_moveRover() {
        Rover r = new Rover(3,plateau.getMinY(), Direction.S);
        r.moveRover(plateau);
        assertEquals(3,r.getPosX());
        assertEquals(plateau.getMaxY(),r.getPosY());
    }

    @Test
    public void shouldSetYtoMaxYCoordinatesOfPlateau_whenRoverDirectionIsNAndPosYIsMinYOfPlateau_moveRover() {
        Rover r = new Rover(3,plateau.getMaxY(), Direction.N);
        r.moveRover(plateau);
        assertEquals(3,r.getPosX());
        assertEquals(plateau.getMinY(),r.getPosY());
    }

}