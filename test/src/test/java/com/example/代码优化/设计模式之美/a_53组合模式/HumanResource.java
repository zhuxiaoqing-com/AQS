package com.example.代码优化.设计模式之美.a_53组合模式;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/8 19:38
 * @Description:
 */
public abstract class HumanResource {
	protected long id;
	protected double salary;

	public HumanResource(long id) {
		this.id = id;
	}

	public abstract double calculateSalary();
}
