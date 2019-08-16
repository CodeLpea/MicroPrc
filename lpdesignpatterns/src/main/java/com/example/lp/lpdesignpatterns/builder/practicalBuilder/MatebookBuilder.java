package com.example.lp.lpdesignpatterns.builder.practicalBuilder;

public class MatebookBuilder extends Builder {
    private Computer computer=new MateBook();

    @Override
    public Builder setCore(String core) {
        computer.setCore(core);
        return this;
    }

    @Override
    public Builder setScreen(String screen) {
        computer.setScreen(screen);
        return this;

    }

    @Override
    public Builder setName(String name) {
        computer.setName(name);
        return this;
    }

    @Override
    public Computer create() {
        return computer;
    }
}
