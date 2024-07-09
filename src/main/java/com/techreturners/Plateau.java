package com.techreturners;

import com.techreturners.constants.GridConstants;

public class Plateau {
    private final int maxX;
    private final int maxY;
    private final int minX;
    private final int minY;

    public Plateau() {
        minX = GridConstants.DEFAULT_LEFT_LOWER_CORNER_X;
        minY = GridConstants.DEFAULT_LEFT_LOWER_CORNER_Y;
        maxX = GridConstants.DEFAULT_RIGHT_UPPER_CORNER_X;
        maxY = GridConstants.DEFAULT_RIGHT_UPPER_CORNER_Y;
    }

    public Plateau(int maxX, int maxY) {
        minX = GridConstants.DEFAULT_LEFT_LOWER_CORNER_X;
        minY = GridConstants.DEFAULT_LEFT_LOWER_CORNER_Y;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Plateau(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

        public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

}
