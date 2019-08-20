package com.example.lp.lpdesignpatterns.strategyMode;
/**
 * 策略模式测试类
* */
public class TeatStrategy {
    public static void main(String[] args) {
        ChaseOperating chaseOperating=new ChaseOperating(new MovieStragety());
        chaseOperating.chase();
        chaseOperating.setChaseStragety(new EattingStragety()).chase();
        chaseOperating.setChaseStragety(new ShoppingStragety()).chase();

    }
}
