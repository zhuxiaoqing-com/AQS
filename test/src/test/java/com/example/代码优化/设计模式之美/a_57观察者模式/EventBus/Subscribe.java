package com.example.代码优化.设计模式之美.a_57观察者模式.EventBus;

import com.google.common.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/11 10:09
 * @Description:
 */

/**
 * guava中的net包目前提供的功能较少，
 * 而且大多类都标注了@Beta的注解，在guava中标记Beta注解表示这个类还不稳定，
 * 有可能在以后的版本中变化，或者去掉，所以不建议大量使用，这里也是只做简单的介绍。
 */
@Beta //  表明一个公用API的未来版本是受不兼容变更或删除限制的 拥有这个注释标志的API不受任何兼容性保证
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
}
