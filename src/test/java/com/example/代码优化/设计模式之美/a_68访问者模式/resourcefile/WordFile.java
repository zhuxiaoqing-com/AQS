package com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile;

import com.example.代码优化.设计模式之美.a_68访问者模式.visitor.Visitor;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 15:38
 * @Description:
 */
public class WordFile extends ResourceFile {
	public WordFile(String filePath) {
		super(filePath);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
