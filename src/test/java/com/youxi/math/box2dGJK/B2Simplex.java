package com.youxi.math.box2dGJK;

import com.youxi.math.box2dGJK.shape.B2Vec2;

import static com.youxi.math.box2dGJK.B2Math.*;
import static com.youxi.math.box2dGJK.B2Math.multiply;

public class B2Simplex {

    public B2SimplexVertex v1 = new B2SimplexVertex(); //m_v1, m_v2, m_v3;//单纯形的顶点变量
    public B2SimplexVertex v2 = new B2SimplexVertex();
    public B2SimplexVertex v3 = new B2SimplexVertex();
    public int count;//单纯形顶点个数

    public B2Simplex() {
    }

    public B2SimplexVertex get(int index) {
        switch (index) {
            case 0:
                return v1;
            case 1:
                return v2;
            case 2:
                return v3;
        }
        return null;
    }

    //第一次调用将count置0
    public class b2SimplexCache {
        float metric;        //长度或面积
        int count;       //顶点数
        int indexA[] = new int[3];    //shapeA上的顶点索引数组
        int indexB[] = new int[3];    //shapeB上的顶点索引数组
    }

    ;

    public void init_2(b2ShapeProxy proxyA,
                       b2ShapeProxy proxyB) {
        B2SimplexVertex v = v1;
        v.indexA = 0;
        v.indexB = 0;
        v.wA = proxyA.GetVertex(0);
        v.wB = proxyB.GetVertex(0);
        v.w = sub(v.wB, v.wA);
        v.a = 1.0f;
        count = 1;
    }

    public void init_3_2(b2ShapeProxy proxyA,
                         b2ShapeProxy proxyB) {
        B2SimplexVertex v = v1;
        v.indexA = 0;
        v.indexB = 0;
        v.wA = proxyA.GetVertex(0);
        v.wB = proxyB.GetVertex(0);
        v.w = sub(v.wA, v.wB);
        v.a = 1.0f;
        count = 1;
    }


    /**
     * 获取搜索方向
     */
    B2Vec2 GetSearchDirection() {
        switch (count) {
            case 1:  //一个顶点从相反的方向去找 其实就是顺着原点的方向找
                return v1.w.multiply(-1f);
            case 2: {
                //两个顶点，1、两个点所组成边向量e12与第一个顶点和原点的所组成的向量-m_v1.w先去判断符号
                //          2、再求e12相应方向的法向量a
                //          3、将a作为查找方向返回
                B2Vec2 e12 = v2.w.sub(v1.w);
                //float sgn = b2Cross(e12, -m_v1.w);
                float sgn = e12.cross(v1.w.multiply(-1));
                if (sgn > 0.0f) {
                    // Origin is left of e12. // 原点在e12的左边
                    return b2Cross(1.0f, e12);
                } else {
                    // Origin is right of e12. 原点在e12的右边
                    return b2Cross(e12, 1.0f);
                }
            }
            //这里不会出现多个点的情况，因为查找方向是对两个以下的顶点而言的，
            //没找到合适的第三个顶点之前永远都会将离原点最远的第三个点移除。
            default:
                return null;
        }
    }

    /**
     * 获取最近的点
     */
    B2Vec2 GetClosestPoint() {
        switch (count) {
            case 0://对于0个顶点，没有最近点这一说，所以我们在此处断言
                return null;

            case 1:
                return v1.w; //对于1个顶点，最近点就是那个顶点

            case 2: //对于两个顶点，重心是它们的最近点
                //return m_v1.a * m_v1.w + m_v2.a * m_v2.w;
                return v1.w.multiply(v1.a).add(v2.w.multiply(v2.a));

            case 3:  //对于三个顶点来说，单纯形（此处是三角形）已形成，不需要在找最近点
                //故返回零向量
                return new B2Vec2(0, 0);

            default:
                return null;
        }
    }

    /**************************************************************************
     * 功能描述：获取见证点【形状上的参与求解的两个顶点】
     * 参数说明： pA：形状A上的点
     pB：形状B上的点
     * 返 回 值： (void)
     ***************************************************************************/
    void GetWitnessPoints(B2Vec2 pA, B2Vec2 pB) {
        switch (count) {
            case 0:    //对于0个点，还没有开始查找单纯形的顶点，不存在见证点，故断言
                return;

            case 1:    //对于1个点，见证点就是wA和wB，直接赋值
			/*pA = m_v1.wA;
			pB = m_v1.wB;*/
                pA.Set(v1.wA);
                pB.Set(v1.wB);
                break;

            case 2: //对于2个点，我们用重心坐标求的的，至于为什么要用重心坐标，我们在下面的讲解中将会看到
                // 见证点这里其实一般都会有一边是点 一边是边;所以这个重心坐标其实一般都是给边的那一边用;
                //通过权重a1和a2（由Solve2()计算）对顶点坐标线性插值，得到线段上的最近点3。
                /*	*pA = m_v1.a * m_v1.wA + m_v2.a * m_v2.wA;
                 *pB = m_v1.a * m_v1.wB + m_v2.a * m_v2.wB;*/
                pA.Set(add(multiply(v1.a, v1.wA), multiply(v2.a, v2.wA)));
                pB.Set(add(multiply(v1.a, v1.wB), multiply(v2.a, v2.wB)));
                break;

            case 3: //对于3个点，我们可以判断此时两形状一碰撞了，见证点，就是两形状的碰撞点，故是一样的。
                /* *pA = m_v1.a * m_v1.wA + m_v2.a * m_v2.wA + m_v3.a * m_v3.wA;
                 *pB = *pA;*/
                pA.Set(add(multiply(v1.a, v1.wA), multiply(v2.a, v2.wA), multiply(v3.a, v3.wA)));
                pB.Set(pA);
                break;

            default:
                break;
        }
    }

    /**************************************************************************
     * 功能描述：获取尺度（长度）
     * 参数说明： (void)
     * 返 回 值： 尺度（长度）
     ***************************************************************************/
    float GetMetric() {
        switch (count) {
            case 0: //对于0个点，还没有开始查找单纯形的顶点，不存在长度，故断言
                return 0.0f;

            case 1:
                return 0.0f;

            case 2: //对于2个点，计算其长度，并返回
                //return b2Distance(m_v1.w, m_v2.w);
                return v1.w.sub(v2.w).length();

            case 3:
                //对于3个点，获取(m_v2.w - m_v1.w)与(m_v3.w - m_v1.w)
                //两向量组成的平行四边形面积
                //return b2Cross(m_v2.w - m_v1.w, m_v3.w - m_v1.w);
                return b2Cross(sub(v2.w, v1.w), sub(v3.w, v1.w));

            default: //不存在更多的点，如果到这里就出现了异常
                return 0.0f;
        }
    }


    // Solve a line segment using barycentric coordinates.
    //2个单纯顶点时，判断原点位置函数
//
// p = a1 * w1 + a2 * w2
// a1 + a2 = 1
//
// The vector from the origin to the closest point on the line is
// perpendicular to the line.
// e12 = w2 - w1
// dot(p, e) = 0
// a1 * dot(w1, e) + a2 * dot(w2, e) = 0
//
// 2-by-2 linear system
// [1      1     ][a1] = [1]
// [w1.e12 w2.e12][a2] = [0]
//
// Define
// d12_1 =  dot(w2, e12)
// d12_2 = -dot(w1, e12)
// d12 = d12_1 + d12_2
//
// Solution
// a1 = d12_1 / d12
// a2 = d12_2 / d12
    // 查看 solve2.jpg图
    public void Solve2() {
		/*
		 * 空间中点到线段的最近点必然位于以下三种情况之一：
		 * w1区域‌：原点在线段起点w1的“外侧”（靠近w1一侧）3。
		 * w2区域‌：原点在线段终点w2的“外侧”（靠近w2一侧）3。
		 * e12区域‌：原点在线段内部，需通过插值计算最近点3。
		 *
		    d12_2 = -w1·e12：若≤0，说明原点在w1外侧（因投影方向与e12相反）3。
			d12_1 = w2·e12：若≤0，说明原点在w2外侧（因投影方向与e12相同）3。
			若两者均>0，则原点在线段内部，需按比例分配权重（a1 = d12_1/(d12_1+d12_2)）
		 */

        B2Vec2 w1 = v1.w; // 单纯形的顶点1
        B2Vec2 w2 = v2.w; // 单纯形的顶点2
        B2Vec2 e12 = sub(w2, w1);

        // w1 region
        //d12_2为w1在e12反向的投影，若≤0说明原点在w1这一侧
        float d12_2 = -b2Dot(w1, e12);
        if (d12_2 <= 0.0f) {
            // a2 <= 0, so we clamp it to 0 // a2 <= 0, 所以我们将它置为0
            v1.a = 1.0f;
            count = 1;
            return;
        }

        // w2 region 原点在 w2 这一侧
        float d12_1 = b2Dot(w2, e12);
        if (d12_1 <= 0.0f) {
            // a1 <= 0, so we clamp it to 0    a1 <= 0,所以我们将它置为0
            v2.a = 1.0f;
            count = 1;
            v1 = v2;
            return;
        }

        // Must be in e12 region. 原点在线段里面
        // 根据原点权重 取与中心的距离  这里1.0f/ 就是为了节省一次除法计算
        float inv_d12 = 1.0f / (d12_1 + d12_2);
        // 里面是取比值  所以可以直接用dot好的结果取，因为两边都同时*了w2-w1线段的长度
        v1.a = d12_1 * inv_d12;// d12_1  / (d12_1 + d12_2)
        v2.a = d12_2 * inv_d12;// d12_2  / (d12_1 + d12_2)
        count = 2;
    }

    // Possible regions:
// - points[2]
// - edge points[0]-points[2]
// - edge points[1]-points[2]
// - inside the triangle

    //3个单纯顶点时，判断原点位置函数 下面是判断 原点距离三角形的点最近 还是距离三角形的某条边最近 还是说点在三角形里面
    //这里的代码 详细可以看 navmesh项目里面的 dtClosestPtPointTriangle
    public void Solve3() {
        B2Vec2 w1 = v1.w;
        B2Vec2 w2 = v2.w;
        B2Vec2 w3 = v3.w;

        // Edge12
        // [1      1     ][a1] = [1]
        // [w1.e12 w2.e12][a2] = [0]
        // a3 = 0
        B2Vec2 e12 = sub(w2, w1);
        float w1e12 = b2Dot(w1, e12);
        float w2e12 = b2Dot(w2, e12);
        float d12_1 = w2e12;
        float d12_2 = -w1e12;

        // Edge13
        // [1      1     ][a1] = [1]
        // [w1.e13 w3.e13][a3] = [0]
        // a2 = 0
        B2Vec2 e13 = sub(w3, w1);
        float w1e13 = b2Dot(w1, e13);
        float w3e13 = b2Dot(w3, e13);
        float d13_1 = w3e13;
        float d13_2 = -w1e13;

        // Edge23
        // [1      1     ][a2] = [1]
        // [w2.e23 w3.e23][a3] = [0]
        // a1 = 0
        B2Vec2 e23 = sub(w3, w2);
        float w2e23 = b2Dot(w2, e23);
        float w3e23 = b2Dot(w3, e23);
        float d23_1 = w3e23;
        float d23_2 = -w2e23;

        // Triangle123
        float n123 = b2Cross(e12, e13);

        float d123_1 = n123 * b2Cross(w2, w3);
        float d123_2 = n123 * b2Cross(w3, w1);
        float d123_3 = n123 * b2Cross(w1, w2);

        // w1 region
        if (d12_2 <= 0.0f && d13_2 <= 0.0f) {
            v1.a = 1.0f;
            count = 1;
            return;
        }

        // e12
        if (d12_1 > 0.0f && d12_2 > 0.0f && d123_3 <= 0.0f) {
            float inv_d12 = 1.0f / (d12_1 + d12_2);
            v1.a = d12_1 * inv_d12;
            v2.a = d12_2 * inv_d12;
            count = 2;
            return;
        }

        // e13
        if (d13_1 > 0.0f && d13_2 > 0.0f && d123_2 <= 0.0f) {
            float inv_d13 = 1.0f / (d13_1 + d13_2);
            v1.a = d13_1 * inv_d13;
            v3.a = d13_2 * inv_d13;
            count = 2;
            v2 = v3;
            return;
        }

        // w2 region
        if (d12_1 <= 0.0f && d23_2 <= 0.0f) {
            v2.a = 1.0f;
            count = 1;
            v1 = v2;
            return;
        }

        // w3 region
        if (d13_1 <= 0.0f && d23_1 <= 0.0f) {
            v3.a = 1.0f;
            count = 1;
            v1 = v3;
            return;
        }

        // e23
        if (d23_1 > 0.0f && d23_2 > 0.0f && d123_1 <= 0.0f) {
            float inv_d23 = 1.0f / (d23_1 + d23_2);
            v2.a = d23_1 * inv_d23;
            v3.a = d23_2 * inv_d23;
            count = 2;
            v1 = v3;
            return;
        }

        // Must be in triangle123
        float inv_d123 = 1.0f / (d123_1 + d123_2 + d123_3);
        v1.a = d123_1 * inv_d123;
        v2.a = d123_2 * inv_d123;
        v3.a = d123_3 * inv_d123;
        count = 3;
    }


}
