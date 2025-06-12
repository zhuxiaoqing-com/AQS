package com.youxi.dataStructure.box2d3_2.entity;
//
public class b2AABB {
    public b2Vec2 lowerBound;
    public b2Vec2 upperBound;

    public b2AABB() {
        lowerBound = new b2Vec2();
        upperBound = new b2Vec2();
    }

    public b2AABB(b2Vec2 lowerBound, b2Vec2 upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }


    @Override
    public String toString() {
        return "{" + lowerBound + "," + upperBound + '}';
    }
}
