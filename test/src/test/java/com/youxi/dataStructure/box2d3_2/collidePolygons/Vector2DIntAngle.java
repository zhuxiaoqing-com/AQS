package com.youxi.dataStructure.box2d3_2.collidePolygons;

/**
 * @author zhuxiaoqing
 * @Description: Vector2DIntAngle
 * @Date 2025/10/14 19:47 角度定点数版本
 **/
public class Vector2DIntAngle {
    public static final int SCALE = 10000; // 定点放大倍数
    private static final int[] COS_TABLE = new int[360];
    private static final int[] SIN_TABLE = new int[360];
    private static final int[] TAN = new int[46]; // 弧度表

    static {
        // 生成查表 0~359°
        for (int deg = 0; deg < 360; deg++) {
            double rad = Math.toRadians(deg);
            COS_TABLE[deg] = (int)Math.round(Math.cos(rad) * SCALE);
            SIN_TABLE[deg] = (int) Math.round(Math.sin(rad) * SCALE);
            if (deg <= 45) {
                TAN[deg] = (int) Math.round(Math.tan(rad) * SCALE);
            }
        }
    }

    /**
     * 返回向量 (dx, dy) 相对于 X 轴的夹角 0~359°
     * 全部整数运算，帧同步安全
     * dx = v2.x - v1.x
     * dy = v2.y - v1.y
     */
    public static int angle(int dx, int dy) {
        if (dx == 0 && dy == 0) return 0; // 零向量

        int bestDeg = 0;
        long bestScore = Long.MIN_VALUE;

        int tan = dy * SCALE / dx;


        // O(360) 查表：用点积评分
        for (int deg = 0; deg < 360; deg++) {
            long score = (long)dx * COS_TABLE[deg] + (long)dy * SIN_TABLE[deg];
            if (score > bestScore) {
                bestScore = score;
                bestDeg = deg;
            }
        }
        return bestDeg;
    }

    // 测试
    public static void main(String[] args) {
        System.out.println(angle(1, 0));   // 0°
        System.out.println(angle(0, 1));   // 90°
        System.out.println(angle(-1, 0));  // 180°
        System.out.println(angle(0, -1));  // 270°
        System.out.println(angle(1, 1));   // 45°
        System.out.println(angle(-1, 1));  // 135°
    }
}
