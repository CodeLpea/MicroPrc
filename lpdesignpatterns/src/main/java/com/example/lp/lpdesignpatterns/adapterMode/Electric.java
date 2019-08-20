package com.example.lp.lpdesignpatterns.adapterMode;
/**
 *创建被适配对象
 * 它还可以实现其他的抽象
 * */
public class Electric {
    public Electric() {
    }

    public int output_220v() {//输出220V
        System.out.println("我的电压是 220");
        return 220;
    }
}
