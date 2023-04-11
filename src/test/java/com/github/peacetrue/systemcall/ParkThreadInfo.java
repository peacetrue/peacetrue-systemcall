package com.github.peacetrue.systemcall;

import java.util.concurrent.locks.LockSupport;

/**
 * @author peace
 **/
public class ParkThreadInfo extends ThreadInfoBean {

    public ParkThreadInfo() {
        this
                .setName("park")
                .setDescription("暂停线程")
                .setRunnable(() -> {
                    while (true) {
                        LockSupport.park(this);
                    }
                });
    }

}
