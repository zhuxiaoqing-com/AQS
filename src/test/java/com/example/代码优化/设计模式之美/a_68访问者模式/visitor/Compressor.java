package com.example.代码优化.设计模式之美.a_68访问者模式.visitor;

import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.PPTFile;
import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.PdfFile;
import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.WordFile;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 15:47
 * @Description:
 */
public class Compressor implements Visitor {
	@Override
	public void visit(PdfFile file) {
		System.out.println("Compressor  PdfFile");
	}

	@Override
	public void visit(PPTFile file) {
		System.out.println("Compressor  PPTFile");
	}

	@Override
	public void visit(WordFile file) {
		System.out.println("Compressor  WordFile");
	}
}
