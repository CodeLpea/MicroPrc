package com.example.lpreflect.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClickEventBase(listenerSetter = "setOnClickListener",listenerType = View.OnClickListener.class,callBackListener = "onClick")
public @interface OnClick {
    int[] value();
}
