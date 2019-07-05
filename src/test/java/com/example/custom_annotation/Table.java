package com.example.custom_annotation;

import javax.persistence.UniqueConstraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    String primaryKey() default "id";

    String tableName() default "";

    Index[] indexes() default {};

}