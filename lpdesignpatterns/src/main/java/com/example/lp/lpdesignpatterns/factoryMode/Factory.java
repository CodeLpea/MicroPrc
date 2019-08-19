package com.example.lp.lpdesignpatterns.factoryMode;

public abstract class Factory {
    public abstract <T extends Product> T creatProduct(Class<T> Class) ;
}
