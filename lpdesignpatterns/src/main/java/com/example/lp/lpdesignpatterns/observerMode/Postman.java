package com.example.lp.lpdesignpatterns.observerMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者的实现
 * 消息通知管理对象
 * */
public class Postman implements Observable {
    private List<Observer> observerList=new ArrayList<>();


    @Override
    public void add(Observer observer) {
        observerList.add(observer);

    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);

    }

    @Override
    public void notify(String messege) {
        for(Observer observer:observerList){
            observer.update(messege);
        }

    }
}
