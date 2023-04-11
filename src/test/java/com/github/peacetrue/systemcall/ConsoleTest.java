package com.github.peacetrue.systemcall;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author peace
 **/
public class ConsoleTest {

    private Console console = new Console();

    public static void main(String[] args) {
        SystemCallApplication.main(args);
    }

    @Test
    void start() {
        // 如何模拟 System.in 测试
        // https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        console.start();

        // 覆盖率测试没有线程信息时的异常分支
        new Console() {
            @Override
            protected List<ThreadInfo> getThreadInfos() {
                return Collections.emptyList();
            }
        }.start();
    }

    @Test
    void getThreadInfos() {
        Assertions.assertEquals(2, console.getThreadInfos().size());
    }

    @Test
    void execute() {
        List<ThreadInfo> threadInfos = console.getThreadInfos();
        // 使用不同的执行条件，完善覆盖率测试
        console.execute(threadInfos, "a");
        console.execute(threadInfos, "-1");
        console.execute(threadInfos, "0");
        console.execute(threadInfos, "2");
    }

}
