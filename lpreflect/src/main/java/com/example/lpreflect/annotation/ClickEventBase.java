package com.example.lpreflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)//作用在另外的注解上
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickEventBase {
    //监听方法OnClickLister或者是OnLongClickLister
    String  listenerSetter();

    //监听的接口 View.OnClickListener
    Class<?> listenerType();

    //监听的回调onClick方法
    String callBackListener();

}
