package com.youxi.oom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiaoqing
 * @Description: OOMTest
 * @Date 2026/3/23 11:45
 **/

/**
 * 启动参数：
 * java \
    -Xms32m
    -Xmx32m
    -XX:+UnlockDiagnosticVMOptions
    -XX:NativeMemoryTracking=summary
    -XX:+HeapDumpOnOutOfMemoryError
    -XX:HeapDumpPath=./heapdump.hprof
    -XX:OnOutOfMemoryError='jcmd %p VM.native_memory summary scale=MB > ./nmt_%p.log'

 // 这个是windows的参数，上面是linux得参数
 -XX:OnOutOfMemoryError="cmd /c jcmd %p VM.native_memory summary scale=MB > .\nmt_%p.log"
 *   OomDemo
 *
 *  -XX:OnOutOfMemoryError 这个经过测试，发现里面不能嵌套 jcmd，大概率应该也不能嵌套其他jvm命令吧;
 * -XX:+PrintNMTStatistics 可以用这个，在运行结束的时候自动打印一份？
 * 或者用 dumpNmtToLog() 方法直接使用打印一份;
 * 我之前遇到过 jvm内存设置6g，oom快照4g， oom快照打开以后只有2.5g 能分析下吗
 */
public class OOMNMTTest {


    public static void main(String[] args) throws Exception {
        List<byte[]> holder = new ArrayList<>();
        int count = 0;

        String name = ManagementFactory.getRuntimeMXBean().getName();
        // 格式通常是 "pid@hostname"
        String pid = name.split("@")[0];
        System.out.println("PID = " + pid);
        System.out.println("Start allocating memory...");

       //Thread.sleep(100000);
        try {
        while (true) {
            // 每次申请 1MB
            holder.add(new byte[1024 * 1024]);
            count++;

            if (count % 10 == 0) {
                System.out.println("Allocated ~" + count + " MB");
                Thread.sleep(300);
            }
        }

        } catch (Throwable e) {
            System.out.println("内存溢出！！！！！");
            e.printStackTrace();
            dumpNmtToLog(pid);
        }

    }

    private static void dumpNmtToLog(String pid) {
        ProcessBuilder pb = new ProcessBuilder(
                "jcmd",
                pid,
                "VM.native_memory",
                "summary",
                "scale=MB"
        );
        pb.redirectErrorStream(true);

        try {
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    //logger.error("[NMT] {}", line);
                    System.out.println(line);
                }
            }
            int exit = process.waitFor();
            //logger.error("jcmd finished, exitCode={}", exit);
        } catch (Exception ex) {
            //logger.error("Failed to execute jcmd for NMT dump", ex);
        }
    }

}
