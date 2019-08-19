package com.example.lp.lpdesignpatterns.commandMode.Command;

import com.example.lp.lpdesignpatterns.commandMode.Receiver;

/**
 * 具体的start命令执行类
 * */
public class StartCommand implements Command {
    private Receiver receiver;
    public StartCommand(Receiver receiver) {
        this.receiver=receiver;
    }

    @Override
    public void excute() {
        receiver.start();
    }
}
