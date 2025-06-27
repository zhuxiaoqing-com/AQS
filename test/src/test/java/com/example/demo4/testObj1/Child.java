package com.example.demo4.testObj1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Child extends Father {
    int id;
    String name;

    /*public Child() {
    }*/

    public Child(int id, String name) {
		//String print = print();
		//super(print);
        this.id = id;
        this.name = name;
        if(true || print()&&print()) {
			System.out.println("ss");
		}
    }

    public static boolean print() {
		System.out.println("测试");
		return true;
	}
}
