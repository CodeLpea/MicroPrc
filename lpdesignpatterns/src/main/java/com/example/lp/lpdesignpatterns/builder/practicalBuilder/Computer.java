package com.example.lp.lpdesignpatterns.builder.practicalBuilder;
/**
 * 抽象电脑
 * */
public abstract class Computer {
    protected String Core;
    protected String Name;
    protected String Screen;

    public Computer() {
    }

    public void setCore(String core) {
        Core = core;
    }

    public void setName(String name) {
        Name=name;
    }

    public void setScreen(String screen) {
        Screen = screen;
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
