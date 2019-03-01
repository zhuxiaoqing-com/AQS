package com.example.demo4;

import org.apache.ibatis.javassist.bytecode.analysis.Executor;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class Test02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //这里特别注意一下类路径必须这样写
        //获取指定包下的所有类
        Resource[] resources = resourcePatternResolver.getResources("classpath*:");

        MetadataReaderFactory metadata = new SimpleMetadataReaderFactory();
        for (Resource resource : resources) {
            System.out.println(resource);
            MetadataReader metadataReader = metadata.getMetadataReader(resource);
            ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
            sbd.setResource(resource);
            sbd.setSource(resource);
            candidates.add(sbd);
        }
        for (BeanDefinition beanDefinition : candidates) {
            String classname = beanDefinition.getBeanClassName();
            //扫描controller注解
            Controller c = Class.forName(classname).getAnnotation(Controller.class);
            //扫描Service注解
            Service s = Class.forName(classname).getAnnotation(Service.class);
            //扫描Component注解
            Component component = Class.forName(classname).getAnnotation(Component.class);
            if (c != null || s != null || component != null) {
                System.out.println(classname);
            }
        }
    }

    @Test
    public void test01() {
        int s = Integer.MAX_VALUE;
        int s2 = 100;
        int s3 = (int) (s*100L/100L);
        System.out.println(s3);
        System.out.println(s);
    }

    @Test
    public void test02() {
       int id = 3032;
        System.out.println(id%1000);
    }

    @Test
    public void test03() {
        int id = 10000;
        int temp = 3;
        System.out.println(3*10000/10000);
        System.out.println(3*0.1/10000);
    }

    @Test
    public void test04() {
        long id = 1_000_000000;
        int temp = 3;
        System.out.println(id*id);
        System.out.println(Math.toIntExact(id*id));
    }

    @Test
    public void test05() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(3);
        objects.add(2);
        objects.add(4);
        objects.add(5);
        objects.add(6);
        objects.add(7);
        objects.add(8);
        objects.add(1);
        System.out.println(objects);

        Collections.sort(objects,(a,b)->{
            return  a-b;
        });
        System.out.println(objects);
    }

    @Test
    public void test06() {
        int hashcode = 3;
        int mask = 4-1;
        /**
         * 1...000
         * 1...101
         *
         * 从右到左
         */
        System.out.println(hashcode |= ~mask);
    }

    @Test
    public void test07() {
        ConcurrentSkipListSet c = new ConcurrentSkipListSet();
        c.add(2);
        c.add(2);
        System.out.println(c);
    }

    @Test
    public void test08() {
        String s = "2d";
        System.out.println(s == 2+"d");
    }

    public String join(int x) {
        return x+"d";
    }

    @Test
    public void test09() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(){
            {
                put(1,1);
                put(2,2);
                put(3,3);
                put(4,4);
                put(5,5);
                put(6,6);
                put(7,7);
            }
        };
        /*map.forEach((key, value)->{
            System.out.println(key);
            map.remove(key);
        });*/
        Collection<Integer> values = map.values();
        for(Integer s:values) {
            System.out.println(s);
            map.remove(s);
        }
    }

    @Test
    public void test10() {
        List<Integer> integers = Arrays.asList(2, 3, 4, 1, 3, 5);
        Collections.sort(integers,(a,b)-> b-a);
        System.out.println(integers);
    }

    @Test
    public void test11() {
        System.out.println(exception());
    }
    public int exception() {
        int i = 10;
        try {
            int a= 1/0;
            return 1;
        } catch (Exception e) {
            return i;
        } finally {
            i = 20;
            return i;
        }
    }

    @Test
    public void test12() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        int i = 27714560 / 1000 / 60;
        System.out.println(i);
        System.out.println(new Date(1551229021000L));
    }

    @Test
    public void test13() {
        long s = Integer.MAX_VALUE + 4;
        System.out.println(s);
    }

    @Test
    public void test14() {
        long l = 2L * 60 * 60 * 1000;
        System.out.println(l);
        System.out.println(l/4);
        System.out.println(l/(1000*60));
    }

    @Test
    public void test15() {
        System.out.println(24L*60);
    }
}















