package com.example.lp.lpmvp.ui;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lp.lpmvp.R;
import com.example.lp.lpmvp.contract.ProductContract;
import com.example.lp.lpmvp.model.ProductBean;
import com.example.lp.lpmvp.presenter.ProductPresenter;

public class MvpMainActivity extends AppCompatActivity implements ProductContract.View {
    private TextView textView;
    private Button button;
    private ProductPresenter productPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_activity_main);
        doPresenter();
        initView();


    }

    private void doPresenter() {
        productPresenter=new ProductPresenter(this);
    }

    private void initView() {
        textView= findViewById(R.id.tv_result);
        button= findViewById(R.id.btn_do);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.setProduct("test");
                productPresenter.execute();
            }
        });
    }

    @Override
    public void setResult(ProductBean productbean) {

        textView.setText(productbean.toString());
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void setPresenter(ProductContract.Presenter presenter) {

    }
}
