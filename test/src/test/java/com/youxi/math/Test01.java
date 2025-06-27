package com.youxi.math;

import com.example.demo1.util.vo.Vector2f;
import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/11/10 11:50
 * @Description:
 */
public class Test01 {
	/**
	 * angle 1 = r
	 * 周长= 2πr
	 * 360° = 2πr
	 * 360° = 2π*1弧度
	 * 1° = 2π/360°
	 * 1弧度 =  360/2π
	 */
	@Test
	public void test01() {
		System.out.println(Math.atan2(1, 1)*(180/Math.PI));
		System.out.println(Math.atan2(1, -1)*(180/Math.PI));
		System.out.println(Math.atan2(-1, 1)*(180/Math.PI));
		System.out.println(Math.atan2(-1, -1)*(180/Math.PI));
		System.out.println(Math.atan2(1, 0)*(180/Math.PI));
	}
	/**
	 * x^2 + y^2 = 距离
	 * ax + by + c = 0;
	 * x = (by-c)/ a;
	 *
	 * 如果公式 解;
	 */


	@Test
	public void test02() {
		/*Vector2f a = new Vector2f(1,3);
		Vector2f b = new Vector2f(5,3);
		Vector2f c = new Vector2f(4,10);
		Vector2f d = new Vector2f(4,1);*/

		/*Vector2f a = new Vector2f(3,3);
		Vector2f b = new Vector2f(1,3);
		Vector2f c = new Vector2f(4,10);
		Vector2f d = new Vector2f(4,1);*/

		Vector2f a = new Vector2f(31,3);
		Vector2f b = new Vector2f(13,3);
		Vector2f c = new Vector2f(11,10);
		Vector2f d = new Vector2f(11,1);

		double a1 = YouxiMath.cross(a, b, d);
		double a2 = YouxiMath.cross(a, b, c);
		double a3 = YouxiMath.cross(c, d, a);
		double a4 = YouxiMath.cross(c, d, b);

		System.out.println(" a1:"+ a1 + "  a2:" + a2 + "  a3:" + a3 + "  a4:" + a4);
		System.out.println(a3+a2-a1);
	}

	/**
	 * 测试 dtIntersectSegSeg2D 类
	 *    (p2-I)xv2 = 0
	 *    (p2-(p1+tv1))xv2=0;
	 *    (p2-p1-tv1)xv2=0;   v2x(p2-p1-tv1)
	 *    (p2-p1)xv2-tv1xv2=0; v2x(p2-p1)-v2xtv1
	 *    tv1xv2 = (p2-p1)xv2; tv2xv1 = v2x(p2-p1)
	 *    t1 = (p2-p1)xv2/v1xv2 t = v2x(p2-p1) /v2xv1
	 *
	 *   (I-p2)xv2 = 0
	 *   ((p1+tv1)-p2)xv2=0;
	 *  (p1-p2+tv1)xv2=0;
	 *  (p1-p2)xv2 = tv1xv2
	 *   t = (p1-p2)xv2/v1xv2
	 *  *
	 */
	@Test
	public void test03() {
		Vector2f p1 = new Vector2f(31,3);
		Vector2f b = new Vector2f(3,31);
		Vector2f v1 = b.sub(p1);

		Vector2f p2 = new Vector2f(5,5);
		Vector2f d = new Vector2f(20,20);
		Vector2f v2 = d.sub(p2);


		// (p2-p1)xv2 / v1xv2
		float aa = (float) (YouxiMath.cross(p2.sub(p1), v2) / YouxiMath.cross(v1, v2));

		// (p1-p2)xv2/v1xv2
		float bb = (float) (YouxiMath.cross(p1.sub(p2), v2) / YouxiMath.cross(v1, v2));

		System.out.println("t: "+aa);

		System.out.println();

		Vector2f I1 =p1.add(v1.multiply((float) aa));
		Vector2f I2 =p1.add(v1.multiply((float) bb));
		System.out.println("I1 = "+ I1 + "    I2 = "+I2);
		//(I-p2)xv2 = 0

		System.out.println(YouxiMath.cross(I1.sub(p2), v2));
		System.out.println(YouxiMath.cross(I2.sub(p2), v2));

		/**
		 * (I1-p2)xv2=0
		 * ((p1+tv1)-p2)xv2=0;
		 * (p1-p2 + tv1)xv2 = 0;
		 * (p1-p2)xv2 + tv1xv2 = 0;
		 * (p1-p2)xv2 = -t(v1xv2)
		 * -t(v1xv2) = (p1-p2)xv2;
		 * -t = (p1-p2)xv2/v1xv2
		 */

		System.out.println(p1.add(v1.multiply((float) aa)).sub(p2).cross(v2));
		System.out.println(p1.add(v1.multiply((float) bb)).sub(p2).cross(v2));

		System.out.println(I1.sub(p2).cross(v2));
		System.out.println(p1.add(v1.multiply(aa)).sub(p2).cross(v2));

		// (p1-p2 + tv1)xv2 = 0;
		System.out.println(" (p1-p2 + tv1)xv2 = 0 ：" + p1.sub(p2).add(v1.multiply(aa)).cross(v2));
		//(p1-p2)xv2 + tv1xv2 = 0;
		System.out.println("1 (p1-p2)xv2 + tv1xv2 = 0 : ---" + (p1.sub(p2).cross(v2)+v1.multiply(aa).cross(v2)));
		// (p1-p2)xv2 - t(v1xv2) =0
		System.out.println("(p1-p2)xv2 = t(v1xv2) --- :" + (p1.sub(p2).cross(v2)+v1.cross(v2)*aa));

		/**
		 *  t = v2x(p1-p2) /(v1xv2)  (p2-p1)xv2/v1xv2
		 * s = v1x(p1-p2) /(v1xv2)  (p1 - p2)xv1/v2xv1
		 */

		System.out.println("框架" + v2.cross(p1.sub(p2))/ v1.cross(v2));
		System.out.println("m" + p2.sub(p1).cross(v2)/ v1.cross(v2));

		System.out.println("框架" +v1.cross(p1.sub(p2))/ v1.cross(v2));
		System.out.println("m" + p1.sub(p2).cross(v1)/ v2.cross(v1));
	}




}
