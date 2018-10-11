package com.example.java8;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Test01Optional {
    @Test
    public void fun01() {
        Optional<Object> empty = Optional.empty();
        empty.get();
    }

    @Test
    public void fun02() {
        Optional<Object> empty = Optional.of(null);
        Optional<Object> empty2 = Optional.ofNullable(null);
    }

    @Test
    public void fun03() {
        String name = "John";
        Optional<String> name1 = Optional.ofNullable(name);
        System.out.println(name1.toString());
    }

    @Test
    public void fun04() {
        String name = "John";
        Optional<String> name1 = Optional.ofNullable(name);
        System.out.println(name1.isPresent());
        Optional<Integer> integer = name1.map((x) -> x.length()).map((x)-> x.hashCode());
        System.out.println(integer);
    }

    @Test
    public void fun05() {
        String name = "John";
        Optional<String> name1 = Optional.ofNullable(null);
        System.out.println(name1.orElse("aaa"));
    }

    @Test
    public void fun06() {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i <= 10; i++) {
            if(i%2 ==0)
                set.add(i);
        }
        System.out.println(set);
        List<Integer> rankRanges = set.stream().sorted((x,y)->x>y?1:-1).collect(Collectors.toList());
        System.out.println(rankRanges);
        int rank = 4;
        int prevKey = 9999;
        for (int rankRange : rankRanges) {
            if (rank > rankRange) {
                break;
            }
            prevKey = rankRange;
        }

        System.out.println(prevKey);
    }
}
