package com.mp.todo.domain.entity;

public class Task implements com.mp.todo.domain.Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }



    @Override
    public String getName() {
        return this.name;
    }
}
