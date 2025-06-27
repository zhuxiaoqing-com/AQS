package com.example.demo1.util.excel.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/11 14:31
 * @Description:
 */
public class SheetObj {
	private String fileName;
	private String sheetName;
	/**
	 * 第一个list 行数据
	 * 第二个list 列数据
	 */
	private List<List<String>> data  = new ArrayList<>();

	public SheetObj(String fileName, String sheetName) {
		this.fileName = fileName;
		this.sheetName = sheetName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<List<String>> getData() {
		return data;
	}

	public void setData(List<List<String>> data) {
		this.data = data;
	}
}
