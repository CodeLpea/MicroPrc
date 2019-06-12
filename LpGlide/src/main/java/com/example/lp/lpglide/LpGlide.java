package com.example.lp.lpglide;

import android.content.Context;

import com.example.lp.lpglide.Manager.RequestManager;
import com.example.lp.lpglide.Request.BitmapRequest;

public class LpGlide {
    public static BitmapRequest with(Context context){
        return new BitmapRequest(context);
    }

    public static void Stop(){ RequestManager.getRequestManager().stop(); }

}
