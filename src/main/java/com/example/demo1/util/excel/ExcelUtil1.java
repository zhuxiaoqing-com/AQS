package com.example.demo1.util.excel;

import com.example.demo1.util.excel.excelobj.ExcelObj;
import com.example.demo1.util.excel.excelobj.SheetObj;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil1 {
    public static void exportExcel(ExcelObj excelObj) throws IOException {
        if (excelObj == null) {
            return;
        }
        //1.在内存中创建一个excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        List<SheetObj> list = excelObj.getList();
        for(SheetObj<T> sheetObj)
        for()
        //2.创建工作簿
        HSSFSheet sheet = hssfWorkbook.createSheet("Cost");
        //3.创建标题行
        HSSFRow titlerRow = sheet.createRow(0);
        titlerRow.createCell(0).setCellValue("类型id");
        titlerRow.createCell(1).setCellValue("类型中文");


        CostType[] values = CostType.values();

        //4.遍历数据,创建数据行
        for (CostType costType : values) {
            //获取最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(costType.getId());
            dataRow.createCell(1).setCellValue(costType.getDesc());
        }

        //6.获取输出流对象
        FileOutputStream outputStream = new FileOutputStream(excelObj.getExcelName());
        //10.写出文件,关闭流
        hssfWorkbook.write(outputStream);
        hssfWorkbook.close();
    }


    @Test
    public void test01() throws IOException {
        exportExcel();
    }
}
