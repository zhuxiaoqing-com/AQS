package com.example.common.line2DCheck;

import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 10:12
 * @Description:
 */
public class Line2DCheckTest {
    @Test
    public void test1() {
        // 严格相交
        Line line1 = new Line(new Point(0, 2), new Point(1, 0));
        Line line2 = new Line(new Point(0, 0), new Point(1, 2));
        System.out.println("严格相交 result：" + Line2DCheck.intersection2(line1, line2));

        // 端点相交
        line1 = new Line(new Point(1, 1), new Point(2, 2));
        line2 = new Line(new Point(2, 2), new Point(2, 1));
        System.out.println("端点相交 result：" + Line2DCheck.intersection2(line1, line2));
        // 两条线段平行
        line1 = new Line(new Point(1, 2), new Point(2, 2));
        line2 = new Line(new Point(1, 1), new Point(2, 1));
        System.out.println("两条线段平行 result：" + Line2DCheck.intersection2(line1, line2));

        // 两条线段 不平行但是 不相交
        line1 = new Line(new Point(1, 2), new Point(2, 1));
        line2 = new Line(new Point(3, 2), new Point(4, 3));
        System.out.println("两条线段 不平行但是 不相交 result：" + Line2DCheck.intersection2(line1, line2));

        // 两条线段 相等
        line1 = new Line(new Point(1, 2), new Point(2, 1));
        line2 = new Line(new Point(1, 2), new Point(2, 1));
        System.out.println("两条线段 相等 result：" + Line2DCheck.intersection2(line1, line2));
    }

}
