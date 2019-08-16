package com.example.lp.lpdesignpatterns.builder.traditionalBuilder;

public class Test {
    public static void main(String[] args) {
        Builder builder=new MatebookBuilder();
        Director director=new Director(builder);
        director.construct("8","Amoled屏幕");
        System.out.println(builder.create().toString());
    }
}
