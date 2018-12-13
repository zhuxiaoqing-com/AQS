package com.example.java8;

import org.junit.Test;

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
        stream01(2, 4,
                (x, y)-> x+y > 3,
                (x,y) -> x+y,
                x-> x>5);
    }

    /**
     *
     * @param oldParam 之前已经保存的数据
     * @param addParam 当前的参数
     * @param conditionFun 条件函数
     * @param calculateFun 处理函数
     * @param successFun 完成的条件值
     */
    public<O> void stream01(O oldParam, O addParam,
                               BiPredicate<O, O> conditionFun,
                            BinaryOperator<O> calculateFun,
                               Predicate<O> successFun) {
        // 是否已经完成
        if(successFun.test(oldParam)) {
            // 执行成功逻辑
            System.out.println("成功 ：oldParam = " + oldParam);
            return;
        }
        // 判断条件是否满足
        if(conditionFun.test(oldParam, addParam)) {
            // 进行处理
            oldParam = calculateFun.apply(oldParam, addParam);
            System.out.println("失败 ：oldParam = " + oldParam);
        }

        // 任务
        // 1.将英雄升星到5星这个任务完成

        //    1.英雄升星完毕后调用检测任务是否能完成

        //      1.1 检测所有升星任务是否能完成任务
                // 当前这个任务  进度   data 1
                // 1.2
                // if(mode==1)
              /*  {
                this.data=0;
                }
                */

    }
}
