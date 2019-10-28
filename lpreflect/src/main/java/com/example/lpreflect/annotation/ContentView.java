package com.example.lpreflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//表示该注解作用在类上
@Retention(RetentionPolicy.RUNTIME)//该注解在运行期
public @interface ContentView {
    int value();
}
