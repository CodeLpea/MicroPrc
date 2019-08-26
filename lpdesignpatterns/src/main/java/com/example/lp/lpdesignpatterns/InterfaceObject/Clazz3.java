package com.example.lp.lpdesignpatterns.InterfaceObject;

public class Clazz3 implements interfaceObject2   {
    public Clazz3() {
        System.out.println("我是Clazz3，实现了interfaceObject2");
    }

    @Override
    public void Method1(String messge) {
        System.out.println("我是Clazz3的---method1---方法："+messge);

    }

    @Override
    public void Method2(String messge) {
        System.out.println("我是Clazz3的---method2---方法："+messge);
        Method3();

    }

    @Override
    public void Method3() {
        System.out.println("我是Clazz3的---method3---方法：");
    }
}
