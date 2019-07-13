package com.example.demo1.util.excel.excelobj;

/**
 * 行为表数据类
 */
public class BehaviorObj {
    private int id;
    private String desc;

    public BehaviorObj() {
    }

    public BehaviorObj(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
