package com.example.lp.lpdesignpatterns.commandMode.Command;

import com.example.lp.lpdesignpatterns.commandMode.Receiver;

/**
 * 具体的stop命令执行类
 * */
public class StopCommand implements Command {
    private Receiver receiver;
    public StopCommand(Receiver receiver) {
        this.receiver=receiver;
    }

    @Override
    public void excute() {
        receiver.stop();
    }
}
