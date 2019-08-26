package com.example.lp.lpdesignpatterns.InterfaceObject;

public class TestInterface {
    public static void main(String[] args) {
        Clazz clazz=new Clazz();
        Clazz2 clazz2=new Clazz2();
        Clazz3 clazz3=new Clazz3();
        Clazz4 clazz4=new Clazz4();


        clazz.Method1("clazz");
        clazz.Method2("clazz");
        clazz2.Method1("clazz2");
        clazz2.Method2("clazz2");
        clazz3.Method1("clazz3");
        clazz3.Method2("clazz3");
        clazz3.Method3();

        clazz4.Method1("clazz4");
        clazz4.Method2("clazz4");
        clazz4.Method3();

        interfaceObject interfaceObject= new interfaceObject() {
            @Override
            public void Method1(String messge) {

            }

            @Override
            public void Method2(String messge) {

            }
        };
        interfaceObject2 interfaceObject2=  new interfaceObject2() {
            @Override
            public void Method1(String messge) {
                System.out.println("在这里处理Method2"+messge);
            }

            @Override
            public void Method2(String messge) {
                System.out.println("在这里处理Method2"+messge);
            }

            @Override
            public void Method3() {
                System.out.println("在这里处理Method3");
            }
        };

        abstractObject abstractObject=  new abstractObject() {
            @Override
            public void Method2(String messge) {
                System.out.println("在这里处理Method2"+messge);

            }
        };

        abstractObject2 abstractObject2=    new abstractObject2() {
            @Override
            public void Method2(String messge) {
                System.out.println("在这里处理Method2"+messge);

            }
        };
        abstractObject.Method1("abstractObject");
        abstractObject.Method2("abstractObject");
        abstractObject2.Method2("abstractObject2");
        abstractObject2.Method1("abstractObject2");
        interfaceObject.Method1("interfaceObject");
        interfaceObject.Method2("interfaceObject");
        interfaceObject2.Method1("interfaceObject2");
        interfaceObject2.Method2("interfaceObject2");



    }
}
