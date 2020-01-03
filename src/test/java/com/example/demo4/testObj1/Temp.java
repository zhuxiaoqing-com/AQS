package com.example.demo4.testObj1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Temp extends Father {
    private int id;
    private String name;
    private Child child;

    public Temp(int id, String name, Child child) {
        this.id = id;
        this.name = name;
        this.child = child;
    }
}
