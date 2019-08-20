package com.example.lp.lpdesignpatterns.proxyMode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理实现
 * */
public class DynamicProxy implements InvocationHandler {
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理了"+object.getClass().getName().substring(42,50)+"实现：");

        Object result=method.invoke(object,args);//调用被代理的对象的方法

        return result;
    }
}
