package com.example.lp.lpaccessibility;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lp.lpaccessibility.Utils.PermissionUtil;

public class LpAccessBilityActivity extends AppCompatActivity {
    private static final int FLAT_REQUEST_CODE = 213;
    private static final int ACCESSIBILITY_REQUEST_CODE = 438;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_bility);
        flatWindowVisible();//判断悬浮窗权限
    }

    /**
     * flut button visible
     */
    private void flatWindowVisible() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // > M,grant permission
            if (PermissionUtil.isCanDrawOverlays(this)) {
                // permission authorized,service go,button gone
                accessibilityVisible();
            } else {
                // permission unauthorized,button visible
                //没有悬浮窗权限，直接去申请
                goGetFlatWindow();
            }
        } else {
            // < M,service go,gone
            //小于安卓m，直接去申请辅助功能
            accessibilityVisible();
        }
    }

    /**
     * Accessibility button visible
     */
    private void accessibilityVisible() {
        if (PermissionUtil.isAccessibilityServiceEnable(this)) {
            finish();
        } else {
            goGetAccessibility();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void goGetFlatWindow() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        startActivityForResult(intent, FLAT_REQUEST_CODE);
    }

    public void goGetAccessibility() {
        Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivityForResult(accessibleIntent, ACCESSIBILITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FLAT_REQUEST_CODE) {
            flatWindowVisible();
        } else if (requestCode == ACCESSIBILITY_REQUEST_CODE) {
            accessibilityVisible();
        }
    }

}
