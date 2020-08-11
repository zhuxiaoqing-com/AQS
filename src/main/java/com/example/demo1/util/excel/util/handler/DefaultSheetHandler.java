package com.example.demo1.util.excel.util.handler;

import com.example.demo1.util.excel.util.SheetObj;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.util.*;


/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/11 15:17
 * @Description:
 */
public class DefaultSheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
	/**
	 * 读取数据
	 */
	private SheetObj obj;

	// 读取行信息
	private List<String> lineRowData;
	private int lastCellIndex;


	public void init(SheetObj obj) {
		this.obj = obj;
	}

	@Override
	public void startRow(int rowNum) {
		System.out.println(rowNum);

		lastCellIndex = -1;
		lineRowData = new ArrayList<>();
	}

	@Override
	public void endRow(int rowNum) {
		System.out.println(rowNum);

		obj.getData().add(lineRowData);
	}

	@Override
	public void cell(String cellReference, String formattedValue, XSSFComment comment) {
		System.out.println("cellReference: " + cellReference + "  formattedValue:  " + formattedValue + "   comment:  " + comment);

		int currIndex = getCellIndex(cellReference);
		int realLastsIndex = currIndex - 1;
		for (; lastCellIndex < realLastsIndex; lastCellIndex++) {
			lineRowData.add("");
		}
		lineRowData.add(formattedValue);
		lastCellIndex = currIndex;
	}

	@Override
	public void headerFooter(String text, boolean isHeader, String tagName) {
	}

	/**
	 * 转换表格引用为列编号
	 *
	 * @param cellReference 列引用
	 * @return 表格列位置，从0开始算
	 */
	public int getCellIndex(String cellReference) {
		String ref = cellReference.replaceAll("\\d+", "");
		int num = 0;
		int result = 0;
		for (int i = 0; i < ref.length(); i++) {
			char ch = cellReference.charAt(ref.length() - i - 1);
			num = (int) (ch - 'A' + 1);
			num *= Math.pow(26, i);
			result += num;
		}
		return result - 1;
	}
}
