package com.example.common.classscan.entity;

import java.util.ArrayList;
import java.util.List;

public class PackageObj<T> {
	private String name;
	private List<PackageObj<T>> packageObjList = new ArrayList<>();

	private List<Class<T>> classObjList = new ArrayList<>();

	public PackageObj() {
	}

	public PackageObj(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PackageObj<T>> getPackageObjList() {
		return packageObjList;
	}

	public void setPackageObjList(List<PackageObj<T>> packageObjList) {
		this.packageObjList = packageObjList;
	}

	public List<Class<T>> getClassObjList() {
		return classObjList;
	}

	public void setClassObjList(List<Class<T>> classObjList) {
		this.classObjList = classObjList;
	}
}
