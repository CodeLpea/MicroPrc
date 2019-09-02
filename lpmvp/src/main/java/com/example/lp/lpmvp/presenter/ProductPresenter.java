package com.example.lp.lpmvp.presenter;

import com.example.lp.lpmvp.contract.ProductContract;
import com.example.lp.lpmvp.model.ProductBean;


public class ProductPresenter implements ProductContract.Presenter {

    private String prodcutName;
    private ProductContract.View view;
    private static String testUrl = "http://www.kuaidi100.com/query?type=yuantong&postid=11111111111";

    public ProductPresenter(ProductContract.View view) {
        this.view = view;
    }

    @Override
    public void setProduct(String productName) {
        this.prodcutName = productName;
    }

    @Override
    public void execute() {
        view.setResult(new ProductBean());
    }
}
