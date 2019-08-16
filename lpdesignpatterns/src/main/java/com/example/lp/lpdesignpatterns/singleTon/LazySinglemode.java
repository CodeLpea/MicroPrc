package com.example.lp.lpdesignpatterns.singleTon;

/**
 * 懒汉单例模式
 * 优点，只在第一次使用的时候创建对象，减少资源消耗
 * 缺点，每次使用都会同步，浪费资源
* */
public class LazySinglemode {
    private static LazySinglemode instance;
    private static synchronized LazySinglemode  getInstance(){
        if(instance==null){
            instance=new LazySinglemode();
        }
        return instance;
    }
}
