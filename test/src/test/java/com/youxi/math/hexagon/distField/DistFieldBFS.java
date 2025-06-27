package com.youxi.math.hexagon.distField;

import com.youxi.math.hexagon.HexagonConst;
import com.youxi.math.hexagon.HexagonManager;
import com.youxi.math.hexagon.IObstacleCheck;
import com.youxi.math.hexagon.beans.Cell;

import java.util.*;

/**
 * 距离场
 */
public class DistFieldBFS {
    public static int[] calcDistField(DistFieldObj distFieldObj, IObstacleCheck iObstacleCheck, HexagonManager instance) {
        int[] distField = distFieldObj.getDistField();
        int startIndex = distFieldObj.getStartIndex();
        Arrays.fill(distField, -1);

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        distField[startIndex] = 0;
        deque.add(startIndex);

        while (!deque.isEmpty()) {
            Integer poll = deque.poll();
            Cell curCell = instance.getCell(poll);
            int currIndex = curCell.getIndex();
            for (Integer neighbor : curCell.getNeighbors()) {
                if (iObstacleCheck.checkObstacle(neighbor)) {
                    continue;
                }
                if (distField[neighbor] == -1) {
                    distField[neighbor] = distField[currIndex] + 1;
                    deque.add(neighbor);
                }
            }
        }
        return distField;
    }

    public static List<Integer> findPathByDistField(DistFieldObj distFieldObj, int endIndex, HexagonManager instance) {
        int[] distField = distFieldObj.getDistField();
        int startIndex = distFieldObj.getStartIndex();

        // 无法到达
        if (distField[endIndex] == -1) {
            return Collections.emptyList();
        }


        ArrayList<Integer> path = new ArrayList<>();
        path.add(endIndex);
        int forIndex = endIndex;
        int forCount = 0;
        while (forIndex != startIndex) {
            if (forCount++ > 300) {
                System.out.println("while死循环了");
                return Collections.emptyList();
            }
            int range = distField[forIndex];
            Cell cell = instance.getCell(forIndex);
            for (Integer neighbor : cell.getNeighbors()) {
                if (distField[neighbor] == range - 1) {
                    forIndex = neighbor;
                    path.add(forIndex);
                    break;
                }
            }
        }

        Collections.reverse(path);

        return path;

    }

    public static void main(String[] args) {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(4);

        DistFieldObj distFieldObj = new DistFieldObj(30, 0);

        HexagonManager instance = HexagonManager.getInstance();
        int[] ints = calcDistField(distFieldObj, new IObstacleCheck() {
            @Override
            public boolean checkObstacle(int index) {
                return objects.contains(index);
            }

            @Override
            public Long[] getObstacles() {
                return new Long[0];
            }
        }, instance);

        printMap(ints);

        List<Integer> pathByDistField = findPathByDistField(distFieldObj, 13, instance);
        System.out.println(pathByDistField);

    }

    public static void printMap(int[] maps) {
        for (int j = 0; j < HexagonConst.height; j++) {
            for (int i = 0; i < HexagonConst.width; i++) {
                int index = j * HexagonConst.width + i;
                String map = index + "-" + maps[index] + "";
                int count = 10 - map.length();
                for (int i1 = 0; i1 < count; i1++) {
                    map += " ";
                }
                System.out.print(map);
            }
            System.out.println();
        }
    }


    public static void printMap(Long[] maps) {
        for (int j = 0; j < HexagonConst.height; j++) {
            for (int i = 0; i < HexagonConst.width; i++) {
                int index = j * HexagonConst.width + i;
                String map = index + "-" + maps[index] + "";
                int count = 10 - map.length();
                for (int i1 = 0; i1 < count; i1++) {
                    map += " ";
                }
                System.out.print(map);
            }
            System.out.println();
        }
    }
}
