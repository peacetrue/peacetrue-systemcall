package com.github.peacetrue.systemcall;

import java.lang.management.ManagementFactory;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * 系统调用入口。
 *
 * @author peace
 **/
public class Console {

    /** 默认的控制台 */
    public static final Console DEFAULT = new Console();

    public void start() {
        System.out.printf("Process ID is '%s', Main thread name is '%s'\n",
                ManagementFactory.getRuntimeMXBean().getName().split("@")[0],
                Thread.currentThread().getName()
        );

        List<ThreadInfo> threadInfos = getThreadInfos();
        if (threadInfos.isEmpty()) {
            System.err.println("no thread provided");
            return;
        }

        System.out.printf("You have setup %s threads:\n", threadInfos.size());
        for (int i = 0; i < threadInfos.size(); i++) {
            ThreadInfo threadInfo = threadInfos.get(i);
            System.out.printf("%s(%s): %s\n", i, threadInfo.getName(), threadInfo.getDescription());
        }
        System.out.println("Please choose thread index which you want to execute?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            execute(threadInfos, scanner.nextLine());
        }
    }

    /**
     * 获取线程信息集合，按线程名称排序。
     *
     * @return 线程信息集合
     */
    protected List<ThreadInfo> getThreadInfos() {
        ServiceLoader<ThreadInfo> threadInfos = ServiceLoader.load(ThreadInfo.class);
        Spliterator<ThreadInfo> spliterator = Spliterators.spliteratorUnknownSize(threadInfos.iterator(), Spliterator.ORDERED);
        return StreamSupport.stream(spliterator, false)
                .sorted(Comparator.comparing(ThreadInfo::getName))
                .collect(Collectors.toList());
    }

    /**
     * 执行选中的线程。
     *
     * @param threadInfos 线程信息集合
     * @param choice      选择的线程
     */
    protected void execute(List<ThreadInfo> threadInfos, String choice) {
        if (!choice.matches("\\d+(\\.\\d+)?")) {
            System.err.printf("Choose error index %s! Please choose again.\n", choice);
            return;
        }

        int index = Integer.parseInt(choice);
        if (index >= threadInfos.size()) {
            System.err.printf("Choose error index %s! Please choose again.\n", index);
            return;
        }

        ThreadInfo threadInfo = threadInfos.get(index);
        String threadInfoName = threadInfo.getName();
        new Thread(() -> {
            System.out.printf("Thread '%s' started!\n", threadInfoName);
            threadInfo.getRunnable().run();
            System.out.printf("Thread '%s' finished!\n", threadInfoName);
        }, threadInfoName).start();
    }
}
