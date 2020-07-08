package com.example.demo1.util.excel.readExcel;

import org.apache.poi.openxml4j.util.ZipSecureFile;

import java.util.Arrays;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/8 15:47
 * @Description:
 */
public class ReadExcelTest {
	public static void main(String[] args) {
		ReadExcel readExcel = new ReadExcel();
		ZipSecureFile.setMinInflateRatio(-1.0d);
		Map<String, String> fillDatas = readExcel.readExcel("D:/aaa/道具.xlsx", Arrays.asList("装备表", "武器表", "辅助品表"));
		WriteExcel writeExcel = new WriteExcel();
		writeExcel.writeExcel("D:/aaa/道具1.xlsx", Arrays.asList("道具总表"), fillDatas);

	}
}
