package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.math.box2dGJK.b2ShapeProxy;

//  b2Distance的输入
//  在计算中你必须选择使用形状的半径
public class b2DistanceInput {
    b2ShapeProxy proxyA;    //距离代理A
    b2ShapeProxy proxyB;    //距离代理B
    /*B2Transform transformA;    //转换A
    B2Transform transformB;    //转换B*/
    boolean useRadii;             //是否使用半径

    public b2DistanceInput() {
    }

    public b2DistanceInput(b2ShapeProxy proxyA, b2ShapeProxy proxyB, boolean useRadii) {
        this.proxyA = proxyA;
        this.proxyB = proxyB;
        this.useRadii = useRadii;
    }
}
