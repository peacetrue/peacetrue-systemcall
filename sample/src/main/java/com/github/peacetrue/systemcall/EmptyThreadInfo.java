package com.github.peacetrue.systemcall;

/**
 * @author peace
 **/
public class EmptyThreadInfo extends ThreadInfoBean {

    public EmptyThreadInfo() {
        this
                .setName("empty")
                .setDescription("空线程")
                .setRunnable(() -> {
                });
    }

}
