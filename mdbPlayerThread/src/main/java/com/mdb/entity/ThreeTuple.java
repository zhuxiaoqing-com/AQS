package com.mdb.entity;

/**
 * @author zhuxiaoqing
 * @Description: TwoTuple
 * @Date 2026/5/8 19:14
 **/
public class ThreeTuple<A,B,C> {
    A one;
    B two;
    C three;

    public ThreeTuple(A one, B two, C three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public A getOne() {
        return one;
    }

    public void setOne(A one) {
        this.one = one;
    }

    public B getTwo() {
        return two;
    }

    public void setTwo(B two) {
        this.two = two;
    }

    public C getThree() {
        return three;
    }

    public void setThree(C three) {
        this.three = three;
    }
}
