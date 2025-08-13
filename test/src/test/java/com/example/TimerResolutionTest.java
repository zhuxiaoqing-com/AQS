package com.example;

import com.sun.jna.Library;
import com.sun.jna.Native;

import java.util.concurrent.locks.LockSupport;

public class TimerResolutionTest {
   /* public static void main(String[] args) {
        long last = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            LockSupport.parkNanos(1_000_000); // 1ms
            long now = System.nanoTime();
            System.out.println((now - last) / 1_000_000.0 + " ms");
            last = now;
        }
    }*/

    public static void main(String[] args) throws InterruptedException {
        WinMM.INSTANCE.timeBeginPeriod(1); // 提升到 1ms
        System.out.println("Timer resolution set to 1ms");

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Thread.sleep(1);
            System.out.println((System.nanoTime() - start) / 1_000_000.0 + " ms");
        }

        WinMM.INSTANCE.timeEndPeriod(1); // 还原
        System.out.println("Timer resolution reset");
    }

    public interface WinMM extends Library {
        WinMM INSTANCE = Native.loadLibrary("winmm", WinMM.class);
        int timeBeginPeriod(int uPeriod);
        int timeEndPeriod(int uPeriod);
    }
}
