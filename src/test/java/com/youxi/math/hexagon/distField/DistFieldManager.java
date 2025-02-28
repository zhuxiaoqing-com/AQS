package com.youxi.math.hexagon.distField;


import com.youxi.db.test.KV;
import com.youxi.math.hexagon.HexagonConst;
import com.youxi.math.hexagon.HexagonManager;
import com.youxi.math.hexagon.IObstacleCheck;
import com.youxi.math.hexagon.beans.Cell;
import io.swagger.models.auth.In;

import java.util.*;
import java.util.function.Predicate;

public class DistFieldManager {

    DistFieldObj distFieldObj;
    public int startIndex;

    public DistFieldManager(int startIndex) {
        this.startIndex = startIndex;
        this.distFieldObj = new DistFieldObj(startIndex, 0);
    }

    public DistFieldObj getAndCheckDistFieldObj(HexagonManager hexagonManager, IObstacleCheck iObstacleCheck, int attackRange) {

        // 重置 全部填上-1
        Arrays.fill(distFieldObj.getDistField(), -1);
        Arrays.fill(distFieldObj.getEnemyCanAttack(), -1L);


        distFieldObj.setStartIndex(startIndex);
        Long[] enemyCanAttack = distFieldObj.getEnemyCanAttack();
        // 填充距离场数据
        DistFieldBFS.calcDistField(distFieldObj, iObstacleCheck, hexagonManager);

        // 这样处理就可以知道我可不可以打到他(被阻挡包围了就会忽略他)
        // 重新填充enemy数据  用攻击距离获取我可以攻击到这个目标的范围 并且将其填充上我可以攻击到的unique;
        Long[] obstacles = iObstacleCheck.getObstacles();
        for (int i = 0; i < obstacles.length; i++) {
            Long obstacle = obstacles[i];
            if (obstacle <= 0) {
                continue;
            }
            // obstacle == 自己的话 也需要排除;比如我在索敌
            Cell cell = hexagonManager.getCell(i);
            List<Cell> cells = hexagonManager.coordinateRange(cell.getIndex(), attackRange);
            for (Cell rangeCell : cells) {
                // 排除自己
                if (rangeCell.getIndex() == cell.getIndex()) {
                    continue;
                }
                enemyCanAttack[rangeCell.getIndex()] = obstacle;
            }
        }
        return distFieldObj;
    }


    /**
     * 查找最近的敌人
     */
    public KV<Long, Integer> findNearestEnemy(DistFieldObj distFieldObj, Predicate<Long> filter) {
        Long[] enemyCanAttack = distFieldObj.getEnemyCanAttack();
        int[] distField = distFieldObj.getDistField();

        int minRange = Integer.MAX_VALUE;
        Long minUniqId = 0L;
        int minIndex = -1;
        for (int i = 0; i < enemyCanAttack.length; i++) {
            Long uniqueId = enemyCanAttack[i];
            if (uniqueId == null || uniqueId <= 0) {
                continue;
            }
            if (!filter.test(uniqueId)) {
                continue;
            }
            // 查看距离
            if (distField[i] > 0 && distField[i] < minRange) {
                minRange = distField[i];
                minUniqId = uniqueId;
                minIndex = i;
            }
        }

        return new KV<>(minUniqId, minIndex);
    }

    /**
     * 查找所有可以攻击到的敌人
     */
    public Set<Long> findNearestEnemyList(DistFieldObj distFieldObj, Predicate<Long> filter) {
        Long[] enemyCanAttack = distFieldObj.getEnemyCanAttack();
        int[] distField = distFieldObj.getDistField();

        Set<Long> list = new HashSet<>();
        for (int i = 0; i < enemyCanAttack.length; i++) {
            Long uniqueId = enemyCanAttack[i];
            if (uniqueId == null || uniqueId <= 0) {
                continue;
            }

            if (!filter.test(uniqueId)) {
                continue;
            }
            // 查看距离
            if (distField[i] <= 0) {
                continue;
            }
            list.add(uniqueId);
        }

        return list;
    }

    public List<Integer> findPath(int endIndex, HexagonManager hexagonManager) {
        return DistFieldBFS.findPathByDistField(distFieldObj, endIndex, hexagonManager);
    }

    public static void main(String[] args) {
        HexagonManager instance = HexagonManager.getInstance();
        IObstacleCheck iObstacleCheck = new IObstacleCheck() {
            Long[] obstacles = new Long[HexagonConst.HEX_SIZE];

            {
                Arrays.fill(obstacles, -1L);
                for (int i = 0; i < obstacles.length; i++) {
                    if (i == 31
                            || i == 22
                            || i == 34
                    ) {
                        obstacles[i] = (long) i * 1000;
                    }
                }
            }

            @Override
            public boolean checkObstacle(int index) {
                return obstacles[index] > 0;
            }

            @Override
            public Long[] getObstacles() {
                return obstacles;
            }
        };
        DistFieldManager distFieldManager = new DistFieldManager(30);
        DistFieldObj distFieldObj = distFieldManager.getAndCheckDistFieldObj(instance, iObstacleCheck, 1);

        System.out.println("getDistField()");
        DistFieldBFS.printMap(distFieldObj.getDistField());
        System.out.println("getEnemyCanAttack()");
        DistFieldBFS.printMap(distFieldObj.getEnemyCanAttack());

        KV<Long, Integer> nearestEnemy = distFieldManager.findNearestEnemy(distFieldObj, a -> true);
        System.out.println("nearestEnemy " + nearestEnemy);
        Set<Long> nearestEnemyList = distFieldManager.findNearestEnemyList(distFieldObj, a -> true);
        System.out.println("nearestEnemyList " + nearestEnemyList);

        List<Integer> path = distFieldManager.findPath(nearestEnemy.getV(), instance);
        System.out.println("path " + path);

    }
}
