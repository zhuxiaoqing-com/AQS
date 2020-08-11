package com.example.demo1.util.excel.util;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 解析Excel工具类
 */
public class ExcelNormalParser implements IExcelParser {
	private static final Logger logger = LoggerFactory.getLogger(ExcelNormalParser.class);
	/**
	 * 读取数据
	 */
	private List<SheetObj> sheetObjs = new ArrayList<>();


	public List<SheetObj> parse(File file, Predicate<String> filter) {
		XSSFWorkbook workbook = null;
		try {
			OPCPackage opcPackage = OPCPackage.open(file.getPath(), PackageAccess.READ);
			workbook = new XSSFWorkbook(opcPackage);

			String fileName = file.getName();
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); ++sheetNum) {
				XSSFSheet sheet = workbook.getSheetAt(sheetNum);
				if (!filter.test(sheet.getSheetName())) {
					continue;
				}
				parse(sheet, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sheetObjs;
	}

	private void parse(XSSFSheet sheet, String fileName) {
		SheetObj sheetObj = new SheetObj(fileName, sheet.getSheetName());

		if (sheet.getLastRowNum() <= 0) {
			return;
		}

		for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
			List<String> lineRowList = new ArrayList<>();
			XSSFRow row = sheet.getRow(rowNum);

			if (row.getLastCellNum() <= 0) {
				continue;
			}

			for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
				XSSFCell cell = row.getCell(cellNum);
				lineRowList.add(cellToStringValue(cell));
			}
			sheetObj.getData().add(lineRowList);
		}
		sheetObjs.add(sheetObj);
	}


	public List<SheetObj> getSheetObjs() {
		return sheetObjs;
	}

	/**
	 * 表格转换错误
	 */
	public class ParseException extends Exception {
		private static final long serialVersionUID = -2451526411018517607L;

		public ParseException(Throwable t) {
			super("表格转换错误", t);
		}

	}


	public static String cellToStringValue(XSSFCell cell) {
		if (cell == null) {
			return "";
		}
		String returnValue = "";
		switch (cell.getCellType()) {
			case NUMERIC: //数字
				String value = "";
				try {
					value = String.valueOf(cell.getNumericCellValue());
					if (value.contains("E")) {
						DecimalFormat df = new DecimalFormat("0");
						value = String.valueOf(df.format(cell.getNumericCellValue()));
					}
				} catch (Exception var3) {
					value = String.valueOf(cell.getRichStringCellValue());
				}
				returnValue = value;
				break;
			case STRING: //字符串
				returnValue = cell.getStringCellValue();
				break;
			case BOOLEAN: //布尔
				Boolean booleanValue = cell.getBooleanCellValue();
				returnValue = booleanValue.toString();
				break;
			case BLANK: // 空值
				break;
			case FORMULA: // 公式
				try {
					returnValue = String.valueOf(cell.getNumericCellValue());
				} catch (IllegalStateException e) {
					returnValue = String.valueOf(cell.getRichStringCellValue());
				}
				break;
			case ERROR: // 故障
				break;
			default:
				break;
		}
		return returnValue.trim();
	}

}