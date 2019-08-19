package com.example.lp.lpdesignpatterns.factoryMode;

public class FactoryIml  extends Factory{

    @Override
    public <T extends Product> T creatProduct(Class<T> clazz) {
        Product p=null;
        try{
            p=(Product)Class.forName(clazz.getName()).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T)p;
    }

}
