package com.example.common.line2DCheck;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 09:39
 * @Description: 线段
 */
public class Line {
    private Point point1;
    private Point point2;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Line2 toLine2() {
        return new Line2(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }
}
