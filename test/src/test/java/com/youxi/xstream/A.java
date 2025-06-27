package com.youxi.xstream;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class A {
	 static private org.slf4j.Logger logger  = org.slf4j.LoggerFactory.getLogger(A.class);

	private int a;

	private List<Integer> list;
	@XStreamAlias("map")
	private TreeMap<Integer, Integer> mapSource;

	@XStreamOmitField
	private Map<Integer, Integer> map;


	public A() {
	}


	public static A createA(int a, List<Integer> list, TreeMap<Integer, Integer> map) {
		A a1 = new A();
		a1.a = a;
		a1.list = list;
		a1.mapSource = map;
		a1.map = Collections.unmodifiableMap(a1.mapSource);
		return a1;
	}

	public int getA() {
		return a;
	}

	public List<Integer> getList() {
		return list;
	}

	public Map<Integer, Integer> getMap() {
		return map;
	}

	@Override
	public String toString() {
		return "A{" +
				"a=" + a +
				", list=" + list +
				", mapSource=" + mapSource +
				", map=" + map +
				'}';
	}
}
