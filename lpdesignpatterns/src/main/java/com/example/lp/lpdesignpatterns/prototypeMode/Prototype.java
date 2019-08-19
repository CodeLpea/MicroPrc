package com.example.lp.lpdesignpatterns.prototypeMode;

import java.util.ArrayList;
/**
* 原型模式测试对象
 * 实现Cloneable
 * 重写clone方法
* */
public class Prototype implements Cloneable {
    private String Name;
    private ArrayList<String> Text=new ArrayList<>();

    public Prototype(String name, ArrayList<String> text) {
        Name = name;
        Text = text;
    }

    @Override
    protected Prototype clone()  {
        Prototype prototype=null;
        try{
            prototype= (Prototype) super.clone();

        }catch (Exception e){
        }
        return prototype;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<String> getText() {
        return Text;
    }

    public void setText(ArrayList<String> text) {
        Text = text;
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "Name='" + Name + '\'' +
                ", Text=" + Text +
                '}';
    }
}
