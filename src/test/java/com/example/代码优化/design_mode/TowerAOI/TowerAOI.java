package com.example.代码优化.design_mode.TowerAOI;

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


    /**
     * 添加一个对象，将对象放入坐标对应的灯塔里面，向灯塔中的观察者触发一个 新对象添加事件
     */
    public void addObject(long obj, Vector2f v2) {
        int point = convertPoint(v2);
        if (point == -1) {
            return;
        }
        int x = BitUtil.separateIntHigh16Bit(point);
        int y = BitUtil.separateIntLow16Bit(point);
        Tower tower = towers[x][y];
        addObject(tower, obj, tower.findAllWatcherIds());
    }

    /**
     * 添加一个对象，将对象放入坐标对应的灯塔里面，向灯塔中的观察者触发一个 新对象添加事件
     */
    private void addObject(Tower tower, long obj, Set<Long> watcherIds) {
        boolean result = tower.addObjectId(obj);
        if (!result) {
            //GameLog.sys.warn("添加对象失败！objId={}, name={}", obj.getId(), obj.getName());
        }
        //listener.onAdd(obj, watcherIds);
    }

    /**
     * 删除一个对象，将对象从坐标对应的灯塔里面移除，向灯塔中的观察者触发一个 对象移除事件
     */
    public void removeObject(long obj, Vector2f v2) {
        int point = convertPoint(v2);
        if (point == -1) {
            return;
        }
        int x = BitUtil.separateIntHigh16Bit(point);
        int y = BitUtil.separateIntLow16Bit(point);
        Tower tower = towers[x][y];
        removeObject(tower, obj, tower.findAllWatcherIds());
    }


    /**
     * 删除一个对象，将对象从坐标对应的灯塔里面移除，向灯塔中的观察者触发一个 对象移除事件
     */
    private void removeObject(Tower tower, long obj, Set<Long> watcherIds) {
        boolean result = tower.removeObjectId(obj);
        if (!result) {
            //GameLog.sys.error("移除对象失败！objId={}, name={}", obj.getId(), obj.getName());
        }
        //listener.onRemove(obj, watcherIds);
    }



    /**
     * 更新一个游戏对象，该方法会触发一个update事件，此事件通知其他观察者，我消失或者进入了你的视野</br>
     * 注意：该方法的触发的事件不包括自己
     */
    public void updateObject(long obj, Vector2f oldV2, Vector2f newV2) {
        int oldTP = convertPoint(oldV2);
        int newTP = convertPoint(newV2);

        if (oldTP == -1) {
            if (newTP >= 0) {
                addObject(obj, newV2);
            }
            return;
        }
        if (newTP == -1) {
            removeObject(obj, oldV2);
            return;
        }
        if (oldTP == newTP) {
            return;
        }

        int oldX = BitUtil.separateIntHigh16Bit(oldTP);
        int oldY = BitUtil.separateIntLow16Bit(oldTP);
        Tower oldTower = towers[oldX][oldY];
        int newX = BitUtil.separateIntHigh16Bit(newTP);
        int newY = BitUtil.separateIntLow16Bit(newTP);
        Tower newTower = towers[newX][newY];

        Set<Long> oldWatcherIds = oldTower.findAllWatcherIds();
        Set<Long> newWatcherIds = newTower.findAllWatcherIds();

        Set<Long> removeWatcherIds = new HashSet<>(oldWatcherIds);
        Set<Long> addWatcherIds = new HashSet<>(newWatcherIds);

        // 从旧的灯塔里面删除新的灯塔里面的观察者，通知这些观察者玩家离开这个灯塔了
        removeWatcherIds.removeAll(newWatcherIds);
        removeObject(oldTower, obj, removeWatcherIds);

        // 从新的灯塔里面删除旧的观察者，通知这些观察这玩家进入这个灯塔了。
        addWatcherIds.removeAll(oldWatcherIds);
        addObject(newTower, obj, addWatcherIds);
    }


    /**
     * 添加观察者
     */
    public void addWatcher(long obj, Vector2f v2) {
        addWatcher(obj, v2, range);
    }

    /**
     * 添加观察者
     */
    private void addWatcher(long obj, Vector2f v2, int range) {
        List<Integer> points = convertPoint(v2, range);
        addWatcher(obj, points);
    }

    /**
     * 添加观察者
     */
    private void addWatcher(long obj, List<Integer> points) {
        for (int point : points) {
            int x = BitUtil.separateIntHigh16Bit(point);
            int y = BitUtil.separateIntLow16Bit(point);
            Tower tower = towers[x][y];
            addWatcher(tower, obj, tower.findAllObjectIds());
        }
    }

    /**
     * 添加观察者
     */
    private void addWatcher(Tower tower, long watcher, Set<Long> objIds) {
        boolean result = tower.addWatcherId(watcher);
        if (!result) {
            //GameLog.sys.warn("添加观察者失败！watcherId={}, name={}", watcher.getId(), watcher.getName());
        }
        //listener.watcherEnter(watcher, objIds);
    }

    /**
     * 移除观察者
     */
    public void removeWatcher(long obj, Vector2f v2) {
        removeWatcher(obj, v2, range);
    }

    /**
     * 移除观察者
     */
    private void removeWatcher(long obj, Vector2f v2, int range) {
        List<Integer> points = convertPoint(v2, range);
        removeWatcher(obj, points);
    }


    /**
     * 移除观察者
     */
    private void removeWatcher(long obj, List<Integer> points) {
        for (int point : points) {
            int x = BitUtil.separateIntHigh16Bit(point);
            int y = BitUtil.separateIntLow16Bit(point);
            Tower tower = towers[x][y];
            removeWatcher(tower, obj, tower.findAllObjectIds());
        }
    }

    /**
     * 移除观察者
     */
    private void removeWatcher(Tower tower, long watcher, Set<Long> objIds) {
        boolean result = tower.removeWatcherId(watcher);
        if (!result) {
            //GameLog.sys.error("移除观察者失败！watcherId={}, name={}", watcher.getId(), watcher.getName());
        }
        //listener.watcherOuter(watcher, objIds);
    }

    /**
     * 更新观察者，该方法会触发一个updateWatcher事件，此事件告诉观察者本身，我的视野游戏哪些变化
     */
    public void updateWatcher(long obj, Vector2f oldV2, Vector2f newV2) {
        updateWatcher(obj, oldV2, newV2, range);
    }

    /**
     * 更新观察者，该方法会触发一个updateWatcher事件，此事件告诉观察者本身，我的视野游戏哪些变化
     */
    private void updateWatcher(long obj, Vector2f oldV2, Vector2f newV2, int range) {
        List<Integer> oldPoints = convertPoint(oldV2, range);
        List<Integer> newPoints = convertPoint(newV2, range);

        List<Integer> equalsPoints = new ArrayList<>();
        for (int oldPoint : oldPoints) {
            for (int newPoint : newPoints) {
                if (oldPoint == newPoint) {
                    equalsPoints.add(oldPoint);
                }
            }
        }
        oldPoints.removeAll(equalsPoints);
        removeWatcher(obj, oldPoints);
        //
        newPoints.removeAll(equalsPoints);
        addWatcher(obj, newPoints);
    }

    /**
     * 清空
     */
    public void clear() {
        for (Tower[] towerArray : towers) {
            for (Tower tower : towerArray) {
                tower.clear();
            }
        }
    }

}



























