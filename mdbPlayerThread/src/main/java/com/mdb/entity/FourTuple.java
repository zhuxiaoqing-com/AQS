package com.mdb.entity;

public class FourTuple<A, B, C, D> {
    private A one;
    private B two;
    private C three;
    private D four;

    public FourTuple(A one, B two, C three, D four) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
    }

    public A getOne() {
        return one;
    }

    public B getTwo() {
        return two;
    }

    public C getThree() {
        return three;
    }

    public D getFour() {
        return four;
    }
}
