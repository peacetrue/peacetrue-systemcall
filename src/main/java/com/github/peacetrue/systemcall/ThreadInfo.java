package com.github.peacetrue.systemcall;

/**
 * 线程信息。
 *
 * @author peace
 **/
public interface ThreadInfo {

    /**
     * 获取线程名称。
     *
     * @return 线程名称。
     */
    String getName();

    /**
     * 获取线程描述。
     *
     * @return 线程描述
     */
    String getDescription();

    /**
     * 获取线程执行内容。
     *
     * @return 线程执行内容
     */
    Runnable getRunnable();

}
