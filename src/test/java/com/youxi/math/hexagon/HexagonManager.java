package com.youxi.math.hexagon;


import com.youxi.math.hexagon.aStar.AStarGraph;
import com.youxi.math.hexagon.aStar.Graph;
import com.youxi.math.hexagon.aStar.GraphPoint;
import com.youxi.math.hexagon.beans.AxialCoordinates;
import com.youxi.math.hexagon.beans.Cell;
import com.youxi.math.hexagon.beans.OffsetCoordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HexagonManager {

    private static HexagonManager instance = new HexagonManager();

    public static HexagonManager getInstance() {
        return instance;
    }

    private Cell[] cells;
    private Graph graph;

    private HexagonManager() {
        this.cells = new Cell[HexagonConst.height * HexagonConst.width];
        for (int z = 0; z < HexagonConst.height; z++) {
            for (int x = 0; x < HexagonConst.width; x++) {
                Cell cell = new Cell(new OffsetCoordinates(x, z));
                this.cells[cell.getIndex()] = cell;
            }
        }
        // 构建A*寻路图
        graph = AStarGraph.buildGraph(this);
    }

    public Cell getCell(int index){
        if (index < 0 || index>=cells.length) {
            return null;
        }
        return cells[index];
    }

    public Cell[] getCells() {
        return cells;
    }

    public  List<Cell> findPath(int startIndex, int endIndex, Set<Integer> obstracleSet) {
        AStarGraph.Node startNode = new AStarGraph.Node(new GraphPoint(startIndex));
        AStarGraph.Node endNode = new AStarGraph.Node(new GraphPoint(endIndex));
        List<AStarGraph.Node> path = AStarGraph.findPath(startNode, endNode, graph, obstracleSet);
        return path.stream().map(a->getCell(a.point.getIndex())).collect(Collectors.toList());
    }

    /**
     * 两个cell的距离
     */
    public static int distance(Cell cell1, Cell cell2) {
        AxialCoordinates c1 = cell1.getAxialCoordinates();
        AxialCoordinates c2 = cell2.getAxialCoordinates();

        int q = c1.getQ() - c2.getQ();
        int r = c1.getR() - c2.getR();
        int s = c1.getS() - c2.getS();

        return (Math.abs(q) + Math.abs(r) + Math.abs(s)) / 2;
    }


    /**
     * cell的邻居
     */
    private static final int[][] axial_direction_vectors =
            {{+1, 0}, {+1, -1}, {0, -1},
              {-1, 0}, {-1, +1}, {0, +1}};


    public List<Cell> getNeighbors(Cell cell) {
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

    /*
     * 两个点经过的六边形
     */


}
