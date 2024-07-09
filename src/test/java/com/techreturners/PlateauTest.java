package com.techreturners;

import com.techreturners.constants.GridConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    @Test
    public void createPlateauWithDefaultCoordinates() {
        Plateau p = new Plateau();
        assertEquals(GridConstants.DEFAULT_LEFT_LOWER_CORNER_X, p.getMinX());
        assertEquals(GridConstants.DEFAULT_LEFT_LOWER_CORNER_Y, p.getMinY());
        assertEquals(GridConstants.DEFAULT_RIGHT_UPPER_CORNER_X, p.getMaxX());
        assertEquals(GridConstants.DEFAULT_RIGHT_UPPER_CORNER_Y, p.getMaxY());
    }

    @Test
    public void createPlateauWithDefaultLeftLowerCoordinatesAndUserProvidedUpperRightCoordinates() {
        int upperRightX = 5;
        int upperRightY = 5;
        Plateau p = new Plateau(upperRightX, upperRightY);
        assertEquals(GridConstants.DEFAULT_LEFT_LOWER_CORNER_X, p.getMinX());
        assertEquals(GridConstants.DEFAULT_LEFT_LOWER_CORNER_Y, p.getMinY());
        assertEquals(upperRightX, p.getMaxX());
        assertEquals(upperRightY, p.getMaxY());


    }
}