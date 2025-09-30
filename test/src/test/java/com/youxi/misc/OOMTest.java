package com.youxi.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiaoqing
 * @Description: OOMTest
 * @Date 2025/9/16 20:11
 **/
public class OOMTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting improved multi-thread OOM demo...");

        // 线程 1：高负载，快速分配内存，最终触发 OOM 并退出
        Thread oomThread = new Thread(() -> {
            List<byte[]> list = new ArrayList<>();
            try {
                while (true) {
                    list.add(new byte[10 * 1024 * 1024]); // 每次分配 10MB
                    System.out.println("OOM Thread allocated " + list.size() * 10 + "MB");
                    Thread.sleep(50);
                }
            } catch (OutOfMemoryError e) {
                System.err.println("OOM Thread triggered OOM!");
                throw e; // 不捕获异常，线程退出
            } catch (InterruptedException ignored) {}
        });
        oomThread.start();

        // 线程 2：低负载线程，先分配少量对象，然后 sleep，保持 live
        Thread liveThread = new Thread(() -> {
            List<byte[]> list = new ArrayList<>();
            try {
                // 先分配少量对象
                for (int i = 0; i < 5; i++) {
                    list.add(new byte[1 * 1024 * 1024]); // 每次 1MB
                }
                System.out.println("Live Thread allocated initial objects, now sleeping...");
                Thread.sleep(10_000); // 10 秒睡眠，保持线程存活
            } catch (InterruptedException ignored) {}
        });
        liveThread.start();

        // 等待 OOM 线程结束
        oomThread.join();

        // 生成 Heap Dump 时，liveThread 仍然存活，但持有的对象少
        System.out.println("OOM Thread exited. You can now analyze heap dump.");
    }
}
