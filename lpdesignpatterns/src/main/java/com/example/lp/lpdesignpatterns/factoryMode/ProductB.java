package com.example.lp.lpdesignpatterns.factoryMode;

public class ProductB extends Product {
    private String productName;

    public ProductB(String productName) {
        this.productName = productName;
    }

    public ProductB() {
    }

    @Override
    public void doSomeTing() {
        System.out.println("this is ProductB");
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ProductA{" +
                "productName='" + productName + '\'' +
                '}';
    }
}
