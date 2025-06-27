package com.youxi.dataStructure.box2d3_2.jgk.zeroGC;

import com.youxi.math.box2dGJK.B2Math;

public class CCDZeroGC {

    public static B2CCDOutputZeroGC ccd(B2CCDInputZeroGC input, B2CCDOutputZeroGC b2CCDOutput) {
        b2CCDOutput.reset();
        B2ShapeCastPairInputZeroGC b2ShapeCastPairInput = b2CCDOutput.b2ShapeCastPairInput;
        b2ShapeCastPairInput.proxyA.reset(input.proxyA);
        b2ShapeCastPairInput.proxyB.reset(input.proxyB);
        // 得到相对移动
        B2Math.subZeroGC(input.proxyBDir, input.proxyADir, b2ShapeCastPairInput.translationB);


        B2CastOutputZeroGC b2CastOutput = DistanceZeroGC.b2ShapeCast(b2ShapeCastPairInput, b2CCDOutput.b2CastOutputZeroGC);

        if (!b2CastOutput.hit) {
            b2CCDOutput.hit = false;
            return b2CCDOutput;
        }

        b2CCDOutput.hit = true;
        b2CCDOutput.fraction = b2CastOutput.fraction;
        b2CCDOutput.normal.setXY(b2CastOutput.normal);
        b2CCDOutput.iterations = b2CastOutput.iterations;
        // 因为上面的相对向量是让B飞  所有这里的求出的碰撞点和fraction 是B飞行了fraction这么远碰撞到了
        B2Math.b2MulAddZeroGC(b2CastOutput.point, b2CastOutput.fraction, input.proxyBDir, b2CCDOutput.point);

        return b2CCDOutput;
    }
}
