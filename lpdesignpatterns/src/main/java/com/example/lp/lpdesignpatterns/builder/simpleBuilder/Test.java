package com.example.lp.lpdesignpatterns.builder.simpleBuilder;

import android.app.AlertDialog;
import android.content.Context;

public class Test {
    public static void main(String[] args) {
        String rulst1= Builder.create(new MateBook()).setCore("8").setScreen("LED").build().toString();
        String rulst2 =new MateBook().setCore("16").setScreen("LCD").build().toString();
        System.out.println(rulst1);
        System.out.println(rulst2);
    }
}
