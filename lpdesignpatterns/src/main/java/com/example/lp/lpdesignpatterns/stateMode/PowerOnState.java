package com.example.lp.lpdesignpatterns.stateMode;
/**
 * 开机状态的功能
 * */
public class PowerOnState implements TvState {
    @Override
    public void turnUp() {
        System.out.println("音量+");

    }

    @Override
    public void turnDown() {
        System.out.println("音量-");

    }

    @Override
    public void nextChanel() {
        System.out.println("next换台");
    }

    @Override
    public void preChanel() {
        System.out.println("pre换台");

    }
}
