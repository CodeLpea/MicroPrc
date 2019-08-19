package com.example.lp.lpdesignpatterns.commandMode;
/**
 * 命令接受者对象
 * 实际命令执行
 * */
public class Receiver {
    public void start(){
        System.out.println("执行start命令");
    }
    public void stop(){
        System.out.println("执行stop命令");
    }
}
