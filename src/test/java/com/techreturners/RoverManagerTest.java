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

    @ParameterizedTest
    @CsvSource({
            "1,2,'N','LMLMLMLMM',true",
            "3,3,'E','MMRMMRMRRM',true",
            "9,9,'E','MMLMM',true",
            "20,0,'N','MML', false",
            "0,0,'X','MML', false",
            "0,0,'N','MMLRZ', false",
    })
    public void addRoverToBeManagedTest(int roverInitialPosX, int roverInitialPosY, String roverInitialOrientation,
                                        String roverInstructionSet, boolean expectedOutput) {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        assertEquals(expectedOutput,rm.addRoverToBeManaged(roverInitialPosX,roverInitialPosY,roverInitialOrientation,roverInstructionSet));
        if(expectedOutput)
            assertEquals(roverInstructionSet,rm.getRoverList().getLast().getInstructions());
    }

    @Test
    void executeInstructionsForAllRovers_whenTheyDoNotCauseObstruction() {
        RoverManager rm = new RoverManager();
        rm.setPlateau(new Plateau());
        Rover r1 = new Rover(1,2,Direction.N);
        r1.setInstructions("LMLMLMLMM");
        Rover r2 = new Rover(3,3,Direction.E);
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
        Rover r1 = new Rover(1,2,Direction.N);
        r1.setInstructions("LM");
        Rover r2 = new Rover(1,3,Direction.E);
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
}