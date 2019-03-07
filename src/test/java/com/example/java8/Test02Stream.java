package com.example.java8;

import javafx.application.Application;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
public class Test02Stream {
    @Test
    public void test01() {
        List<Employee> employees = new ArrayList<>();
        Map<Integer, Employee> collect = employees.stream().collect(Collectors.toMap(x -> x.getId(), x -> x));
        Map<Integer, Map<Integer, Employee>> a = new HashMap<>();
        Map<Integer, Map<Integer, Employee>> b = employees.stream().collect(Collectors.groupingBy(Employee::getAge,LinkedHashMap::new, Collectors.toMap(Employee::getId, Function.identity())));

    }


    @Test
    public void test02() {
        ArrayList<A> resources = new ArrayList<>();
        resources.add(new A(1,3));
        resources.add(new A(1,2));
        resources.add(new A(2,3));
        resources.add(new A(2,3));
        Map<Integer, Integer> collect = resources.stream()
                .collect(Collectors.groupingBy(A::getId, Collectors.summingInt(A::getCount)));
        System.out.println(collect);
    }

    class A {
        int id;
        int count;

        public A(int id, int count) {
            this.id = id;
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


    @Test
    public void test03() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        List<Object> collect = objects.stream().skip(1).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test04() {
        double v = 1544000 * 0.06;
        System.out.println(v);
        System.out.println(v/8);
    }
}