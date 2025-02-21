package com.youxi.math.hexagon.beans;

/**
 * 坐标
 */
public class Coordinates {
    private float x;
    private float z;

    public Coordinates(float x, float z) {
        this.x = x;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", z=" + z +
                '}';
    }
}
