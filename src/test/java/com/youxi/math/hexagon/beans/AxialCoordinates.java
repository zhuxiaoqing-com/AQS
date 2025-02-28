package com.youxi.math.hexagon.beans;

import java.util.Objects;

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

    public int distance(AxialCoordinates a) {
        AxialCoordinates c1 = this;

        int q = c1.getQ() - a.getQ();
        int r = c1.getR() - a.getR();
        int s = c1.getS() - a.getS();

        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AxialCoordinates)) return false;
        AxialCoordinates that = (AxialCoordinates) o;
        return q == that.q && r == that.r && s == that.s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, r, s);
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
