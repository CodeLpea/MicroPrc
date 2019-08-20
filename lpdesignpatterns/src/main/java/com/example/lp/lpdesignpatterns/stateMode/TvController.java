package com.example.lp.lpdesignpatterns.stateMode;
/**
 * 控制电源开关的实现
 * */
public class TvController  implements  PowerController,TvState{
    private TvState tvState;

    public TvController() {
        //默认为开机状态
        setTvState(new PowerOnState());
    }

    public void setTvState(TvState tvState) {
        this.tvState = tvState;
    }

    @Override
    public void powerOn() {
        setTvState(new PowerOnState());
        System.out.println("开机了");

    }

    @Override
    public void powerOff() {
        setTvState(new PowerOffState());
        System.out.println("关机了");

    }

    @Override
    public void turnUp() {
        tvState.turnUp();
    }

    @Override
    public void turnDown() {
        tvState.turnDown();

    }

    @Override
    public void nextChanel() {
        tvState.nextChanel();

    }

    @Override
    public void preChanel() {
        tvState.preChanel();

    }
}
