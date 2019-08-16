package com.example.lp.lpdesignpatterns.builder.practicalBuilder;

public class Test {
    public static void main(String[] args) {
        String rulst=new MatebookBuilder().setCore("8").setScreen("LED").create().toString();
        System.out.println(rulst);
    }
}
