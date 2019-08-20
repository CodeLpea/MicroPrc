package com.example.lp.lpdesignpatterns.strategyMode;
/**
 * 吃饭策略实现
 * */
public class EattingStragety implements ChaseStragety {
    @Override
    public void chase() {
        System.out.println("带她吃饭");
    }
}
