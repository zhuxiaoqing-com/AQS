package com.example.design_mode.TowerAOI;

import java.util.*;

public class TowerAOI {
    /**
     * 默认范围(是以灯塔为基础计量单位的 半径，半径1的话 直径就是2  2就可以覆盖4个灯塔)
     * 计算方式 默认以当前灯塔为 0,0   然后
     * startX = x - range;
     * startY = y - range;
     * endY = y + range;
     * endY = y + range;
     * <p>
     * (startX, startY)  (endX, endY)
     */
    private int range = 1;

    /**
     * 原点坐标 x
     */
    private int originX = 0;

    /**
     * 原点坐标 y
     */
    private int originY = 0;

    /**
     * 地图宽度
     */
    private float width;

    /**
     * 地图高度
     */
    private float height;


    /**
     * 灯塔的宽度
     */
    private float towerWidth = 12;

    /**
     * 灯塔的高度
     */
    private float towerHeight = 12;

    /**
     * X最大值
     */
    private int maxX;

    /**
     * Y 最大值
     */
    private int maxY;

    /**
     * 灯塔数组
     */
    private Tower[][] towers;

    /**
     * 事件监听者
     */
    // private AOIEventListener listener;


    /**
     * 创建一个AOI对象
     *
     * @param width  地图宽度
     * @param height 地图高度
     * @param range  视野范围最大值
     */
    public TowerAOI(int originX, int originY, int width, int height, int range) {
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.range = range;
        init();
    }

    /**
     * 初始化地图中的灯塔
     */
    private void init() {
        this.maxX = (int) Math.ceil(width / towerWidth);
        this.maxY = (int) Math.ceil(height / towerHeight);
        this.towers = new Tower[maxX + 1][maxY + 1];

        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                towers[i][j] = new Tower();
            }
        }
    }


    private int convertPoint(Vector2f vector2f) {
        float rx = vector2f.getX() - originX; // rx 是距离原点距离的坐标
        float ry = vector2f.getY() - originX;
        int px = (int) Math.ceil(rx / towerWidth);// px 是以灯塔等基础单位的坐标 1单位就是一个灯塔单位
        int py = (int) Math.ceil(ry / towerWidth);
        // 这里因为要转递两个参数所以就拼接起来了
        return px < 0 || px > maxX || py < 0 || py > maxY ? -1 : BitUtil.combineIntToTwo(px, py);
    }

    /**
     * 获取 v2 的 range 半径 的灯塔
     */
    private List<Integer> convertPoint(Vector2f v2, int range) {
        float rx = v2.getX() - originX;
        float ry = v2.getY() - originY;
        int px = (int) Math.ceil(rx / towerWidth);
        int py = (int) Math.ceil(ry / towerHeight);

        int startX = Math.max(0, px - range);
        int startY = Math.max(0, py - range);

        int endX = Math.min(maxX, px + range);
        int endY = Math.min(maxY, py + range);

        List<Integer> points = new ArrayList<>();

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                points.add(BitUtil.combineIntToTwo(i, j));
            }
        }
        return points;
    }

    /**
     * 通过pos和range获取对象列表
     */
    private Set<Long> findObjectIds(Vector2f v2, int range) {
        List<Integer> points = convertPoint(v2, range);
        Set<Long> ids = new HashSet<>();
        for (int point : points) {
            int x = BitUtil.separateIntHigh16Bit(point);
            int y = BitUtil.separateIntLow16Bit(point);
            Tower tower = towers[x][y];
            ids.addAll(tower.findAllObjectIds());
        }
        return ids;
    }

    /**
     * 通过pos和range获取对象列表
     */
    public Set<Long> findObjectIds(Vector2f v2) {
        return findObjectIds(v2, range);
    }

    /**
     * 当前位置观察者
     */
    public Set<Long> findWatcherIds(Vector2f v2) {
        int point = convertPoint(v2);
        if (point == -1) {
            return Collections.emptySet();
        }
        int x = BitUtil.separateIntHigh16Bit(point);
        int y = BitUtil.separateIntLow16Bit(point);
        Tower tower = towers[x][y];
        return tower.findAllWatcherIds();
    }


}



























