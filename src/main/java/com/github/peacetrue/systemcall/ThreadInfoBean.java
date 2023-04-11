package com.github.peacetrue.systemcall;

/**
 * 线程信息 Bean。
 *
 * @author peace
 **/
public class ThreadInfoBean implements ThreadInfo {
    private String name;
    private String description;
    private Runnable runnable;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Runnable getRunnable() {
        return runnable;
    }

    protected ThreadInfoBean setName(String name) {
        this.name = name;
        return this;
    }

    protected ThreadInfoBean setDescription(String description) {
        this.description = description;
        return this;
    }

    protected ThreadInfoBean setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }
}
