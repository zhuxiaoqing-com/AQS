package com.example.entity;

//实体类需要继承 MBean 接口类，而接口类的命名规则是 「实体类名+MBean」,这是固定的规则。
public interface ReloadMBean {
    public String reload();
}
