package com.youxi.math.hexagon.beans;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(x, that.x) == 0 && Float.compare(z, that.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", z=" + z +
                '}';
    }
}
