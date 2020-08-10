package com.example.demo1.util.excel.util;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/10 20:44
 * @Description:
 */
public class ExcelParserTest {

	public static void main(String[] args) throws IOException, ExcelParser.ParseException, InvalidFormatException {
		String path = "F:\\mh\\game_server-develop\\tools\\excelGenerate\\excel\\测试excel.xlsx";
		ExcelParser parser = new ExcelParser().parse(new FileInputStream(path), "cfg_advance_info");
		List<String[]> datas = parser.getDatas();
		System.out.println(datas);

	}
}
