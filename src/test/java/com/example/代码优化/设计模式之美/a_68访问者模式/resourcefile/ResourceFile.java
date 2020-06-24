package com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile;

import com.example.代码优化.设计模式之美.a_68访问者模式.visitor.Visitor;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 15:36
 * @Description:
 */
public abstract class ResourceFile {
	protected String filePath;

	public ResourceFile(String filePath) {
		this.filePath = filePath;
	}

	abstract public void accept(Visitor visitor);

	//abstract public void accept();
}
