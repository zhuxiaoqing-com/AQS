package com.example.代码优化.设计模式之美.a_53组合模式;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/8 19:40
 * @Description:
 */
public class Employee extends HumanResource{

	public Employee(long id) {
		super(id);
	}

	@Override
	public double calculateSalary() {
		return salary;
	}
}
