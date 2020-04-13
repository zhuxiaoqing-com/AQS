package com.example.common.line2DCheck;

import java.awt.geom.Line2D;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 10:12
 * @Description:
 */
public class Line2DCheck {
    // 首先

    public static boolean intersection(Line2 l1, Line2 l2) {

        //快速排斥实验
        if ((l1.x1 > l1.x2 ? l1.x1 : l1.x2) < (l2.x1 < l2.x2 ? l2.x1 : l2.x2) ||
                (l1.y1 > l1.y2 ? l1.y1 : l1.y2) < (l2.y1 < l2.y2 ? l2.y1 : l2.y2) ||
                (l2.x1 > l2.x2 ? l2.x1 : l2.x2) < (l1.x1 < l1.x2 ? l1.x1 : l1.x2) ||
                (l2.y1 > l2.y2 ? l2.y1 : l2.y2) < (l1.y1 < l1.y2 ? l1.y1 : l1.y2)) {
            return false;
        }
        //跨立实验
        if ((((l1.x1 - l2.x1) * (l2.y2 - l2.y1) - (l1.y1 - l2.y1) * (l2.x2 - l2.x1)) *
                ((l1.x2 - l2.x1) * (l2.y2 - l2.y1) - (l1.y2 - l2.y1) * (l2.x2 - l2.x1))) > 0 ||
                (((l2.x1 - l1.x1) * (l1.y2 - l1.y1) - (l2.y1 - l1.y1) * (l1.x2 - l1.x1)) *
                        ((l2.x2 - l1.x1) * (l1.y2 - l1.y1) - (l2.y2 - l1.y1) * (l1.x2 - l1.x1))) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 两条线段有且仅有一个公共点，且这个点不是任何一条线段的端点时，称这两条线段是严格相交的。
     * <p>
     * 如果两线段相交，则两线段必然相互跨立对方。
     * 若P1P2跨立Q1Q2 ，则矢量 ( P1 - Q1 ) 和( P2 - Q1 )位于矢量( Q2 - Q1 ) 的两侧，即( P1 - Q1 ) × ( Q2 - Q1 ) * ( P2 - Q1 ) × ( Q2 - Q1 ) < 0。
     * 上式可改写成( P1 - Q1 ) × ( Q2 - Q1 ) * ( Q2 - Q1 ) × ( P2 - Q1 ) > 0。
     * <p>
     * 当 ( P1 - Q1 ) × ( Q2 - Q1 ) = 0 时，说明 ( P1 - Q1 ) 和 ( Q2 - Q1 )共线，
     * 但是因为已经通过快速排斥试验，所以 P1 一定在线段 Q1Q2上；
     * 同理，( Q2 - Q1 ) ×(P2 - Q1 ) = 0 说明 P2 一定在线段 Q1Q2上。  如果两个线段共线，说明两个线段只有端点相交，就是不严格相交。如果不严格相交不算相交的话
     * 那么其实就不需要 快速排斥实验 直接进行 跨立实验 就好了
     * <p>
     * 所以判断P1P2跨立Q1Q2的依据是：( P1 - Q1 ) × ( Q2 - Q1 ) * ( Q2 - Q1 ) × ( P2 - Q1 ) >= 0。
     * 同理判断Q1Q2跨立P1P2的依据是：( Q1 - P1 ) × ( P2 - P1 ) * ( P2 - P1 ) × ( Q2 - P1 ) >= 0。
     *
     * @param P
     * @param Q
     * @return
     */
    public static boolean intersection2(Line P, Line Q) {
        /*
        跨立实验  设矢量 P = (x1, y1)，Q = ( x2, y2 )，则矢量叉积定义为：P × Q = x1*y2 - x2*y1
        (P1-Q1)x(Q2-Q1)*(P2-Q1)x(Q2-Q1) < 0   &&
        (Q1-P1)x(P2-P1)*(Q2-P1)x(P2-P1) < 0
         */
        if (vectorCross(pointSubtract(P.getPoint1(), Q.getPoint1()), pointSubtract(P.getPoint2(), Q.getPoint1())) *
                vectorCross(pointSubtract(P.getPoint2(), Q.getPoint1()), pointSubtract(Q.getPoint2(), Q.getPoint1())) < 0 &&
                vectorCross(pointSubtract(Q.getPoint1(), P.getPoint1()), pointSubtract(P.getPoint2(), P.getPoint1())) *
                        vectorCross(pointSubtract(Q.getPoint2(), P.getPoint1()), pointSubtract(P.getPoint2(), P.getPoint1())) < 0) {
            return true;
        }
        return false;
    }


    //  x1*y2 - x2*y1
    private static int vectorCross(Point point1, Point point2) {
        return point1.getX() * point2.getY() - point2.getX() * point1.getY();
    }

    private static Point pointSubtract(Point point1, Point point2) {
        int newX = point1.getX() - point2.getX();
        int newY = point1.getY() - point2.getY();
        return new Point(newX, newY);
    }


    /*
     计算矢量叉积与直线和线段相关算法的核心部分
     设矢量 P=(x1,y1), Q=(x2,y2), 则矢量叉积 定义为 ：
     PxQ = x1*y2 - x2*y1
     显然有性质 PxQ= -(QxP) 和 Px(-Q) = -(PxQ);

     叉积的一个非常重要性质是可以通过它的符号判断两矢量相互之间的顺逆时针关系：
     　　若 P × Q > 0 , 则 P 在 Q 的顺时针方向。
     　　若 P × Q < 0 , 则 P 在 Q 的逆时针方向。
     　　若 P × Q = 0 , 则 P 与 Q 共线，但可能同向也可能反向。


     Q1-Q2
     P1-P2
     如果两条线段相交，则两线段必然互相跨越对方。
     矢量 ( P1 - Q1 ) 和( P2 - Q1 )位于矢量( Q2 - Q1 ) 的两侧
     矢量 ( Q1 - P1 ) 和( Q2 - P1 )位于矢量( P2 - P1 ) 的两侧
     x 是矢量相乘 ；* 是正常相乘
     (Q1-P1)x(P2-P1)*(Q2-P1)x(P2-P1) < 0  &&
     (P1-Q1)x(Q2-Q1)*(P2-Q1)x(Q2-Q1) < 0
     */

}
