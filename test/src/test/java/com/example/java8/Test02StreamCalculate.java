package com.example.java8;

import com.example.java8.entity.Employee;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test02StreamCalculate {
    @Test
    public void test01() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,1));
        employees.add(new Employee(1,2));
        employees.add(new Employee(1,3));
		IntSummaryStatistics intSummaryStatistics = employees.stream().mapToInt(Employee::getAge).summaryStatistics();
		System.out.println(intSummaryStatistics); // IntSummaryStatistics{count=3, sum=6, min=1, average=2.000000, max=3}
		// BigDecimal bb =employees.stream().map(Employee::getAge).reduce(BigDecimal.ZERO,BigDecimal::add);
	}



}