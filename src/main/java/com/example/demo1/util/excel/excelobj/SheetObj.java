package com.example.demo1.util.excel.excelobj;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class SheetObj<T> {
    // 标题
    private String[] titles;
    private List<T> dataList;
}
