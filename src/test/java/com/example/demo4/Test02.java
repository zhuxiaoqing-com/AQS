package com.example.demo4;

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
import java.util.LinkedHashSet;
import java.util.Set;

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
}















