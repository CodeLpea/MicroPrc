package com.example.lp.lpdesignpatterns.prototypeMode;

import java.util.ArrayList;
/**
 * 测试原型模式
 * */
public class TestProtorype {
    public static void main(String[] args) {
        System.out.println("测试原型模式");
        ArrayList<String> prototypeList=new ArrayList<>();
        prototypeList.add("list1");
        prototypeList.add("list2");
        Prototype prototype=new Prototype("origin",prototypeList);
        System.out.printf("origin:");
        System.out.println(prototype.toString());
        Prototype newPrototype=prototype.clone();
        newPrototype.setName("newPrototype");
        newPrototype.getText().add("newPrototypeList3");
        System.out.println("newPrototype");
        System.out.println(newPrototype.toString());
    }
}
