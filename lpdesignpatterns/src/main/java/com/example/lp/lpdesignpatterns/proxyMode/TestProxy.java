package com.example.lp.lpdesignpatterns.proxyMode;

import java.lang.reflect.Proxy;

public class TestProxy {
    public static void main(String[] args) {
        //创建实现
        Shop phyicalShop=new PhysicalStore("万达");
        Shop onlineShop=new OnlineStore("天猫");

        //创建静态代理
        StaticProxy staticProxy=new StaticProxy(phyicalShop);
        //启动具体代理功能
        staticProxy.buy("爆米花");

        //切换代理的实现
        staticProxy.setShop(onlineShop);
        //启动具体代理功能
        staticProxy.buy("电影票");

        //创建动态代理
        DynamicProxy dynamicProxy=new DynamicProxy(phyicalShop);
        //获取ClassLoader
        ClassLoader classLoader = phyicalShop.getClass().getClassLoader();
        //通过 dynamicProxy 创建phyicalShop实例 ，实际上通过反射来实现的
        Shop shop = (Shop) Proxy.newProxyInstance(classLoader, new Class[]{Shop.class}, dynamicProxy);
        shop.buy("AJ5冰蓝");

        dynamicProxy.setObject(onlineShop);
        //获取ClassLoader
        ClassLoader onlineLoader = phyicalShop.getClass().getClassLoader();
        //通过 dynamicProxy 创建phyicalShop实例 ，实际上通过反射来实现的
        Shop onlineshop = (Shop) Proxy.newProxyInstance(onlineLoader, new Class[]{Shop.class}, dynamicProxy);
        onlineshop.buy("莆田AJ");



    }
}
