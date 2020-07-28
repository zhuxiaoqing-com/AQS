package com.example.demo1.util.excel.readExcel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/8 15:20
 * @Description:
 */
public class ReadExcel {
	private static final String XLS = "xls";
	private static final String XLSX = "xlsx";

	private static final int sheetHead = 5;
	private static final int sheetFirstBody = 6;

	public Map<String, String> readExcel(String fileName, List<String> sheet) {
		Workbook workbook = null;
		FileInputStream inputStream = null;

		try {
			// 获取excel 后缀名
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			// 获取Excel文件
			File excelFile = new File(fileName);
			if (!excelFile.exists()) {
				System.out.println("指定的Excel文件不存在！");
				return null;
			}
			// 获取Excel工作簿
			inputStream = new FileInputStream(excelFile);
			workbook = getWorkbook(inputStream, fileType);

			// 读取excel中的数据
			Map<String, String> map = parseExcel(workbook, sheet);
			return map;
		} catch (Exception e) {
			System.out.println("关闭数据流出错！错误信息：" + e.getMessage());
			return null;
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private Map<String, String> parseExcel(Workbook workbook, List<String> sheets) {
		Map<String, String> map = new HashMap<>();
		for (String sheetName : sheets) {
			Sheet sheet = workbook.getSheet(sheetName);
			int rowEnd = sheet.getPhysicalNumberOfRows();
			for (int i = sheetFirstBody; i <= rowEnd; i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				Cell id = row.getCell(0);
				Cell data = row.getCell(2);
				String idStr = convertCellValueToString(id);
				String dataStr = convertCellValueToString(data);
				if(idStr == null || idStr.isEmpty()) {
					continue;
				}
				if(sheetName.equals("辅助品表")) {
					dataStr = "6;7";
				}
				map.put(idStr, dataStr);
			}
		}
		return map;
	}

	public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
		Workbook workbook = null;
		if (fileType.equalsIgnoreCase(XLS)) {
			workbook = new HSSFWorkbook(inputStream);
		} else if (fileType.equalsIgnoreCase(XLSX)) {
			workbook = new XSSFWorkbook(inputStream);
		}
		return workbook;
	}


	/**
	 * 将单元格内容转换为字符串
	 *
	 * @param cell
	 * @return
	 */
	private static String convertCellValueToString(Cell cell) {
		if (cell == null) {
			return null;
		}
		String returnValue = null;
		switch (cell.getCellTypeEnum()) {
			case NUMERIC: //数字
				Double doubleValue = cell.getNumericCellValue();

				// 格式化科学计数法，取一位整数
				DecimalFormat df = new DecimalFormat("0");
				returnValue = df.format(doubleValue);
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
				returnValue = cell.getCellFormula();
				break;
			case ERROR: // 故障
				break;
			default:
				break;
		}
		return returnValue;
	}


}
