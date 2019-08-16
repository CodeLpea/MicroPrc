package com.example.lp.lpdesignpatterns.singleTon;
/**
 * 饿汉单例
 * */
public class HungrySinglemode {
    private static HungrySinglemode instance =new HungrySinglemode();
    private HungrySinglemode(){

    }
    private static HungrySinglemode getInstance(){
        return instance;
    }
}
