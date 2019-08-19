package com.example.lp.lpdesignpatterns.factoryMode;

public class ProductA extends Product {
    private String productName;

    public ProductA(String productName) {
        this.productName = productName;
    }

    public ProductA() {
    }

    @Override
    public void doSomeTing() {

        System.out.println("this is ProductA");
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
