package com.coin.bnb;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import com.coin.bnb.random.BNBWalletGenerator;
import com.example.demo4.TimeUtil;
import org.bitcoinj.crypto.MnemonicCode;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Scan {
    static String path = "\\src\\test\\java\\com\\coin\\bnb\\address";
    static String savePath = "\\src\\test\\java\\com\\coin\\bnb\\";
    static {
        path = new File("").getAbsolutePath()+path;
        savePath = new File("").getAbsolutePath()+savePath;
    }

    static Set<String> strings;
    static File saveFile;

    static AtomicInteger count = new AtomicInteger(0);

    // 加载csv.
    public static void main(String[] args) throws Exception {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = (Logger) LoggerFactory.getLogger(MnemonicCode.class);
        logger.setLevel(ch.qos.logback.classic.Level.OFF);
        /*String s = "0x2c7447bf72ffd23e7f2a5aa04bdff0faf5798658";
        String substring = s.substring(1, 41);
        System.out.println(s);*/
        strings = loadCsv();

        saveFile = new File(savePath+"1.txt");
        if(!saveFile.exists()) {
            boolean newFile = saveFile.createNewFile();
        }
        System.out.println("savePath " + saveFile.getAbsolutePath());

    /*    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        int index = 6;
        for (int i = 0; i < index; i++) {
            Thread thread = new Thread(() -> check(singleThreadExecutor));
            thread.start();
        }

        int second = 10;
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            System.out.println("统计 ！！！！second " + second + " count " + count.get());
            count.set(0);
        }, second,second, TimeUnit.SECONDS);
*/
        while (true) {
            Map<String, String> bscAddress = BNBWalletGenerator.createBscAddress();
            String s = bscAddress.get("address");
            if(!strings.contains(s)){
                continue;
            }
            List<String> list = Collections.singletonList(bscAddress.toString());
            System.out.println("出现了~！！！" + list);
            FileUtil.appendLines(list, saveFile, CharsetUtil.CHARSET_UTF_8);
        }
    }


    private static void check(ExecutorService singleThreadExecutor) {
        int index = 0;
        int a = 2<<5;
        System.out.println(a);
        while (true) {
            if (index < 0) {
                index = 0;
            }
            index++;
            if((index & a) == 0) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Map<String, String> bscAddress = BNBWalletGenerator.createBscAddress();
            String s = bscAddress.get("address");

            count.incrementAndGet();

            if(!strings.contains(s)){
                continue;
            }
            List<String> list = Collections.singletonList(bscAddress.toString());
            System.out.println("出现了~！！！" + list);
            singleThreadExecutor.submit(()->FileUtil.appendLines(list, saveFile, CharsetUtil.CHARSET_UTF_8));
        }
    }

    private static Set<String> loadCsv() {
        //int length = "0x2c7447bf72ffd23e7f2a5aa04bdff0faf5798658".length();
        long startTime = TimeUtil.getTime();
        int length = 42;
        File[] files = new File(path).listFiles();

        HashSet<String> stringsSet = new HashSet<>(files.length*100_000);
        for (File file : files) {
            List<String> strings = FileUtil.readLines(file, CharsetUtil.CHARSET_UTF_8);
            for (int i = 1; i < strings.size(); i++) {
                String substring = strings.get(i).substring(1, 41);
                stringsSet.add(substring);
            }
        }

        long costTime = TimeUtil.getTime() - startTime;
        System.out.println("loadCsv Cost " + costTime);
        return stringsSet;
    }
}
