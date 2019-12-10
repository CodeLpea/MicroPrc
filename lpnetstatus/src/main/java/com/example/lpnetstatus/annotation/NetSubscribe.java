package com.example.lpnetstatus.annotation;

import com.example.lpnetstatus.annotation.type.Mode;
import com.example.lpnetstatus.annotation.type.ThreadWhere;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 网路状态监听注解
 * NetSubscribe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NetSubscribe {
    Mode mode() default Mode.AUTO;
    ThreadWhere threadwhere() default ThreadWhere.BACKGURAND;

}
