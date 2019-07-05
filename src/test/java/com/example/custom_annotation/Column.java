package com.example.custom_annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface Column {
    FieldType fieldType();

    String commit() default "";

    boolean allowNull() default false;

    boolean autoIncrement() default false;

    int size() default 0;

    String colName() default "";
}
