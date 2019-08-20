package com.example.lp.lpdesignpatterns.adapterMode;

public class TestAdapter {
    public static void main(String[] args) {
        //新建一个需要适配的对象
        Electric electric = new Electric();
        //对象适配方案
        ObjectPhoneAdapter mObjectPhoneAdapter = new ObjectPhoneAdapter(electric);
        mObjectPhoneAdapter.convert_5v();

        //类适配方案
        ClazzPhoneAdapter mClazzPhoneAdapter = new ClazzPhoneAdapter();
        mClazzPhoneAdapter.convert_5v();

    }
}
