package com.example.lp.lpdesignpatterns.factoryMode;

public class TestFactory {
    public static void main(String[] args) {
        Factory factory=new FactoryIml();
        ProductA productA=factory.creatProduct(ProductA.class);
        productA.doSomeTing();
        ProductB productB=factory.creatProduct(ProductB.class);
        productB.doSomeTing();
    }
}
