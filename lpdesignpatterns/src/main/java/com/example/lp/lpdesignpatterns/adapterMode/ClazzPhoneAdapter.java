package com.example.lp.lpdesignpatterns.adapterMode;

import com.example.lp.lpdesignpatterns.adapterMode.Adapter;

/**
 * 手机充电
 * 类适配器实现
 * */
public class ClazzPhoneAdapter extends Electric implements Adapter {
    @Override
    public int convert_5v() {
        System.out.println("类 适配器开始工作：");
        super.output_220v();
        System.out.println("输出电压：" + 5);
        return 5;
    }
}
