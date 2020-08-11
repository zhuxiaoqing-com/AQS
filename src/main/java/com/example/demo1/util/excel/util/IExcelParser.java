package com.example.demo1.util.excel.util;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;

/**
 * 解析Excel工具类
 */
public interface IExcelParser {
	public List<SheetObj> parse(File file, Predicate<String> predicate);

}