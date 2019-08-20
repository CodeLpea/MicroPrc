package com.example.lp.lpdesignpatterns.observerMode;
/**
 * 抽象被观察者
 * */
public interface Observable {
    void add(Observer observer);
    void remove(Observer observer);
    void notify(String messege);
}
