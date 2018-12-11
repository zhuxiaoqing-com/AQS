package com.example.java8;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test02Stream {
    @Test
    public void test01() {
        List<Employee> employees = new ArrayList<>();
        Map<Integer, Employee> collect = employees.stream().collect(Collectors.toMap(x -> x.getId(), x -> x));
        Map<Integer, Map<Integer, Employee>> a = new HashMap<>();
        Map<Integer, Map<Integer, Employee>> b = employees.stream().collect(Collectors.groupingBy(Employee::getAge,LinkedHashMap::new, Collectors.toMap(Employee::getId, Function.identity())));

    }

//    public void stream01(int a, int b, )
}
