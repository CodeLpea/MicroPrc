package com.example.lp.lpdesignpatterns.observerMode;
/**
 * Boy
 * 具体的观察者实现
 * */
public class Boy implements Observer{
    private String name;

    public Boy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String messege) {
        System.out.println(name+"收到了"+messege+"消息");
    }
}
