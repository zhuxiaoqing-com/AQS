package com.example.common.line2DCheck;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 12:30
 * @Description:
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Point subtract(Point point) {
        int newX  = this.x - point.x;
        int newY  = this.y - point.y;
        return new Point(newX, newY);
    }
}
