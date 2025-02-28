package com.youxi.math.hexagon.beans;


import com.youxi.math.hexagon.HexagonConst;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {
    private AxialCoordinates axialCoordinates;
    private Coordinates coordinates;
    private OffsetCoordinates offsetCoordinates;

    private List<Integer> neighbors = new ArrayList<>();

    private int index;

    public Cell(OffsetCoordinates offsetCoordinates) {
        this.offsetCoordinates = offsetCoordinates;
        this.index = HexagonConst.getIndex(offsetCoordinates);
        this.axialCoordinates = HexagonConst.offsetToAxial(offsetCoordinates);
        this.coordinates =  HexagonConst.pointy_hex_to_pixel(axialCoordinates);
    }


    public AxialCoordinates getAxialCoordinates() {
        return axialCoordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public OffsetCoordinates getOffsetCoordinates() {
        return offsetCoordinates;
    }

    public int getIndex() {
        return index;
    }


    public List<Integer> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Integer> neighbors) {
        this.neighbors = neighbors;
    }

    public int distance(Cell b) {
        return axialCoordinates.distance(b.axialCoordinates);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return index == cell.index && Objects.equals(axialCoordinates, cell.axialCoordinates) && Objects.equals(coordinates, cell.coordinates) && Objects.equals(offsetCoordinates, cell.offsetCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(axialCoordinates, coordinates, offsetCoordinates, index);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "axialCoordinates=" + axialCoordinates +
                ", coordinates=" + coordinates +
                ", offsetCoordinates=" + offsetCoordinates +
                ", index=" + index +
                '}';
    }
}
