package com.example.lp.lpuicore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lp.lpuicore.paint.PaintActivity;

import java.io.InputStream;

public class UiCoreActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_core);
        intView();
    }

    private void intView() {
        bt_paint=findViewById(R.id.bt_paint);
        bt_paint.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_paint) {
            Intent paintUi = new Intent(this, PaintActivity.class);
            startActivity(paintUi);

        }

    }
}
