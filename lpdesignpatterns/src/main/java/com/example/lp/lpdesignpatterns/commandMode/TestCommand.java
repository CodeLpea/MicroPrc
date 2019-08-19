package com.example.lp.lpdesignpatterns.commandMode;

import com.example.lp.lpdesignpatterns.commandMode.Command.StartCommand;
import com.example.lp.lpdesignpatterns.commandMode.Command.StopCommand;

public class TestCommand {
    public static void main(String[] args) {
        //创建一个接受者对象
        Receiver receiver=new Receiver();
        //创建一个命令执行者
        StartCommand startCommand=new StartCommand(receiver);
        StopCommand stopCommand=new StopCommand(receiver);

        //执行一次start
        Invoker invoker=new Invoker(startCommand);
        invoker.action(startCommand);

        //执行一次stop，每次重新new是为了记录Command，可以对其优化
        invoker=new Invoker(stopCommand);
        invoker.action(stopCommand);


        //撤销一次命令stop
        invoker.removeAction(stopCommand);
        //撤销一次命令stop
        invoker.removeAction(stopCommand);

        //回复一次撤销
        invoker.redoAction();

        //回复一次撤销
        invoker.redoAction();

        //重新全部执行
        invoker.allAction();
    }
}
