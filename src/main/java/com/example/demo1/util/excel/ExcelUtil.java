package com.example.demo1.util.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

    private static final String FILE_PATH = "D:/行为表.xls";

    public static void exportExcel() throws IOException {
        //1.在内存中创建一个excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //2.创建工作簿
        HSSFSheet gainSheet = hssfWorkbook.createSheet("gain");
        HSSFSheet costSheet = hssfWorkbook.createSheet("cost");
        //3.创建标题行
        HSSFRow titlerRow = gainSheet.createRow(0);
        titlerRow.createCell(0).setCellValue("类型id");
        titlerRow.createCell(1).setCellValue("类型中文");


        //4.遍历数据,创建数据行
        for (GainType costType : GainType.values()) {
            //获取最后一行的行号
            int lastRowNum = gainSheet.getLastRowNum();
            HSSFRow dataRow = gainSheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(costType.getId());
            dataRow.createCell(1).setCellValue(costType.getDesc());
        }

        for (CostType costType : CostType.values()) {
            //获取最后一行的行号
            int lastRowNum = costSheet.getLastRowNum();
            HSSFRow dataRow = costSheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(costType.getId());
            dataRow.createCell(1).setCellValue(costType.getDesc());
        }

        //6.获取输出流对象
        FileOutputStream outputStream = new FileOutputStream(FILE_PATH);
        //10.写出文件,关闭流
        hssfWorkbook.write(outputStream);
        hssfWorkbook.close();
    }


    @Test
    public void test01() throws IOException {
        exportExcel();
    }
}
