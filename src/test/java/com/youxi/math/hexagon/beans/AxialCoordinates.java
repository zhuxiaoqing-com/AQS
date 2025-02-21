package com.youxi.math.hexagon.beans;

/**
 * 轴坐标
 */
public class AxialCoordinates {
    private int q;
    private int r;
    private int s;

    public AxialCoordinates(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
    }

    public int getQ() {
        return q;
    }


    public int getR() {
        return r;
    }


    public int getS() {
        return s;
    }

    public AxialCoordinates subtract(AxialCoordinates a) {
        return new AxialCoordinates(this.q - a.q, this.r - a.r);
    }

    public AxialCoordinates add(AxialCoordinates a) {
        return new AxialCoordinates(this.q + a.q, this.r + a.r);
    }


    @Override
    public String toString() {
        return "AxialCoordinates{" +
                "q=" + q +
                ", r=" + r +
                ", s=" + s +
                '}';
    }
}
