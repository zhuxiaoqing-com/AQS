package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.shape.B2Vec2;

/**
 * 只能用于直线飞行 并且飞行中不改变朝向的两个运动物体
 * 也就是阉割版的CCD
 * Continuous Collision Detection
 *
 * 这里还有一个问题 就是CCD怎么和AABB动态树互动调用起来
 */
public class CCD {

    public static B2CCDOutput ccd(B2CCDInput input) {
        B2CCDOutput b2CCDOutput = new B2CCDOutput();
        // 得到相对移动
        /**
         * 我这里其实还有一个问题,我相减以后获取的dir 包含移动速度,但是并不是两者任何一者的速度,只要他们最终能碰撞到肯定hit = true;
         * 我这个算法没有问题; 比如A B相差15米;他们分别朝着x轴向对方移动; Apostion(0,0,0),Adir(5,0,0)  Bposition(15,0,0),Bdir(-5,0,0);
         * 他们的相对向量=(-5,0,0)-(5,0,0) = (-10,0,0); 然后就相当于b朝着A移动(-10,0,0) 是不会碰撞到的 没问题的;
         *
         */
        B2Vec2 relativeBDir = B2Math.sub(input.proxyBDir, input.proxyADir);

        // 方向一致
        if (relativeBDir.length() <= 0) {
            b2CCDOutput.hit = false;
            return b2CCDOutput;
        }

        B2ShapeCastPairInput b2ShapeCastPairInput = new B2ShapeCastPairInput(input.proxyA, input.proxyB, relativeBDir);
        B2CastOutput b2CastOutput = Distance.b2ShapeCast(b2ShapeCastPairInput);

        if (!b2CastOutput.hit) {
            b2CCDOutput.hit = false;
            return b2CCDOutput;
        }

        b2CCDOutput.hit = true;
        b2CCDOutput.fraction = b2CastOutput.fraction;
        b2CCDOutput.normal = b2CastOutput.normal;
        b2CCDOutput.iterations = b2CastOutput.iterations;
        // 因为上面的相对向量是让B飞  所有这里的求出的碰撞点和fraction 是B飞行了fraction这么远碰撞到了
        b2CCDOutput.point = B2Math.b2MulAdd(b2CastOutput.point, b2CastOutput.fraction, input.proxyBDir);

        return b2CCDOutput;
    }
}
