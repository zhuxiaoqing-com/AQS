package com.example.demo4.testObj1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Temp extends Father {
    private int id;
    private String name;
    private Child child;

    public Temp() {
    }

    public Temp(int id, String name, Child child) {
        this.id = id;
        this.name = name;
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", child=" + child +
                '}';
    }
}
