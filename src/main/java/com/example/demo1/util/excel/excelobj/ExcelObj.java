package com.example.demo1.util.excel.excelobj;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

public class ExcelObj {
    private String excelName;
    private List<SheetObj> list = new ArrayList<>();


    public List<SheetObj> getList() {
        return list;
    }

    public void setList(List<SheetObj> list) {
        this.list = list;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }
}
