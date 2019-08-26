package com.example.lp.lpdesignpatterns.InterfaceObject;

public class Clazz extends abstractObject {
    public Clazz() {
        System.out.println("我是Clazz，继承了abstractObject");
    }
    @Override
    public void Method2(String messge) {
        System.out.println("我是Clazz的---Method2---方法："+messge);

    }
}
