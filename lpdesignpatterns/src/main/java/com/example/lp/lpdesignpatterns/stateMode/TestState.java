package com.example.lp.lpdesignpatterns.stateMode;

public class TestState {
    public static void main(String[] args) {
        TvController tvController=new TvController();
        tvController.powerOn();
        tvController.nextChanel();
        tvController.preChanel();
        tvController.turnUp();
        tvController.turnDown();


        tvController.powerOff();
        tvController.nextChanel();
        tvController.preChanel();
        tvController.turnUp();
        tvController.turnDown();
    }
}
