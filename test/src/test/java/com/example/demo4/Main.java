package com.example.demo4;

public class Main {
    static {
        System.out.println("static");
    }

    public Main() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println("main");
    }
}
