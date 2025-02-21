package com.youxi.math.hexagon.beans;

import com.youxi.math.hexagon.HexagonConst;

public class Cell {
    private AxialCoordinates axialCoordinates;
    private Coordinates coordinates;
    private OffsetCoordinates offsetCoordinates;

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
}
