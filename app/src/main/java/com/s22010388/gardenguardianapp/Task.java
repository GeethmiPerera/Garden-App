package com.s22010388.gardenguardianapp;

public class Task {
    private String task;
    private boolean isCompleted;
    private long reminderTime;

    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
        this.reminderTime = 0;
    }

    public String getTask() {
        return task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

}