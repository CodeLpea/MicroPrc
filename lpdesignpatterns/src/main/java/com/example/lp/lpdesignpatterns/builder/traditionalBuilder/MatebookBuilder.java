package com.example.lp.lpdesignpatterns.builder.traditionalBuilder;

public class MatebookBuilder extends Builder {
    private Computer computer=new MateBook();

    @Override
    public void setCore(String core) {
        computer.setCore(core);
    }

    @Override
    public void setScreen(String screen) {
        computer.setScreen(screen);

    }

    @Override
    public void setName() {
        computer.setName();
    }

    @Override
    public Computer create() {
        return computer;
    }
}
