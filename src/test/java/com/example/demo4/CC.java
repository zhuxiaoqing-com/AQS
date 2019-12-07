package com.example.demo4;

public class CC implements AA, BB {
    @Override
    public void a() {
        AA.super.a();
        BB.super.a();
    }
}
