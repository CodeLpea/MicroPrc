package com.example.lp.lpdesignpatterns.stateMode;
/**
 * 关机状态的功能
 * */
public class PowerOffState implements TvState {
    @Override
    public void turnUp() {
        remind();

    }

    @Override
    public void turnDown() {
        remind();
    }

    @Override
    public void nextChanel() {
        remind();
    }

    @Override
    public void preChanel() {
        remind();
    }

    private void remind(){
        System.out.println("目前是关机状态，请先开机");
    }
}
