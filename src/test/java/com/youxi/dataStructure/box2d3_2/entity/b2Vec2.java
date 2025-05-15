package com.youxi.dataStructure.box2d3_2.entity;

public class b2Vec2 {
    public float x, y;

    public b2Vec2() {
    }

    public b2Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + '}';
    }
}
