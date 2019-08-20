package com.example.lp.lpdesignpatterns.strategyMode;
/**
 * 逛街策略实现
 * */
public class ShoppingStragety implements ChaseStragety {
    @Override
    public void chase() {
        System.out.println("带她逛街");
    }
}
