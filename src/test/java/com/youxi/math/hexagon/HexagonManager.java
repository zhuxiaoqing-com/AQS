package com.youxi.math.hexagon;


import com.youxi.math.hexagon.aStar.AStarGraph;
import com.youxi.math.hexagon.aStar.Graph;
import com.youxi.math.hexagon.aStar.GraphPoint;
import com.youxi.math.hexagon.beans.AxialCoordinates;
import com.youxi.math.hexagon.beans.Cell;
import com.youxi.math.hexagon.beans.OffsetCoordinates;

import java.util.*;
import java.util.stream.Collectors;

public class HexagonManager {
    private Graph graph;
    private Cell[] cells;
    private Map<AxialCoordinates, Cell> axial2CellMap = new HashMap<>();

    private HexagonManager() {
        this.cells = new Cell[HexagonConst.height * HexagonConst.width];
        for (int z = 0; z < HexagonConst.height; z++) {
            for (int x = 0; x < HexagonConst.width; x++) {
                Cell cell = new Cell(new OffsetCoordinates(x, z));
                this.cells[cell.getIndex()] = cell;
            }
        }

        // 设置邻居数据
        for (Cell cell : cells) {
            List<Cell> neighbors = getNeighbors(cell);
            for (Cell neighbor : neighbors) {
                cell.getNeighbors().add(neighbor.getIndex());
            }
        }

        // 构建A*寻路图
        graph = new Graph(this);
        graph = graph.buildGraph();

        for (Cell cell : cells) {
            axial2CellMap.put(cell.getAxialCoordinates(), cell);
        }

    }

    public Cell getCell(int index) {
        if (index < 0 || index >= cells.length) {
            return null;
        }
        return cells[index];
    }

    public Cell getCell(AxialCoordinates axialCoordinates) {
        return axial2CellMap.get(axialCoordinates);
    }


    public Cell[] getCells() {
        return cells;
    }

    public int distance(int index1, int index2) {
        Cell a = getCell(index1);
        Cell b = getCell(index2);
        return a.distance(b);
    }

    public List<Cell> findPath(int startIndex, int endIndex, IObstacleCheck iObstacleCheck) {
        AStarGraph.Node startNode = new AStarGraph.Node(new GraphPoint(startIndex));
        AStarGraph.Node endNode = new AStarGraph.Node(new GraphPoint(endIndex));
        List<AStarGraph.Node> path = AStarGraph.findPath(startNode, endNode, graph, iObstacleCheck);
        return path.stream().map(a -> getCell(a.point.getIndex())).collect(Collectors.toList());
    }

    /**
     * cell的邻居
     */
    private static final int[][] axial_direction_vectors =
            {{+1, 0}, {+1, -1}, {0, -1},
                    {-1, 0}, {-1, +1}, {0, +1}};


    /**
     * 获取邻居
     */
    private List<Cell> getNeighbors(Cell cell) {
        ArrayList<Cell> cellNeighbors = new ArrayList<>(axial_direction_vectors.length);
        for (int[] n : axial_direction_vectors) {
            int q = cell.getAxialCoordinates().getQ() + n[0];
            int r = cell.getAxialCoordinates().getR() + n[1];
            AxialCoordinates axialCoordinates = new AxialCoordinates(q, r);
            int index = HexagonConst.getIndex(HexagonConst.axialToOffset(axialCoordinates));
            Cell neighbors = getCell(index);
            if (neighbors != null) {
                cellNeighbors.add(neighbors);
            }
        }
        return cellNeighbors;
    }

    /**
     * 获取周围的坐标，包含自己
     */
    public List<Cell> coordinateRange(int index, int N) {
        Cell cell = getCell(index);
        List<AxialCoordinates> axialCoordinates = HexagonConst.coordinateRange(cell.getAxialCoordinates(), N);
        return axialCoordinates.stream()
                .map(this::getCell)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    private static HexagonManager instance = new HexagonManager();

    public static HexagonManager getInstance() {
        return instance;
    }

}
