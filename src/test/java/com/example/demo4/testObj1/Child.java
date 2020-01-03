package com.example.demo4.testObj1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Child {
    int id;
    String name;

    /*public Child() {
    }*/

    public Child(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
