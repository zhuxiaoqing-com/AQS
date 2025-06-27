package com.youxi.math;

import com.example.demo1.util.vo.Vector2f;
import com.youxi.entity.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://jdreamheart.com/wiki/knowledge/math/bezier_curve/">...</a>
 */
public class SeibelCurve {
    public Position seibelCurve(float timeRatio, Position a, Position b, Position c) {
        Position m1 = b.sub(a).multiply(timeRatio).add(a);
        Position m2 = c.sub(b).multiply(timeRatio).add(b);
        return m2.sub(m1).multiply(timeRatio).add(m1);
    }


    /**
     * 递归赛贝尔曲线
     */
    public Position bezier1(float t, List<Position> pList) {
        if(pList.isEmpty()) {
            return null;
        }
        if(pList.size() == 1) {
            return pList.get(0);
        }
        ArrayList<Position> newPList = new ArrayList<>();

        for (int i = 0; i < pList.size() - 1; i++) {
            //B(t)=P0+(P1−P0)t=(1−t)P0+tP1
            Position p = pList.get(i).multiply(1f - t).add(pList.get(i + 1).multiply(t));
            newPList.add(p);
        }
        return bezier1(t, newPList);
    }

    /**
     * 直接根据n阶赛贝尔曲线函数来实现
     */
    // 获取阶乘结果
    public int factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public Position bezier2(float t, List<Position> pList) {
        if(pList.isEmpty()) {
            return null;
        }
        if(pList.size() == 1) {
            return pList.get(0);
        }
        Position result = new Position(0,0,0);

        int n = pList.size() - 1;
        for (int i = 0; i <= n; i++) {
            float coefficient = (float) factorial(n) / (float) (factorial(i) * factorial(n - i));
            Position multiply = pList.get(i).multiply((float) (coefficient * Math.pow(1 - t, n - i) * Math.pow(t, i)));
            result = result.add(multiply);
        }

        return result;
    }


    public Vector2f bezier11(float t, List<Vector2f> pList) {
        if(pList.isEmpty()) {
            return null;
        }
        if(pList.size() == 1) {
            return pList.get(0);
        }
        ArrayList<Vector2f> newPList = new ArrayList<>();

        for (int i = 0; i < pList.size() - 1; i++) {
            Vector2f p = pList.get(i).multiply(1f - t).add(pList.get(i + 1).multiply(t));
            newPList.add(p);
        }
        return bezier11(t, newPList);
    }


    public Vector2f bezier22(float t, List<Vector2f> pList) {
        if(pList.isEmpty()) {
            return null;
        }
        if(pList.size() == 1) {
            return pList.get(0);
        }
        Vector2f result = new Vector2f(0,0);

        int n = pList.size() - 1;
        for (int i = 0; i <= n; i++) {
            float coefficient = (float) factorial(n) / (float) (factorial(i) * factorial(n - i));
            Vector2f multiply = pList.get(i).multiply((float) (coefficient * Math.pow(1 - t, n - i) * Math.pow(t, i)));
            result = result.add(multiply);
        }

        return result;
    }

    @Test
    public void test() {
        ArrayList<Position> pList = new ArrayList<>();
        pList.add(new Position(10000,0,10000));
        pList.add(new Position(20000,0,20000));
        pList.add(new Position(30000,0,30000));
        pList.add(new Position(40000,0,40000));
        pList.add(new Position(50000,0,50000));

        int index = 10;

        for (int i = 0; i < index; i++) {
            Position position1 = bezier1(i * 0.1f, pList);
            Position position2 = bezier2(i * 0.1f, pList);
            System.out.println(position1);
            System.out.println(position2);

        }
    }

    @Test
    public void test2() {
        ArrayList<Vector2f> pList = new ArrayList<>();
        pList.add(new Vector2f(10000, 10000));
        pList.add(new Vector2f(20000, 20000));
        pList.add(new Vector2f(30000, 30000));
        pList.add(new Vector2f(40000, 40000));
        pList.add(new Vector2f(50000, 50000));

        int index = 10;

        for (int i = 0; i < index; i++) {
            Vector2f position1 = bezier11(i * 0.1f, pList);
            Vector2f position2 = bezier22(i * 0.1f, pList);
            System.out.println(position1);
            System.out.println(position2);

        }
    }

}
