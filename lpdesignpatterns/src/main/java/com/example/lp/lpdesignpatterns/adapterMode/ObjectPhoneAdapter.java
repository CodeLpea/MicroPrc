package com.example.lp.lpdesignpatterns.adapterMode;

import com.example.lp.lpdesignpatterns.adapterMode.Adapter;
import com.example.lp.lpdesignpatterns.adapterMode.Electric;

/**
 * 手机充电
 * 对象适配器实现
 * */
public class ObjectPhoneAdapter extends Electric implements Adapter {
    private Electric electric;
    public ObjectPhoneAdapter(Electric electric) {//通过构造方法传入对象
        this.electric = electric;
    }
    @Override
    public int convert_5v() {
        System.out.println("对象 适配器开始工作：");
        electric.output_220v();
        System.out.println("输出电压：" + 5);
        return 5;
    }
}
