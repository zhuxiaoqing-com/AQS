package com.youxi.dataStructure.box2d3_2.jgk.zeroGC;


//  b2Distance的输入
//  在计算中你必须选择使用形状的半径
public class B2DistanceInputZeroGC {
    public b2ShapeProxyZeroGC proxyA = new b2ShapeProxyZeroGC();    //距离代理A
    public b2ShapeProxyZeroGC proxyB = new b2ShapeProxyZeroGC();    //距离代理B
    /*B2Transform transformA;    //转换A
    B2Transform transformB;    //转换B*/
    public boolean useRadii = true;             //是否使用半径

    public B2DistanceInputZeroGC() {
    }

    public B2DistanceInputZeroGC(b2ShapeProxyZeroGC proxyA, b2ShapeProxyZeroGC proxyB, boolean useRadii) {
        reset(proxyA, proxyB, useRadii);
    }

    public void reset(b2ShapeProxyZeroGC proxyA, b2ShapeProxyZeroGC proxyB, boolean useRadii) {
        this.proxyA = proxyA;
        this.proxyB = proxyB;
        this.useRadii = useRadii;
    }
}
