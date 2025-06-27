package com.example.custom_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface Index {
    String name() default "";

    String[] columnNames();

    boolean unique() default false;
}