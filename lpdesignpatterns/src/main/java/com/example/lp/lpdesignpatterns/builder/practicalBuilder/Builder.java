package com.example.lp.lpdesignpatterns.builder.practicalBuilder;

/**
 * 抽象Builder
 * */
public abstract class Builder {
    //设置核心
    public abstract Builder setCore(String core);
    //设置屏幕
    public abstract Builder setScreen(String screen);
    //设置产品名
    public abstract Builder setName(String name);
    //返回Computer
    public abstract Computer create();

}
