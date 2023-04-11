public class SystemCallApplication {
    public static void main(String[] args) throws Exception {
        // 新开一个线程，将测试内容与主线程分开
        Thread thread = new Thread(() -> {
            while (true) {
                java.util.concurrent.locks.LockSupport.park();
            }
        });
        thread.start();
        thread.join();
    }
}
