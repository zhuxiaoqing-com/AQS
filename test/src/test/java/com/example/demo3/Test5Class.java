package com.example.demo3;

/**
 * 验证泛型是否能初始化
 */
public class Test5Class {
    public static void main(String[] args) {
        Test5Class2<String> stringTest5Class2 = new Test5Class2<>();
        System.out.println(stringTest5Class2);
    }
}

class Test5Class2<T> {
    T t;

    Test5Class2() {
        init(t);
    }

    public void init(T t) {
        Class<?> aClass = t.getClass();
        try {
            Object o = aClass.newInstance();
            System.out.println(o.getClass());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}