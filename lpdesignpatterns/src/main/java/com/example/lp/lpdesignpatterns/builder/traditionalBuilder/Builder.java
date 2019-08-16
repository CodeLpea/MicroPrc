package com.example.lp.lpdesignpatterns.builder.traditionalBuilder;
/**
 * 抽象Builder
 * */
public abstract class Builder {
    //设置核心
    public abstract void setCore(String core);
    //设置屏幕
    public abstract void setScreen(String screen);
    //设置产品名
    public abstract void setName();
    //返回Computer
    public abstract Computer create();

}
