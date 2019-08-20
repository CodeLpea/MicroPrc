package com.example.lp.lpdesignpatterns.strategyMode;
/**
 * 看电影策略实现
 * */
public class MovieStragety implements ChaseStragety {
    @Override
    public void chase() {
        System.out.println("带她看电影");
    }
}
