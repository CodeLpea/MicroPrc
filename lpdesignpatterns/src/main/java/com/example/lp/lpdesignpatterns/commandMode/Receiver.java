package com.example.lp.lpdesignpatterns.commandMode;
/**
 * 命令接受者对象
 * 实际命令执行
 * */
public class Receiver {
    public void start(){
        System.out.println("准备执行----------start-----------------命令");
        System.out.println("开始执行----------start-----------------命令");
        System.out.println("执行完成----------start-----------------命令");
    }
    public void stop(){
        System.out.println("准备执行----------stop------------------命令");
        System.out.println("开始执行----------stop------------------命令");
        System.out.println("执行完成----------stop------------------命令");
    }
}
