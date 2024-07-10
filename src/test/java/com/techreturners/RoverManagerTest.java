package com.techreturners;

import com.techreturners.constants.Instruction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

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
    @CsvSource ({
            "'LRMM', true",
            "'LLR', true",
            "'MMM', true",
            "'LRX', false",
            "'lrm', false",
    })
    public void isInstructionSetValidTest(String instructionSet, boolean expectedOutput) {
        assertEquals(expectedOutput, RoverManager.isInstructionSetValid(instructionSet));
    }
}