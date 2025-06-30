package com.example.entity;

public class Reload implements ReloadMBean {
    @Override
    public String reload() {
        System.out.println("reload!!!!!!!!!!!!!!!!!!!!!!!");
        return "reload success";
    }
}
