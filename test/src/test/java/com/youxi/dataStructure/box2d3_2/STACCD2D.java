package com.youxi.dataStructure.box2d3_2;

/**
 * @author zhuxiaoqing
 * @Description: CCD2D
 * @Date 2025/8/22 13:59
 **/
public class STACCD2D {

    // ---------------- Vec2 ----------------
    public static class Vec2 {
        public double x, y;
        public Vec2() { this(0,0); }
        public Vec2(double x, double y) { this.x = x; this.y = y; }
        public Vec2 set(double x, double y){ this.x=x; this.y=y; return this; }
        public Vec2 copy(){ return new Vec2(x,y); }
        public Vec2 add(Vec2 b){ return new Vec2(x + b.x, y + b.y); }
        public Vec2 sub(Vec2 b){ return new Vec2(x - b.x, y - b.y); }
        public Vec2 mul(double s){ return new Vec2(x * s, y * s); }
        public double dot(Vec2 b){ return x*b.x + y*b.y; }
        public double len2(){ return x*x + y*y; }
        public double len(){ return Math.sqrt(len2()); }
        public Vec2 norm(){ double L = len(); return L==0? new Vec2(0,0) : new Vec2(x/L,y/L); }
    }

    // ---------------- OBB2 ----------------
    public static class OBB2 {
        public Vec2 center;
        public Vec2 axisX; // unit
        public Vec2 axisY; // unit
        public double hx, hy; // half extents
        public OBB2(Vec2 center, double angleRad, double hx, double hy){
            this.center = center;
            this.hx = hx; this.hy = hy;
            double c = Math.cos(angleRad), s = Math.sin(angleRad);
            this.axisX = new Vec2(c, s);
            this.axisY = new Vec2(-s, c);
        }
        // convenience: clone with expanded extents
        public OBB2 expanded(double r){
            OBB2 o = new OBB2(new Vec2(center.x, center.y), 0.0, hx + r, hy + r);
            // but need preserve axes and center:
            o.axisX = this.axisX.copy();
            o.axisY = this.axisY.copy();
            o.center = new Vec2(center.x, center.y);
            return o;
        }
    }

    // ---------------- Sweep result ----------------
    public static class SweepResult2D {
        public boolean hit;
        public double t; // [0,1]
        public Vec2 normal; // points from B toward A penetration direction (useful to resolve)
        public Vec2 contactPoint; // approximate contact point
        public Vec2 pointA; // support on A
        public Vec2 pointB; // support on B
        public SweepResult2D(){ hit=false; t=Double.POSITIVE_INFINITY; normal=new Vec2(0,0); contactPoint=new Vec2(0,0); pointA=new Vec2(0,0); pointB=new Vec2(0,0); }
    }

    // ---------------- Helpers ----------------
    private static double EPS = 1e-12;

    // support point on OBB in direction d (world)
    private static Vec2 supportOBB(OBB2 box, Vec2 centerAt, Vec2 d){
        double sx = Math.signum(box.axisX.dot(d)); if (sx==0) sx = 1;
        double sy = Math.signum(box.axisY.dot(d)); if (sy==0) sy = 1;
        return centerAt
                .add(box.axisX.mul(sx * box.hx))
                .add(box.axisY.mul(sy * box.hy));
    }

    // closest point on (non-rotated) box? We'll compute closest point on OBB to point p:
    private static Vec2 closestPointOnOBB(OBB2 box, Vec2 p){
        // transform p into box local coordinates: local = [ axisX dot (p-center), axisY dot (p-center) ]
        // 把世界坐标系中的点 p 转换到 OBB 的局部坐标系
        Vec2 d = p.sub(box.center);
        double lx = box.axisX.dot(d); // 点在 axisX 方向的局部坐标
        double ly = box.axisY.dot(d); // 点在 axisY 方向的局部坐标
        double cx = Math.max(-box.hx, Math.min(box.hx, lx)); // 因为是在obb局部坐标内所以范围就是-hx,-hy   hx,hy;
        double cy = Math.max(-box.hy, Math.min(box.hy, ly));
        // convert back to world 这里其实就是(cx,cy)应用obb的旋转;
        /**
         * 其实就是：
         * cos -sin
         * sin cos
         * 矩阵 *（cx,cy)  也就是右手坐标系原点旋转公式
         *
         */
        return box.center.add(box.axisX.mul(cx)).add(box.axisY.mul(cy));
    }

    /**
     * 这是一个关于两个圆形碰撞检测的数学问题。我来解释如何求解这个二次方程：
     *
     * 问题描述：
     * 圆形A：初始位置p0，半径r0，以速度v移动（在时间t∈[0,1]内位置为p(t)=p0 + v*t）
     * 圆形B：固定位置p1，半径r1
     * 碰撞条件：
     * 当两圆心的距离等于两圆半径之和时发生碰撞：
     * |p0 + v*t - p1|² = (r0 + r1)²
     *
     * 推导过程：
     * 展开距离公式：
     * (p0.x + v.xt - p1.x)² + (p0.y + v.yt - p1.y)² = (r0 + r1)²
     *
     * 整理成标准二次方程形式：
     * at² + bt + c = 0
     *
     * 其中：
     * a = v.x² + v.y²
     * b = 2*[v.x*(p0.x-p1.x) + v.y*(p0.y-p1.y)]
     * c = (p0.x-p1.x)² + (p0.y-p1.y)² - (r0+r1)²
     *
     * 解的性质：
     * 判别式Δ = b² - 4ac
     * Δ > 0：两个实数解（两个碰撞时间点）
     * Δ = 0：一个实数解（刚好擦过）
     * Δ < 0：无实数解（不会碰撞）
     * 实际应用：
     * 在游戏开发或物理模拟中，可以用这个方法来预测圆形物体何时会发生碰撞
     */
    // ---------------- 1) Circle vs Circle sweep ----------------
    // circle A: p0 radius r0, moves by v (over t in [0,1]: p(t)=p0 + v * t)
    // circle B: p1 radius r1, static
    public static SweepResult2D sweepCircleCircle(Vec2 p0, double r0, Vec2 v, Vec2 p1, double r1){
        SweepResult2D out = new SweepResult2D();
        // Solve | p0 + v*t - p1 |^2 = (r0+r1)^2  -> quadratic at^2 + bt + c = 0
        Vec2 s = p0.sub(p1);
        double R = r0 + r1;
        double a = v.dot(v);
        double b = 2.0 * s.dot(v);
        double c = s.dot(s) - R*R;

        if (Math.abs(a) < EPS) {
            // v ~ 0 -> static check
            if (c <= 0) {
                out.hit = true; out.t = 0;
                Vec2 normal = p0.sub(p1).norm(); out.normal = normal;
                out.pointA = p0.copy(); out.pointB = p1.copy(); out.contactPoint = new Vec2((p0.x+p1.x)/2,(p0.y+p1.y)/2);
            }
            return out;
        }

        double disc = b*b - 4*a*c;
        if (disc < 0) return out; // no real roots -> never collide

        double sqrtD = Math.sqrt(disc);
        double t1 = (-b - sqrtD) / (2*a);
        double t2 = (-b + sqrtD) / (2*a);
        double tHit = Double.POSITIVE_INFINITY;
        if (t1 >= 0 && t1 <= 1) tHit = t1;
        else if (t2 >= 0 && t2 <= 1) tHit = t2;
        if (tHit==Double.POSITIVE_INFINITY) return out;

        out.hit = true;
        out.t = tHit;
        Vec2 posA = p0.add(v.mul(tHit));
        Vec2 normal = posA.sub(p1).norm();
        out.normal = normal;
        out.pointA = posA;
        out.pointB = p1; // for circles, B support at center
        out.contactPoint = new Vec2(posA.x - normal.x * r0, posA.y - normal.y * r0); // contact on A surface
        return out;
    }

    /**
     * 这里
     * double rB = Math.abs(expanded.axisX.dot(axis)) * expanded.hx + Math.abs(expanded.axisY.dot(axis)) * expanded.hy;
     * 为啥halfX+halfY=投影最大值呢;
     * 正常我们算投影最大值应该是取obb的四个顶点直接投影到投影轴上，然后分别取最小的投影点 和最大的投影点,两个相减就是投影长度;我这个长度计算是通过坐标轴的原点来算的;
     *
     * 但是上面的代码的axisX和要投影的轴axis，都是单位向量轴,其轴都是以obb中心点出来的,而我上面说的算投影最大值是投影在从坐标轴原点出来的轴;
     * 所以我们可以先理解为,把obb的正方向的两个顶点投影到该轴上面，然后取两个顶点大的那一个就是投影的长度,而
     * 一个矩形顶点在局部坐标系中 v=±hx⋅axisX±hy⋅axisY  一共有这些可能; [⋅] = 点乘
     * 投影到单位向量 n 上：p=v⋅n=±hx(axisX⋅n)±hy(axisY⋅n)
     *四个顶点的投影就是： +hx(axisX⋅n)+hy(axisY⋅n);   +hx(axisX⋅n)−hy(axisY⋅n);   −hx(axisX⋅n)+hy(axisY⋅n);   −hx(axisX⋅n)−hy(axisY⋅n);
     * 那么投影点的最大投影就是 +hx(axisX⋅n)+hy(axisY⋅n);
     * 所以最终的投影的一半长度就是这么来的
     *
     * 为什么  Vec2 cRel = expanded.center.sub(p0);
     *  double t1 = (s0 - R) / vDot;
     *  double t2 = (s0 + R) / vDot;
     * 要这么写;
     *
     * s0 = cRel.dot(axis)：表示扩展OBB中心相对于圆心的位置在轴上的投影。
     * vDot = v.dot(axis)：速度在轴上的投影分量。
     * R：扩展OBB在该轴上的投影半径。
     * 碰撞条件为：圆心在时间 t 的投影位置与扩展OBB中心的投影距离不超过 R。即：∣s0+vDot⋅t∣≤R
     * 这里，s0 是初始相对投影，因为 cRel = expanded.center - p0，所以 s0 正好是 (expanded.center - p0) · axis。
     * 求解时间 t： −R≤s0+vDot⋅t≤R  这里的[vDot⋅t]的.是普通乘法; 就是方向乘时间系数
     * 整理得：
     *  t1=−R−s0 / vDot 和 t2 = R-s0 / vDot;
     *  这个公式是在 p0- expanded.center的基础上运行的;如果是 expanded.center -p0，那公式就要变成:
     *  t1=−R+s0 / vDot 和 t2 = R+s0 / vDot;
     *  也就是
           double t1 = (s0 - R) / vDot;
           double t2 = (s0 + R) / vDot;


     */
    // ---------------- 2) Circle vs OBB sweep ----------------
    // treat circle center as point moving, expand OBB by radius r, then do point vs OBB sweep
    // Input: circle center p0, radius r, velocity v (movement during frame), OBB box (static)
    // v 统一都是 A往B飞
    public static SweepResult2D sweepCircleOBB(Vec2 p0, double r, Vec2 v, OBB2 box) {
        // expand box by r
        OBB2 expanded = new OBB2(new Vec2(box.center.x, box.center.y), 0.0, box.hx + r, box.hy + r);
        expanded.axisX = box.axisX.copy();
        expanded.axisY = box.axisY.copy();

        // Now do point vs OBB sweep: same as sweep where A is a point (zero extents)
        // We'll use dynamic projection on box axes (2 axes).
        SweepResult2D out = new SweepResult2D();
        // 这里我理解 应该是p0-obb.center;将p0转换到以obb.center为原点的局部坐标系下，但是反过来也一样的，只是方向反了;
        // 我猜可能是因为是圆飞向obb 所以才这么处理
        Vec2 cRel = expanded.center.sub(p0); // center(B) - center(A_point)
        double tEnter = Double.NEGATIVE_INFINITY;
        double tExit = Double.POSITIVE_INFINITY;
        Vec2 bestAxis = null;
        boolean got = false;

        Vec2[] axes = new Vec2[]{expanded.axisX, expanded.axisY};
        for (Vec2 axisRaw : axes) {
            Vec2 axis = axisRaw.norm();
            //为什么是加起来的;比如一个aabb投影45°的直线上,x轴往-x轴投射，y轴往正x轴投射才是最大的;
            // 所以总结起来就是因为长和宽一人一个方向;
            // 获取halfX在axis上的投影和halfY在axis上的投影, 获取投影总长度;
            double rB = Math.abs(expanded.axisX.dot(axis)) * expanded.hx // note: hx/hy already expanded; but formula still works
                    + Math.abs(expanded.axisY.dot(axis)) * expanded.hy;
            // but for point, rA = 0
            double R = rB;
            double s0 = cRel.dot(axis); // B - Apoint 圆和扩大obb再axis上的投影
            double vDot = v.dot(axis);// 移动朝向在axis上的投影

            // 移动朝向和轴基本平行
            if (Math.abs(vDot) < EPS) {
                if (Math.abs(s0) > R) { //s0可能是负的, 所以要绝对值,如果不在rB的一半里面，就说明初始不重叠
                    return out; // separated and no movement toward axis -> no hit 有一个轴不相交
                } else {
                    continue;
                }
            } else {
                double t1 = (s0 - R) / vDot;
                double t2 = (s0 + R) / vDot;
                double enter = Math.min(t1, t2);
                double exit = Math.max(t1, t2);
                if (enter > tEnter) {
                    tEnter = enter;
                    Vec2 n = axis.copy();
                    //法线是反方向的;比如你一个子弹从左到右打在aabb上,要往反反向弹;
                    if (vDot > 0) n = new Vec2(-n.x, -n.y);
                    bestAxis = n; got = true;
                }
                if (exit < tExit) tExit = exit;
                if (tEnter > tExit) return out;
            }
        }

        if (tExit < 0) return out;
        if (tEnter > 1) return out;
        double tHit = Math.max(0.0, tEnter);
        out.hit = true; out.t = tHit;
        out.normal = got ? bestAxis : new Vec2(0,0);

        // 这里求碰撞点为什么不用p0 + v * tHit + v.normalized() * r 而是用下面的方法, 具体看图 sweepCircleOBB碰撞点计算.jpg
        // At hit, circle center:
        Vec2 cA = p0.add(v.mul(tHit));
        // For contact point: closest point on original (non-expanded) box to circle center
        Vec2 closest = closestPointOnOBB(box, cA);
        // contact point on circle surface is along (closest - center).normalized * r plus center
        Vec2 diff = closest.sub(cA);
        double d2 = diff.len2();
        if (d2 <= EPS) {
            // 如果圆心刚好落在最近点（这种情况很少见），那么就需要在盒子上沿着 -normal 方向取一个支撑点。 就是圆和obb一开始就重叠在一起
            // center exactly at closest (rare) -> pick support on box along -normal
            if (got) {
                out.pointB = supportOBB(box, box.center, out.normal);
                out.pointA = out.pointB.add(out.normal.mul(-r)); // approximate
                out.contactPoint = new Vec2((out.pointA.x + out.pointB.x)*0.5, (out.pointA.y + out.pointB.y)*0.5);
            } else {
                out.contactPoint = cA;
            }
        } else {
            /**
             * 即使 pointA != pointB，也 可能依然是发生了碰撞：
             * pointA 表示圆边缘的实际接触点；
             * pointB 表示盒子表面几何上对应的接触点；
             * 两个点之间的距离，就是 穿透深度。
             * 如果你在调试里看到 pointA 和 pointB 差距大，说明圆和 OBB 已经相交很深。
             *
             * 为什么取中点 contactPoint？
             * 这是常见的 稳定做法：
             * 如果两个点完全重合，取中点没区别；如果有数值误差或深度穿透，中点就是两者之间的合理“妥协位置”，能避免物体抖动。
             */
            double d = Math.sqrt(d2);
            Vec2 normal = diff.mul(1.0/d); // 修复法线，这才是真的法线
            out.pointA = cA.add(normal.mul(r)); // point on circle surface
            out.pointB = closest; // nearest point on box
            out.contactPoint = new Vec2((out.pointA.x + out.pointB.x)*0.5, (out.pointA.y + out.pointB.y)*0.5);
            out.normal = normal; // override normal to point from A->B
        }
        return out;
    }

    // ---------------- 3) OBB vs OBB sweep (from previous dynamic SAT 2D) ----------------
    // A moves relative to static B by vRel (movement over frame is vRel)
    public static SweepResult2D sweepOBBvsOBB(OBB2 A, OBB2 B, Vec2 vRel) {
        SweepResult2D res = new SweepResult2D();
        Vec2[] axes = new Vec2[]{A.axisX, A.axisY, B.axisX, B.axisY};
        Vec2 cBA = B.center.sub(A.center); // 这里反过来上面 圆打obb的时候说过了为什么反过来

        double tEnter = Double.NEGATIVE_INFINITY;
        double tExit  = Double.POSITIVE_INFINITY;
        Vec2 bestAxis = null;
        boolean gotBest = false;

        for (Vec2 axisRaw : axes) {
            Vec2 axis = axisRaw.norm();
            double rA = Math.abs(A.axisX.dot(axis)) * A.hx + Math.abs(A.axisY.dot(axis)) * A.hy;
            double rB = Math.abs(B.axisX.dot(axis)) * B.hx + Math.abs(B.axisY.dot(axis)) * B.hy;
            // 就相当于obbA和obbB的半径一样的意思,两个投影一半的长度加起来 < 两个中心点的距离就是碰撞了
            double R = rA + rB; // rA 是 A 的“半长度”，rB 是 B 的“半长度”。把这两个加起来，就得到了两个盒子在这条轴上最多能“相交/接触”的范围。
            double s0 = cBA.dot(axis);
            double vDot = vRel.dot(axis);

            if (Math.abs(vDot) < EPS) { // 方向和轴基本平行 两个obb的距离>投影距离
                if (Math.abs(s0) > R) return res; // separated and won't move closer
                else continue;
            } else {
                double t1 = (s0 - R) / vDot;
                double t2 = (s0 + R) / vDot;
                double enter = Math.min(t1, t2);
                double exit = Math.max(t1, t2);
                if (enter > tEnter) {
                    tEnter = enter;
                    Vec2 n = axis.copy();
                    if (vDot > 0) n = new Vec2(-n.x, -n.y);
                    bestAxis = n; gotBest = true;
                }
                if (exit < tExit) tExit = exit;
                if (tEnter > tExit) return res;
            }
        }

        if (tExit < 0) return res;
        if (tEnter > 1) return res;
        double tHit = Math.max(0.0, tEnter);
        res.hit = true; res.t = tHit; res.normal = gotBest ? bestAxis : new Vec2(0,0);

        // compute supports
        Vec2 cA_t = A.center.add(vRel.mul(tHit));
        Vec2 dirA = new Vec2(-res.normal.x, -res.normal.y);
        Vec2 dirB = res.normal;
        res.pointA = supportOBB(A, cA_t, dirA);
        res.pointB = supportOBB(B, B.center, dirB);
        res.contactPoint = new Vec2((res.pointA.x + res.pointB.x)*0.5, (res.pointA.y + res.pointB.y)*0.5);
        return res;
    }

    // ---------------- Demo main ----------------
    public static void main(String[] args) {
        // Circle-circle demo
        Vec2 p0 = new Vec2(0,0), p1 = new Vec2(5,0);
        Vec2 v = new Vec2(10,0); // A moves to x=10 in frame
        SweepResult2D rcc = sweepCircleCircle(p0, 0.5, v, p1, 1.0);
        System.out.println("Circle-Circle hit: " + rcc.hit + " t=" + rcc.t);

        // Circle-OBB demo
        OBB2 box = new OBB2(new Vec2(5,0), 0.0, 1.0, 1.0);
        SweepResult2D rco = sweepCircleOBB(p0, 0.5, v, box);
        System.out.println("Circle-OBB hit: " + rco.hit + " t=" + rco.t);

        // OBB-OBB demo
        OBB2 A = new OBB2(new Vec2(0,0), 0.0, 0.5, 0.5);
        OBB2 B = new OBB2(new Vec2(5,0), 0.0, 1.0, 1.0);
        SweepResult2D robb = sweepOBBvsOBB(A,B,v);
        System.out.println("OBB-OBB hit: " + robb.hit + " t=" + robb.t);
    }
}
