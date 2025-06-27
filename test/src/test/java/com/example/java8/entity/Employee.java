package com.example.java8.entity;

public class Employee {
    int id;
    int age;

	public Employee(int id, int age) {
		this.id = id;
		this.age = age;
	}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
