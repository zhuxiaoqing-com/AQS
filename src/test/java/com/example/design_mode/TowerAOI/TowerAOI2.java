package com.example.design_mode.TowerAOI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TowerAOI2 {
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
    private AOIEventListener listener;


    /**
     * 创建一个AOI对象
     *
     * @param width  地图宽度
     * @param height 地图高度
     * @param range  视野范围最大值
     */
    public TowerAOI2(int originX, int originY, int width, int height, int range) {
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.range = range;
        listener = new AOIEventListener();
        init();
    }

    /**
     * 初始化灯塔
     */
    private void init() {
        maxX = (int) Math.ceil(width / towerWidth);
        maxY = (int) Math.ceil(height / towerHeight);

        towers = new Tower[maxX + 1][maxY + 1];

        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                towers[i][j] = new Tower();
            }
        }

    }

    private int convertTowerPoint(Vector2f v2) {
        float ox = v2.getX() - originX;
        float oy = v2.getY() - originY;

        int px = (int) Math.ceil(ox / towerWidth);
        int py = (int) Math.ceil(oy / towerHeight);

        return px < 0 || px > maxX || py < 0 || py > maxY ? -1 : BitUtil.combineIntToTwo(px, py);
    }


    private List<Integer> convertTowerPointsByRange(Vector2f v2, int range) {
        float ox = v2.getX() - originX;
        float oy = v2.getY() - originY;

        int px = (int) Math.ceil(ox / towerWidth);
        int py = (int) Math.ceil(oy / towerHeight);

        int minPx = Math.max(0, px + range);
        int maxPx = Math.min(maxX, px + range);

        int minPy = Math.max(0, py + range);
        int maxPy = Math.min(maxX, py + range);

        List<Integer> list = new ArrayList<>();

        for (int i = minPx; i <= maxPx; i++) {
            for (int j = minPy; j <= maxPy; j++) {
                list.add(BitUtil.combineIntToTwo(px, py));
            }
        }
        return list;
    }

    /**
     * 往灯塔里面添加对象
     */
    public void addObject(long objId, Vector2f v2) {
        int xyPoint = convertTowerPoint(v2);
        if (xyPoint < 0) {
            return;
        }
        int px = BitUtil.separateIntHigh16Bit(xyPoint);
        int py = BitUtil.separateIntLow16Bit(xyPoint);

        Tower tower = towers[px][py];
        addObject(tower, objId, tower.findAllWatcherIds());
    }

    private void addObject(Tower tower, long objId, Set<Long> allWatcherIds) {
        boolean result = tower.addObjectId(objId);
        if (!result) {
            System.out.println("添加对象失败");
        }
        // 通知给所有的观察者 该灯塔里面加对象了
        //。。。
    }

    /**
     * 往灯塔里面删除对象
     */
    public void removeObject(long objId, Vector2f v2) {
        int xyPoint = convertTowerPoint(v2);
        if (xyPoint < 0) {
            return;
        }
        int px = BitUtil.separateIntHigh16Bit(xyPoint);
        int py = BitUtil.separateIntLow16Bit(xyPoint);

        Tower tower = towers[px][py];
        removeObject(tower, objId, tower.findAllWatcherIds());
    }

    private void removeObject(Tower tower, long objId, Set<Long> allWatcherIds) {
        boolean result = tower.removeObjectId(objId);
        if (!result) {
            System.out.println("删除对象失败");
        }
        // 通知给所有的观察者 该灯塔里面的这个对象被删除了
    }

    /**
     * 添加观察者 到这个坐标 range 范围内的所有灯塔
     * <p>
     * 添加对象到灯塔以后，如果是玩家还需要将其加入观察者，这样子他才能有视野。
     * 视野是 以本身灯塔为圆点，range 为半径，附近所有的灯塔，他都要观察
     *
     * @param objId
     * @param v2
     */
    public void addWatcherObj(long objId, Vector2f v2) {
        List<Integer> points = convertTowerPointsByRange(v2, range);
        for (Integer point : points) {
            int px = BitUtil.separateIntHigh16Bit(point);
            int py = BitUtil.separateIntLow16Bit(point);
            Tower tower = towers[px][py];
            addWatcherObj(tower, objId, tower.findAllObjectIds());
        }
    }

    private void addWatcherObj(Tower tower, long objId, Set<Long> allObjectIds) {
        // 将该玩家加入到 该灯塔的观察者里面
        boolean result = tower.addWatcherId(objId);
        if (!result) {
            System.out.println("添加观察者失败");
        }
        // objId 将 allObjectIds 视为新加入可见
    }

    /**
     * 删除观察者
     * <p>
     * 玩家离开地图的时候删除观察者
     *
     * @param objId
     * @param v2
     */
    public void removeWatcherObj(long objId, Vector2f v2) {
        List<Integer> points = convertTowerPointsByRange(v2, range);
        for (Integer point : points) {
            int px = BitUtil.separateIntHigh16Bit(point);
            int py = BitUtil.separateIntLow16Bit(point);

            Tower tower = towers[px][py];
            removeWatcherObj(tower, objId, tower.findAllObjectIds());
        }
    }

    private void removeWatcherObj(Tower tower, long objId, Set<Long> allObjectIds) {
        // 将该玩家加入到 该灯塔的观察者里面
        boolean result = tower.removeObjectId(objId);
        if (!result) {
            System.out.println("删除观察者失败");
        }
        // objId 将 allObjectIds 视为不可见
    }

    /**
     * 更新 对象
     * <p>
     * 对象移动等
     *
     * @param objId
     * @param oldV2
     * @param newV2
     */
    public void updateObject(long objId, Vector2f oldV2, Vector2f newV2) {
        int oldXyPoint = convertTowerPoint(oldV2);
        int nowXyPoint = convertTowerPoint(newV2);
        if (oldXyPoint < 0 && nowXyPoint > -1) {
            // 如果该玩家之前没有在任何灯塔  现在有灯塔
            addObject(objId, newV2);
            return;
        }
        if (nowXyPoint < 0 && oldXyPoint > -1) {
            // 如果该玩家现在的坐标不属于任何灯塔  现在有灯塔
            removeObject(objId, oldV2);
            return;
        }

        // 移除旧的对象 增加新的对象
        int oldPX = BitUtil.separateIntHigh16Bit(oldXyPoint);
        int oldPY = BitUtil.separateIntLow16Bit(oldXyPoint);

        int nowPX = BitUtil.separateIntHigh16Bit(nowXyPoint);
        int nowPY = BitUtil.separateIntLow16Bit(nowXyPoint);

        // 需要告诉新的灯塔观察者 灯塔里面多了一个新对象
        // 需要告诉旧的灯塔观察者 灯塔里面少了一个对象
        Tower oldTower = towers[oldPX][oldPY];
        Tower nowTower = towers[nowPX][nowPY];

        Set<Long> removeSet = new HashSet<>(oldTower.findAllObjectIds());
        Set<Long> addSet = new HashSet<>(nowTower.findAllObjectIds());

        // 将旧的灯塔里面的新的对象删除 只通知视野有旧灯塔，视野没有新灯塔 的玩家
        removeSet.removeAll(nowTower.findAllObjectIds());
        removeObject(oldTower, objId, removeSet);

        // 将新的灯塔里面里面旧的灯塔里面都有的观察者删除
        addSet.removeAll(oldTower.findAllObjectIds());
        //通知 addSet 里面所有的玩家，该玩家进入其视野了
        addObject(oldTower, objId, addSet);
    }

    /**
     * 更新 该玩家的 视野范围
     *
     * @param objId
     * @param oldV2
     * @param newV2
     */
    public void updateWatcher(long objId, Vector2f oldV2, Vector2f newV2) {
        // 旧的视野范围
        List<Integer> oldWatcherRange = convertTowerPointsByRange(oldV2, range);
        // 新的视野范围
        List<Integer> nowWatcherRange = convertTowerPointsByRange(newV2, range);

        List<Integer> tempOldWatcherRange = new ArrayList<>(oldWatcherRange);
        List<Integer> tempNowWatcherRange = new ArrayList<>(nowWatcherRange);

        // 去除相交的视野 这些视野移动前和移动后都不动
        tempOldWatcherRange.removeAll(nowWatcherRange);
        tempNowWatcherRange.removeAll(oldWatcherRange);


        for (Integer towerXY : tempOldWatcherRange) {
            // 移除观察者
            int px = BitUtil.separateIntHigh16Bit(towerXY);
            int py = BitUtil.separateIntLow16Bit(towerXY);

            Tower tower = towers[px][py];
            removeWatcherObj(tower, objId, tower.findAllObjectIds());
        }

        for (Integer towerXY : tempNowWatcherRange) {
            // 添加新的观察者
            int px = BitUtil.separateIntHigh16Bit(towerXY);
            int py = BitUtil.separateIntLow16Bit(towerXY);

            Tower tower = towers[px][py];
            addWatcherObj(tower, objId, tower.findAllObjectIds());
        }
    }

}



























