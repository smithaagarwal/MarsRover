package com.techreturners;

import com.techreturners.constants.Direction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    private static Plateau plateau;

    @BeforeAll
    static void setUpBeforeAll() {
        plateau = new Plateau();
    }

    @Test
    public void createRoverWithInitialCoordinatesAndDirection() {
        int initialXPosForRover = 3;
        int initialYPosForRover = 4;
        Direction dir = Direction.S;
        Rover marsRover = new Rover(initialXPosForRover, initialYPosForRover, dir);
        assertEquals(initialXPosForRover, marsRover.getPosX());
        assertEquals(initialYPosForRover, marsRover.getPosY());
        assertEquals(dir, marsRover.getOrientation());
    }

    @Test
    public void shouldIncreaseXbyOne_whenRoverDirectionIsE_moveRover() {
        Rover r = new Rover(3, 4, Direction.E);
        r.moveRover(plateau);
        assertEquals(4, r.getPosX());
        assertEquals(4, r.getPosY());
    }

    @Test
    public void shouldDecreaseXbyOne_whenRoverDirectionIsW_moveRover() {
        Rover r = new Rover(3, 4, Direction.W);
        r.moveRover(plateau);
        assertEquals(2, r.getPosX());
        assertEquals(4, r.getPosY());
    }

    @Test
    public void shouldIncreaseYbyOne_whenRoverDirectionIsN_moveRover() {
        Rover r = new Rover(3, 4, Direction.N);
        r.moveRover(plateau);
        assertEquals(3, r.getPosX());
        assertEquals(5, r.getPosY());
    }

    @Test
    public void shouldDecreaseYbyOne_whenRoverDirectionIsN_moveRover() {
        Rover r = new Rover(3, 4, Direction.S);
        r.moveRover(plateau);
        assertEquals(3, r.getPosX());
        assertEquals(3, r.getPosY());
    }

    @Test
    public void shouldSetXtoMinXCoordinatesOfPlateau_whenRoverDirectionIsEAndPosXIsMaxXOfPlateau_moveRover() {
        Rover r = new Rover(plateau.getMaxX(), 4, Direction.E);
        r.moveRover(plateau);
        assertEquals(plateau.getMinX(), r.getPosX());
        assertEquals(4, r.getPosY());
    }

    @Test
    public void shouldSetXtoMaxXCoordinatesOfPlateau_whenRoverDirectionIsWAndPosXIsMinXOfPlateau_moveRover() {
        Rover r = new Rover(plateau.getMinX(), 4, Direction.W);
        r.moveRover(plateau);
        assertEquals(plateau.getMaxX(), r.getPosX());
        assertEquals(4, r.getPosY());
    }

    @Test
    public void shouldSetYtoMinYCoordinatesOfPlateau_whenRoverDirectionIsSAndPosYIsMaxYOfPlateau_moveRover() {
        Rover r = new Rover(3, plateau.getMinY(), Direction.S);
        r.moveRover(plateau);
        assertEquals(3, r.getPosX());
        assertEquals(plateau.getMaxY(), r.getPosY());
    }

    @Test
    public void shouldSetYtoMaxYCoordinatesOfPlateau_whenRoverDirectionIsNAndPosYIsMinYOfPlateau_moveRover() {
        Rover r = new Rover(3, plateau.getMaxY(), Direction.N);
        r.moveRover(plateau);
        assertEquals(3, r.getPosX());
        assertEquals(plateau.getMinY(), r.getPosY());
    }

    @Test
    void shouldSetOrientationToW_whenRoverCurrentOrientationIsN_turnLeft() {
        Rover r = new Rover(3, 3, Direction.N);
        r.turnLeft();
        assertEquals(Direction.W, r.getOrientation());
    }

    @Test
    void shouldSetOrientationToS_whenRoverCurrentOrientationIsW_turnLeft() {
        Rover r = new Rover(3, 3, Direction.W);
        r.turnLeft();
        assertEquals(Direction.S, r.getOrientation());
    }

    @Test
    void shouldSetOrientationToE_whenRoverCurrentOrientationIsS_turnLeft() {
        Rover r = new Rover(3, 3, Direction.S);
        r.turnLeft();
        assertEquals(Direction.E, r.getOrientation());
    }

    @Test
    void shouldSetOrientationToN_whenRoverCurrentOrientationIsE_turnLeft() {
        Rover r = new Rover(3, 3, Direction.E);
        r.turnLeft();
        assertEquals(Direction.N, r.getOrientation());
    }

    @ParameterizedTest(name = "rover orientation={0}")
    @EnumSource(Direction.class)
    void turnRightTest (Direction direction){
        Rover r = new Rover(3,3, direction);
        r.turnRight();
        switch (direction) {
            case N ->  assertEquals(Direction.E, r.getOrientation());
            case E -> assertEquals(Direction.S, r.getOrientation());
            case S -> assertEquals(Direction.W, r.getOrientation());
            case W -> assertEquals(Direction.N, r.getOrientation());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,'N','LMLMLMLMM',1,3,'N'",
            "3,3,'E','MMRMMRMRRM',5,1,'E'",
            "9,9,'E','MMLMM',0,0,'N'"
    })
    public void executeInstructionSetTest(int roverInitialPosX, int roverInitialPosY, String roverInitialOrientation,
                                          String roverInstructionSet, int roverExpectedPosX, int roverExpectedPosY, String roverExpectedOrientation) {
        Rover r = new Rover(roverInitialPosX, roverInitialPosY, Direction.valueOf(roverInitialOrientation));
        r.setInstructions(roverInstructionSet);
        r.executeInstructionSet(plateau);
        assertEquals(roverExpectedPosX, r.getPosX());
        assertEquals(roverExpectedPosY, r.getPosY());
        assertEquals(Direction.valueOf(roverExpectedOrientation), r.getOrientation());
    }
}