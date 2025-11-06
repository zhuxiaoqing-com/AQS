package com.example.demo4;

import cn.hutool.core.lang.func.VoidFunc0;
import com.alibaba.fastjson.JSON;
import com.example.demo1.util.vo.Vector2f;
import com.example.demo4.testObj1.GlobalInfo;
import com.example.代码优化.重构.第一个案例.简介;
import com.google.common.eventbus.EventBus;
import com.youxi.dataStructure.quadTree.Collision2D;
import com.youxi.entity.CMath;
import com.youxi.entity.Dir;
import com.youxi.entity.Position;

import org.junit.Test;
import org.web3j.abi.datatypes.Int;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
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
    public void test02() {
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
    public void test03() {
        Dir dir0 = CMath.getUnityDirByRotateYFromX(20);
        float length = 1000;
        dir0 = dir0.multiply(length);
        Dir dir1 = CMath.getUnityDirByRotateYFromX(100).normalize();

        dir0 = new Dir(3000, 3000);
        length = 3f;
        dir1 = new Dir(1000, -1000);

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
    public void test04() {
        int degree0 = 90;
        int degree1 = 0;

        a(1, degree0, degree1);
        a(1000, degree0, degree1);

        int index = 10;
        for (int i = 1; i < index; i++) {
            a(i / 10f, degree0, degree1);
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
        float x = v0.x - v1.x;
        float z = v0.y - v1.y;

        Dir dir = new Dir(x, z);
        // System.out.println(CMath.getUnityRotateYDegree(dir));
        System.out.println(CMath.getUnityRotateYDegree(dir));
    }


    @Test
    public void test05() {
        int h = 8;
        int w = 7;

        int i = 0;
        for (int z = 0; z < h; z++) {
            for (int x = 0; x < w; x++) {
                int index = z * w + x;
                System.out.println(i + "   " + index);
                i++;
            }
        }

    }

    long lastTime;
    long interval;


    long nanoLastTime;
    long nanoInterval;
    long nanoInterval111;

    int count;
    long allSum;
    long allSumNano;

    @Test
    public void test08() throws InterruptedException {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);

        Runnable runnable = () -> {
            if (nanoLastTime <= 0) {
                nanoLastTime = System.nanoTime();
                lastTime = System.currentTimeMillis();
                return;
            }
            count++;
            long currNano = System.nanoTime();
            nanoInterval = currNano - nanoLastTime;
            nanoLastTime = currNano;
            allSumNano = allSumNano + nanoInterval;

            long time = TimeUtil.getTime();
            interval = time - lastTime;
            lastTime = time;
            allSum = allSum + interval;
        };

        ScheduledFuture<?> future = scheduled.scheduleAtFixedRate(runnable, 100,
                100, TimeUnit.MILLISECONDS);

        int index = 0;
        while (true) {
            Thread.sleep(1);
            if (nanoInterval111 != nanoInterval) {
                if (index++ > 50) {
                    break;
                }
                System.out.println(nanoInterval / 1000_000 + "    " + interval);
                nanoInterval111 = nanoInterval;
            }
        }

        System.out.println("count " + count + "count *100  " + (count * 100));
        System.out.println("allsum " + allSum);
        System.out.println("allSumNano " + allSumNano);

    }


    @Test
    public void test09() throws InterruptedException {
        ArrayList<GlobalInfo> objects = new ArrayList<>();
        objects.add(GlobalInfo.create());
        objects.add(GlobalInfo.create());

        Object json = JSON.toJSON(objects);
        System.out.println(json);
    }

    @Test
    public void test010() throws InterruptedException {
        ArrayList<Integer> objects = new ArrayList<>();
        int index = 10;
        for (int i = 0; i < index; i++) {
            objects.add(i);
        }

        List<Integer> collect = objects.stream().sorted(Comparator.comparingInt(Integer::intValue).thenComparingLong(a -> getCount())).collect(Collectors.toList());
        System.out.println(collect);
    }

    private int getCount() {
        System.out.println("调用我了！！！");
        return 1;
    }


    @Test
    public void test011() throws InterruptedException {
        System.out.println(29233416L*1000*60);
    }


    @Test
    public void test012() throws InterruptedException {
        System.out.println(29233416L*1000*60);
        throw new NullPointerException();
    }

        public static void main(String[] args) {
            long last = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                LockSupport.parkNanos(1_000_000); // 1ms
                long now = System.nanoTime();
                System.out.println((now - last) / 1_000_000.0 + " ms");
                last = now;
            }
        }


   /* ArrayDeque<Integer> queue = new ArrayDeque<>();

    boolean running = false;
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor() {
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if(!queue.isEmpty()) {
                Integer poll = queue.poll();
                threadPoolExecutor.execute(()->poll);
            }
        }
    };

    // 接收消息
    public void receive(Integer message) {
        if(running) {
            queue.add(message);
        }else {
            threadPoolExecutor.execute(()->message);
        }
    }*/

}

