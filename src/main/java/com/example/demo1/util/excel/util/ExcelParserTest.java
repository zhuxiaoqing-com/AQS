package com.example.demo1.util.excel.util;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/10 20:44
 * @Description:
 */
public class ExcelParserTest {

	public static void main(String[] args) {
		String path = "F:/mh/game_server-develop/tools/excelGenerate/excel/测试excel.xlsx";

	/*	long start1 = System.currentTimeMillis();
		OPCPackage opcPackage = OPCPackage.open(new File(path), PackageAccess.READ);
		XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
		for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); ++sheetNum) {
			XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		}
		long end1 = System.currentTimeMillis();
		System.out.println(end1 - start1);*/

		long start = System.currentTimeMillis();
		IExcelParser parser = new ExcelNormalParser();
		//IExcelParser parser = new ExcelEventParser();
		List<SheetObj> parse = parser.parse(new File(path), sheetName -> true);
		long end = System.currentTimeMillis();
		System.out.println(end - start);


	}

	public static void main1(String[] args) throws Exception {
		String path = "F:\\mh\\game_server-develop\\tools\\excelGenerate\\excel\\测试excel.xlsx";

		long start1 = System.currentTimeMillis();
		OPCPackage opcPackage = OPCPackage.open(new File(path), PackageAccess.READ);
		XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
		for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); ++sheetNum) {
			XSSFSheet sheet = workbook.getSheetAt(sheetNum);
		}
		long end1 = System.currentTimeMillis();
		System.out.println(end1 - start1);

	}
}
