package com.example.demo3;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class Test4Java8 {
    public static void main(String[] args) {
        Car.create(Car::new);
    }
}


class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided" + car.toString());
    }
}

class Annotations {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    public @interface NonEmpty {
    }

    public int s;
    public static class Holder<@NonEmpty T> extends @NonEmpty Object {
        public void method(int s) throws @NonEmpty Exception {
        }
    }
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        final Holder<String> holder = new @NonEmpty Holder<String>();
        @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
    }
}