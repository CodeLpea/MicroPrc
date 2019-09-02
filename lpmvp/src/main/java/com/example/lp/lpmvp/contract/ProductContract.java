package com.example.lp.lpmvp.contract;

import com.example.lp.lpmvp.model.ProductBean;

public interface ProductContract {
    interface Presenter{
        void setProduct(String productName);
        void execute();
    }
    interface View extends BaseView<Presenter>{
        void setResult(ProductBean productbean);
        void showError(String msg);
    }
}

