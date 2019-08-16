package com.example.lp.lpdesignpatterns.singleTon;
/**
 * 静态内部类单例模式
 *优点,只有在调用getinstance的时候才会初始化
 * 确保了线程安全，保证了单例唯一性
 * 同时延迟了实例化
 * 推荐使用这种方式
 * */
public class InnerSingleMode {
    private InnerSingleMode(){
    }
    public static  InnerSingleMode getInstance(){
        return InnerHolder.instance;
    }
    private static  class InnerHolder{
        private static final InnerSingleMode instance=new InnerSingleMode();
    }

}
