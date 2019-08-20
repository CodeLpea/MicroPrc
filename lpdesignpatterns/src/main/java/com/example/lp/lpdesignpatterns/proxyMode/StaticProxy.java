package com.example.lp.lpdesignpatterns.proxyMode;
/**
 * 静态代理实现
 * */
public class StaticProxy implements Shop{
    private Shop shop;

    public StaticProxy(Shop shop) {
        this.shop = shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void buy(String thing) {
        System.out.println("静态代理了"+shop.getClass().getName().substring(42,50)+"实现：");
        shop.buy(thing);
    }
}
