package com.example.lp.lpdesignpatterns.InterfaceObject;

public class Clazz2 extends abstractObject   {
    public Clazz2() {
        System.out.println("我是Clazz2，继承了abstractObject");
    }

    @Override
    public void Method2(String messge) {
        System.out.println("我是Clazz2的---method2---方法："+messge);

    }
}
