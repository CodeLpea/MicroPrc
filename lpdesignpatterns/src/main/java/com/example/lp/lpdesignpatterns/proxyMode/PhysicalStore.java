package com.example.lp.lpdesignpatterns.proxyMode;
/**
 * 实体店
 * 购入的实现
 * */
public class PhysicalStore implements Shop{
    private String stroeName;

    public PhysicalStore(String stroeName) {
        this.stroeName = stroeName;
    }

    public String getStroeName() {
        return stroeName;
    }

    public void setStroeName(String stroeName) {
        this.stroeName = stroeName;
    }

    @Override
    public void buy(String thing) {
        System.out.println("在-----"+stroeName+"-----中购买了"+thing);
    }
}
