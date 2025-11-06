package com.youxi.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class XstreamTest {
	private String moduleName;

	@XStreamAlias("env")
	private TreeMap<Integer, Integer> envSource;

	@XStreamOmitField
	private Map<Integer, Integer> env;

	public XstreamTest() {
	}

	public static XstreamTest create(String moduleName, TreeMap<Integer, Integer> map) {
		XstreamTest xstreamTest = new XstreamTest();
		xstreamTest.moduleName = moduleName;
		xstreamTest.env= Collections.unmodifiableMap(map);
		//xstreamTest.env= map;
		xstreamTest.envSource= map;
		return xstreamTest;
	}

	public String getModuleName() {
		return moduleName;
	}


	public Map<Integer, Integer> getEnv() {
		return env;
	}

	/*public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public Map<Integer, Integer> getEnv() {
		return env;
	}

	public void setEnv(TreeMap<Integer, Integer> env) {
		this.env = env;
		this.envSource = env;
	}*/
}
