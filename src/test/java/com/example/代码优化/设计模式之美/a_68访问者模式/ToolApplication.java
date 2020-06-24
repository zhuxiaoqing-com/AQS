package com.example.代码优化.设计模式之美.a_68访问者模式;

import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.PPTFile;
import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.PdfFile;
import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.ResourceFile;
import com.example.代码优化.设计模式之美.a_68访问者模式.resourcefile.WordFile;
import com.example.代码优化.设计模式之美.a_68访问者模式.visitor.Extractor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/24 15:54
 * @Description:
 */
public class ToolApplication {
	public static void main(String[] args) {
		List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);

		Extractor extractor = new Extractor();
		for (ResourceFile resourceFile : resourceFiles) {
			resourceFile.accept(extractor);
		}
	}

	private static List<ResourceFile> listAllResourceFiles(String resourceDir) {
		List<ResourceFile> resourceFiles = new ArrayList<>();
		resourceFiles.add(new PdfFile("a.pdf"));
		resourceFiles.add(new WordFile("a.pdf"));
		resourceFiles.add(new PPTFile("a.pdf"));
		return resourceFiles;
	}
}
