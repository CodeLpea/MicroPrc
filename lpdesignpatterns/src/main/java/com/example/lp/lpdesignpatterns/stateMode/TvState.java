package com.example.lp.lpdesignpatterns.stateMode;
/**
 * 模拟电视遥控器
 * 状态功能接口
* */
public interface TvState {
    void turnUp();
    void turnDown();
    void nextChanel();
    void preChanel();
}
