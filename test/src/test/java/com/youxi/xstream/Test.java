package com.youxi.xstream;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Test {

	@org.junit.Test
	public void test01() {
		ArrayList<Integer> objects = new ArrayList<>();
		objects.add(1);
		objects.add(2);
		objects.add(3);
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		A a = A.createA(111, objects, map);

		XStream xStream = new XStream();//需要XPP3库
		xStream.autodetectAnnotations(true);
		String s = xStream.toXML(a);
		System.out.println(s);
		A aaa = (A) xStream.fromXML(s);
		System.out.println(aaa);

	}

	@org.junit.Test
	public void test02() {
		XstreamTest xstreamTest = new XstreamTest();
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		xstreamTest = XstreamTest.create("dsfa", map);
		XStream xStream = new XStream();//需要XPP3库
		xStream.autodetectAnnotations(true);
		String s = xStream.toXML(xstreamTest);
		System.out.println(s);
	}
}
