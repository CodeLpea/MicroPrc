package com.example.lp.lpglide.Request;

import android.graphics.Bitmap;

public interface RequestListener {

    boolean onSuccess(Bitmap bitmap);

    boolean onFailure();

}
