package com.example.lp.lpdesignpatterns.strategyMode;

/**
 * 策略操作类
 */
public class ChaseOperating {
    private ChaseStragety chaseStragety;

    public ChaseOperating() {
        //默认为看电影
        chaseStragety = new MovieStragety();
    }

    public void chase() {
        chaseStragety.chase();
    }

    public ChaseOperating(ChaseStragety chaseStragety) {
        this.chaseStragety = chaseStragety;
    }


    /*
     * 使用建议构建者，返回实例，简化构建成本
     * */
    public ChaseOperating setChaseStragety(ChaseStragety chaseStragety) {
        this.chaseStragety = chaseStragety;
        return this;
    }
}
