package com.coin.bnb;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import com.coin.bnb.random.BNBWalletGenerator;
import com.example.demo4.TimeCostPrint;
import com.example.demo4.TimeUtil;
import org.apache.logging.log4j.LogManager;
import org.bitcoinj.crypto.MnemonicCode;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;

public class Scan {
    static String path = "\\src\\test\\java\\com\\coin\\bnb\\address";
    static String savePath = "\\src\\test\\java\\com\\coin\\bnb\\";
    static {
        path = new File("").getAbsolutePath()+path;
        savePath = new File("").getAbsolutePath()+savePath;
    }

    static Set<String> strings;
    // 加载csv.
    public static void main(String[] args) throws Exception {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = (Logger) LoggerFactory.getLogger(MnemonicCode.class);
        logger.setLevel(ch.qos.logback.classic.Level.OFF);
        /*String s = "0x2c7447bf72ffd23e7f2a5aa04bdff0faf5798658";
        String substring = s.substring(1, 41);
        System.out.println(s);*/
        strings = loadCsv();

        File saveFile = new File(savePath+"1.txt");
        if(!saveFile.exists()) {
            saveFile.createNewFile();
        }
        System.out.println("savePath " + saveFile.getAbsolutePath());

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
