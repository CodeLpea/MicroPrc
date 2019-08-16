package com.example.lp.lpdesignpatterns.builder.simpleBuilder;
/**
 * 抽象电脑
 * */
public abstract class Computer {
    protected String Core;
    protected String Name;
    protected String Screen;

    public Computer() {
    }

    public Computer setCore(String core) {
        Core = core;
        return this;
    }

    public Computer setName(String name) {
        Name=name;
        return this;
    }

    public Computer setScreen(String screen) {
        Screen = screen;
        return this;
    }
    public Computer build(){
        return this;
    }


    @Override
    public String toString() {
        return "Computer{" +
                "Core='" + Core + '\'' +
                ", Name='" + Name + '\'' +
                ", Screen='" + Screen + '\'' +
                '}';
    }
}
