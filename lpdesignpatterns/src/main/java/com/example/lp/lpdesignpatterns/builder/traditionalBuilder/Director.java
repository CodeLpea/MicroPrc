package com.example.lp.lpdesignpatterns.builder.traditionalBuilder;
/**
 * 负责构造Computer
 * */
public class Director {
    public Builder builder=null;
    public Director(Builder builder){
        this.builder=builder;
    }

    public void construct(String core,String Screen){
        builder.setCore(core);
        builder.setScreen(Screen);
        builder.setName();
    }

}
