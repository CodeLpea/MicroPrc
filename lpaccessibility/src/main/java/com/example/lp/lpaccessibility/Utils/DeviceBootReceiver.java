package com.example.lp.lpaccessibility.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import com.example.lp.lpaccessibility.LpAccessBilityActivity;
import com.example.lp.lpaccessibility.service.LpAccessibilityService;

/**
 * device boot receiver
 *
 * @author majh
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_BOOT)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(PermissionUtil.isCanDrawOverlays(context) && PermissionUtil.isAccessibilityServiceEnable(context)) {
                   launchAccessibility(context);
                }else {
                    MainPageGo(context);
                }
            }else {
                if(PermissionUtil.isAccessibilityServiceEnable(context)) {
                    launchAccessibility(context);
                }else {
                    MainPageGo(context);
                }
            }
        }
    }

    private void MainPageGo(Context context) {//没有权限
        Intent launch = new Intent(context,LpAccessBilityActivity.class);
        launch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(launch);
    }
    public static void launchAccessibility(Context context) {//有权限直接进入
        Intent intent = new Intent(context, LpAccessibilityService.class);
        context.startService(intent);
    }
}
