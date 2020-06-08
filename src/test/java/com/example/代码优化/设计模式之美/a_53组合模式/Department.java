package com.example.代码优化.设计模式之美.a_53组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/8 19:52
 * @Description:
 */
public class Department extends HumanResource {
	private List<HumanResource> subNodes = new ArrayList<>();

	public Department(long id) {
		super(id);
	}

	@Override
	public double calculateSalary() {
		return salary;
	}

	public void addSubNode(HumanResource hr) {
		subNodes.add(hr);
		this.salary += hr.salary;
	}
}
