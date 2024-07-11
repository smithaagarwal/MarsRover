package com.techreturners;

import com.techreturners.constants.Direction;
import com.techreturners.constants.Instruction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoverManagerTest {
    @ParameterizedTest(name = "rover instruction = {0}")
    @EnumSource(Instruction.class)
    public void shouldReturnTrueForValidInstruction(Instruction instruction) {
        boolean isValid = RoverManager.isInstructionValid(instruction.toString());
        switch (instruction) {
            case L, R, M -> assertTrue(isValid);
        }
    }

    @Test
    public void shouldReturnFalseForInvalidInstruction() {
        assertFalse(RoverManager.isInstructionValid("A"));
    }

    @ParameterizedTest
    @CsvSource({
            "'LRMM', true",
            "'LLR', true",
            "'MMM', true",
            "'LRX', false",
            "'lrm', false",
    })
    public void isInstructionSetValidTest(String instructionSet, boolean expectedOutput) {
        assertEquals(expectedOutput, RoverManager.isInstructionSetValid(instructionSet));
    }

    @ParameterizedTest
    @CsvSource({
            "9, 9, 'N', true",
            "11, 9, 'N', false",
            "10, 10, 'S', true",
            "0, 0, 'N', true",
            "1, 1, 'D', false",
            "-1, -1, 'S', false"
    })
    void isInitialPositionValidTest(int roverPosX, int roverPosY, String orientation, boolean expectedOutput) {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        assertEquals(expectedOutput, rm.isInitialPositionValid(roverPosX, roverPosY, orientation));
    }

    @Test
    void shouldReturnFalse_ifAnotherRoverIsPresentAtThatPosition_isInitialPositionValid() {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        Rover r1 = new Rover(1, 2, Direction.N);
        List<Rover> roverList = new ArrayList<>();
        roverList.add(r1);
        rm.setRoverList(roverList);
        assertFalse(rm.isInitialPositionValid(1, 2, "E"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,'N','LMLMLMLMM'",
            "3,3,'E','MMRMMRMRRM'",
            "9,9,'E','MMLMM'",
    })
    public void addRoverToBeManagedTest(int roverInitialPosX, int roverInitialPosY, String roverInitialOrientation,
                                        String roverInstructionSet) {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        rm.addRoverToBeManaged(roverInitialPosX, roverInitialPosY, roverInitialOrientation, roverInstructionSet);
        assertEquals(roverInstructionSet, rm.getRoverList().get(0).getInstructions());
        assertEquals(roverInitialPosX, rm.getRoverList().get(0).getPosX());
        assertEquals(roverInitialPosY, rm.getRoverList().get(0).getPosY());
        assertEquals(roverInitialOrientation, rm.getRoverList().get(0).getOrientation().toString());
    }

    @Test
    public void shouldThrowExceptionForInvalidCoordinates_addRoverToBeManaged() {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        var exception = assertThrows(IllegalArgumentException.class, () -> rm.addRoverToBeManaged(20, 0, "N", "MML"));
        assertEquals("Rover cannot be placed at 20, 0 facing direction N", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionForInvalidDirection_addRoverToBeManaged() {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        var exception = assertThrows(IllegalArgumentException.class, () -> rm.addRoverToBeManaged(0, 0, "C", "MML"));
        assertEquals("Rover cannot be placed at 0, 0 facing direction C", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionForInvalidInstructionSet_addRoverToBeManaged() {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        var exception = assertThrows(IllegalArgumentException.class, () -> rm.addRoverToBeManaged(0, 0, "N", "MMLRZ"));
        assertEquals("Instruction to place the Rover is invalid (MMLRZ)", exception.getMessage());
    }

    @Test
    void executeInstructionsForAllRovers_whenTheyDoNotCauseObstruction() {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        Rover r1 = new Rover(1, 2, Direction.N);
        r1.setInstructions("LMLMLMLMM");
        Rover r2 = new Rover(3, 3, Direction.E);
        r2.setInstructions("MMRMMRMRRM");
        List<Rover> roverList = new ArrayList<>();
        roverList.add(r1);
        roverList.add(r2);
        rm.setRoverList(roverList);
        rm.executeInstructionsForAllRovers();
        Rover rover1 = rm.getRoverList().get(0);
        Rover rover2 = rm.getRoverList().get(1);
        assertEquals(1, rover1.getPosX());
        assertEquals(3, rover1.getPosY());
        assertEquals(Direction.N, rover1.getOrientation());

        assertEquals(5, rover2.getPosX());
        assertEquals(1, rover2.getPosY());
        assertEquals(Direction.E, rover2.getOrientation());
    }

    @Test
    void executeInstructionsForAllRovers_whenTheyDoCauseObstruction() {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        Rover r1 = new Rover(1, 2, Direction.N);
        r1.setInstructions("LM");
        Rover r2 = new Rover(1, 3, Direction.E);
        r2.setInstructions("RMRMMRM");
        List<Rover> roverList = new ArrayList<>();
        roverList.add(r1);
        roverList.add(r2);
        rm.setRoverList(roverList);
        rm.executeInstructionsForAllRovers();
        Rover rover1 = rm.getRoverList().get(0);
        Rover rover2 = rm.getRoverList().get(1);
        assertEquals(0, rover1.getPosX());
        assertEquals(2, rover1.getPosY());
        assertEquals(Direction.W, rover1.getOrientation());

        assertEquals(1, rover2.getPosX());
        assertEquals(2, rover2.getPosY());
        assertEquals(Direction.W, rover2.getOrientation());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 10, 10"
    })
    void createDefaultPlateauTest(int defaultMinX, int defaultMinY, int defaultMaxX, int defaultMaxY) {
        RoverManager rm = new RoverManager();
        rm.createDefaultPlateau();
        assertEquals(defaultMinX, rm.getPlateau().getMinX());
        assertEquals(defaultMinY, rm.getPlateau().getMinY());
        assertEquals(defaultMaxX, rm.getPlateau().getMaxX());
        assertEquals(defaultMaxY, rm.getPlateau().getMaxY());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 20, 15,true",
            "0, 0, 5, 5, true",
            "0, 0, -5, 15, false"
    })
    void createPlateauWithUserProvidedMaxCoordinatesTest(int defaultMinX, int defaultMinY, int maxX, int maxY, boolean expectedOutput) {
        RoverManager rm = new RoverManager();
        boolean success = rm.createPlateauWithUserProvidedMaxCoordinates(maxX, maxY);
        assertEquals(expectedOutput, success);
        if (success) {
            assertEquals(defaultMinX, rm.getPlateau().getMinX());
            assertEquals(defaultMinY, rm.getPlateau().getMinY());
            assertEquals(maxX, rm.getPlateau().getMaxX());
            assertEquals(maxY, rm.getPlateau().getMaxY());
        } else {
            assertNull(rm.getPlateau());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 20, 15,true",
            "-5, -5, 5, 5, true",
            "5, 5, -5, 15, false",
            "5, 5, 5, 15, false",

    })
    void createPlateauWithUserProvidedMinAndMaxCoordinates(int minX, int minY, int maxX, int maxY, boolean expectedOutput) {
        RoverManager rm = new RoverManager();
        boolean success = rm.createPlateauWithUserProvidedMinAndMaxCoordinates(minX, minY, maxX, maxY);
        assertEquals(expectedOutput, success);
        if (success) {
            assertEquals(minX, rm.getPlateau().getMinX());
            assertEquals(minY, rm.getPlateau().getMinY());
            assertEquals(maxX, rm.getPlateau().getMaxX());
            assertEquals(maxY, rm.getPlateau().getMaxY());
        } else {
            assertNull(rm.getPlateau());
        }
    }
}