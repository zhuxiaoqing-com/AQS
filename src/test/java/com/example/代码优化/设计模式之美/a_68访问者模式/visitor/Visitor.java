package com.example.代码优化.设计模式之美.a_68访问者模式.visitor;

import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.PPTFile;
import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.PdfFile;
import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.WordFile;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 15:38
 * @Description:
 */
public interface Visitor {
	void visit(PdfFile file);
	void visit(PPTFile file);
	void visit(WordFile file);
}
