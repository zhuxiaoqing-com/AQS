package com.example.demo1.util.excel.readExcel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/8 15:20
 * @Description:
 */
public class WriteExcel {
	private static final String XLS = "xls";
	private static final String XLSX = "xlsx";

	private static final int sheetHead = 5;
	private static final int sheetFirstBody = 6;

	public void writeExcel(String fileName, List<String> sheet, Map<String, String> fillDataMap) {
		Workbook workbook = null;
		FileInputStream inputStream = null;
		FileOutputStream excelFileOutPutStream = null;

		try {
			// 获取excel 后缀名
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			// 获取Excel文件
			File excelFile = new File(fileName);
			if (!excelFile.exists()) {
				System.out.println("指定的Excel文件不存在！");
				return;
			}
			// 获取Excel工作簿
			inputStream = new FileInputStream(excelFile);
			workbook = getWorkbook(inputStream, fileType);

			// 读取excel中的数据
			fillExcel(workbook, sheet, fillDataMap);
			//写入数据
			 String FILE_PATH = "D:/行为表.xlsx";
			excelFileOutPutStream = new FileOutputStream(FILE_PATH);
			workbook.write(excelFileOutPutStream);
			excelFileOutPutStream.flush();
			excelFileOutPutStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("关闭数据流出错！错误信息：");
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (excelFileOutPutStream != null) {
					excelFileOutPutStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void fillExcel(Workbook workbook, List<String> sheets, Map<String, String> fillDataMap) {
		for (String sheetName : sheets) {
			Sheet sheet = workbook.getSheet(sheetName);
			int rowEnd = sheet.getPhysicalNumberOfRows();
			for (int i = sheetFirstBody; i <= rowEnd; i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				Cell id = row.getCell(0);
				String idStr = convertCellValueToString(id);

				String newData = fillDataMap.get(idStr);
				if (newData == null) {
					continue;
				}
				Cell cell = row.getCell(11);
				if (cell == null) {
					cell = row.createCell(11);
				}
				cell.setCellValue(newData);
				int a = 1;
			}
		}

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
