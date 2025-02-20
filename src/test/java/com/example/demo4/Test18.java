package com.example.demo4;

import com.example.demo1.util.vo.Vector2f;
import com.example.代码优化.重构.第一个案例.简介;
import com.google.common.eventbus.EventBus;
import com.youxi.dataStructure.quadTree.Collision2D;
import com.youxi.entity.CMath;
import com.youxi.entity.Dir;
import com.youxi.entity.Position;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.web3j.abi.datatypes.Int;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test18 {

    public static class Obj {
        private Map<Integer, Integer> obj1;
        private int obj2;

        public Map<Integer, Integer> getObj1() {
            return obj1;
        }

        public void setObj1(Map<Integer, Integer> obj1) {
            this.obj1 = obj1;
        }

        public int getObj2() {
            return obj2;
        }

        public void setObj2(int obj2) {
            this.obj2 = obj2;
        }
    }

    @Test
    public void test01() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Obj obj = Obj.class.newInstance();

        Method method = Arrays.stream(Obj.class.getMethods())
                .filter(a -> a.getName().equals("setObj1"))
                .findAny().orElse(null);

        Map<Integer, Integer> objectObjectHashMap = new HashMap<>();
        Object objectObjectHashMap1 = new HashMap<>();
        method.invoke(obj, objectObjectHashMap1);
        System.out.println(obj);
    }

    @Test
    public void test02(){
        Dir dir0 = new Dir(1000, 0);
        dir0 = dir0.multiply(1);
        Dir dir1 = new Dir(000, 1000);
        Dir sub = dir0.sub(dir1);
        System.out.println(sub);

        System.out.println(CMath.getUnityRotateYDegree(sub));

         dir0 = new Dir(1000, 0);
        dir0 = dir0.multiply(2);
         dir1 = new Dir(000, 1000);
         sub = dir0.sub(dir1);
        System.out.println(sub);

        System.out.println(CMath.getUnityRotateYDegree(sub));
    }


    @Test
    public void test03(){
        Dir dir0 = CMath.getUnityDirByRotateYFromX(20);
        float length = 1000;
        dir0 = dir0.multiply(length);
        Dir dir1 = CMath.getUnityDirByRotateYFromX(100).normalize();

        dir0 = new Dir(3000,3000);
        length = 3f;
        dir1 = new Dir(1000,-1000);

        System.out.println(CMath.getUnityRotateYDegree(dir0));
        System.out.println(CMath.getUnityRotateYDegree(dir1));

        Vector2f v0 = new Vector2f(dir0.getX() / 1000F, dir0.getZ() / 1000F);
        Vector2f v1 = new Vector2f(dir1.getX() / 1000F, dir1.getZ() / 1000F);

        /**
         * 	dir[0] = dir0[0] - dir1[0]*len0*0.5f; // x方向上的平滑转向分量
         * 	dir[1] = 0;// y方向分量保持为0
         * 	dir[2] = dir0[2] - dir1[2]*len0*0.5f;// z方向上的平滑转向分量
         */
        float x = v0.x - v1.x * length * 0.5f;
        float z = v0.y - v1.y * length * 0.5f;

        Dir dir = new Dir(x, z).normalize();
        System.out.println(CMath.getUnityRotateYDegree(dir));

        System.out.println(CMath.getUnityRotateYDegree(dir0.normalize().sub(dir1.normalize())));
    }

    @Test
    public void test04(){
        int degree0 = 90;
        int degree1 = 0;

        a(1, degree0, degree1);
        a(1000, degree0, degree1);

        int index = 10;
        for (int i = 1; i < index; i++) {
            a(i/10f, degree0, degree1);
        }

        int length = 100;
        for (int i = 0; i < length; i++) {
            a(i, degree0, degree1);
        }
        a(1000, degree0, degree1);
        a(10000, degree0, degree1);
    }

    public void a(float length, int degree0, int degree1) {
        Dir dir0 = CMath.getUnityDirByRotateYFromX(degree0);
        dir0 = dir0.multiply(length);
        Dir dir1 = CMath.getUnityDirByRotateYFromX(degree1).normalize();





        Vector2f v0 = new Vector2f(dir0.getX() / 1000F, dir0.getZ() / 1000F);
        Vector2f v1 = new Vector2f(dir1.getX() / 1000F, dir1.getZ() / 1000F);

        /**
         * 	dir[0] = dir0[0] - dir1[0]*len0*0.5f; // x方向上的平滑转向分量
         * 	dir[1] = 0;// y方向分量保持为0
         * 	dir[2] = dir0[2] - dir1[2]*len0*0.5f;// z方向上的平滑转向分量
         */
       /* float x = v0.x - v1.x  * 0.5f*length;
        float z = v0.y - v1.y  * 0.5f*length;*/
        float x = v0.x - v1.x ;
        float z = v0.y - v1.y ;

        Dir dir = new Dir(x, z);
       // System.out.println(CMath.getUnityRotateYDegree(dir));
        System.out.println(CMath.getUnityRotateYDegree(dir));
    }


    @Test
    public void test05(){
        int h = 8;
        int w = 7;

        int i = 0;
        for (int z = 0; z < h; z++) {
            for (int x = 0; x < w; x++) {
                int index = z * w + x;
                System.out.println(i +"   "+ index);
                i++;
            }
        }

    }

}




/**
 * 1.威力（技能属性-基础伤害倍率）-%
 * 2.冷却时间（技能属性-攻速）-秒
 * 3.穿透（子弹属性-穿透）
 * 4.子弹数量（技能属性-发射数量）
 * 5.子弹判定（伤害判定-判定参数矩形取宽，圆形半径）
 * 6.持续时间（子弹属性-子弹存活时间）-秒
 * 7.装弹时间（技能属性-装弹时间）-秒
 * 8.弹药（技能属性-弹药量）
 * 9.生命值
 * 10.固定防御减免
 * 11.生命恢复速度-每秒
 * 12.物理减免-%
 * 13.魔法减免-%
 * 14.SP效率-%
 * 15.每回合额外SP
 * 16.金币效率-%
 * 17.每回合额外金币
 * 18.伤害间隔
 * 19.攻击距离
 * 20.视野距离
 * 21.视野角度
 * 22.子子弹威力
 * 23.护盾值
 * 24.护盾恢复速度-每秒
 * 25.生命恢复-百分比的形式
 * 26.护盾恢复-百分比的形式
 * 27.飞船移动速度Speed （需要最后乘以100，展示在客户端）
 * 28.飞船交战移速GlobalAngleSpeed（需要最后乘以100，展示在客户端）
 * 29.机库---初始出击时间
 * 30.机库---机甲出击间隔
 */


//  -XX:AutoBoxCacheMax=100000















