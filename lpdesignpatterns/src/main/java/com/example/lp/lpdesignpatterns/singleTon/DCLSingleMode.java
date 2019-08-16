package com.example.lp.lpdesignpatterns.singleTon;

/**
 * DCL单例模式
 * 优点，只在需要时初始化
 * 而且不用每次都同步
 */
public class DCLSingleMode {
    private static DCLSingleMode instance;
    private DCLSingleMode() {
    }
    public  static DCLSingleMode getInstance() {
        if (instance == null) {
            synchronized (DCLSingleMode.class) {
                if (instance == null) {
                    instance = new DCLSingleMode();
                }
            }
        }
        return instance;
    }
}
