package com.example.lp.lpdesignpatterns.InterfaceObject;

public abstract class abstractObject implements interfaceObject {
    public abstractObject() {
        System.out.println("我是abstractObject，实现了interfaceObject");
    }

    @Override
    public void Method1(String messge) {
        System.out.println("我是abstractObject的---Method1---方法："+messge);

    }
}
