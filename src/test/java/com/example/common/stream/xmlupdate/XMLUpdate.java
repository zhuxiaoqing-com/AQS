package com.example.common.stream.xmlupdate;

import java.io.*;
import java.util.ArrayList;

public class XMLUpdate {

    String ignoreSuffix = "_common.xml";
    ArrayList<String> ignoreList = new ArrayList<String>() {
        {
            add("模块对应ID.txt");
            add("user.xml");
            add("role.xml");
        }
    };

    public void start(String path) {
        File file = new File(path);
        File[] files = file.listFiles(x -> !x.getName().endsWith(ignoreSuffix) && !ignoreList.contains(x.getName()));
        for (File f : files) {
            doWriteName(f);
        }
    }

    /**
     * 将文件名称写入
     *
     * @param files
     */
    private void writeName(File[] files) {

    }

    private void doWriteName(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
           while ((line = br.readLine()) != null) {
               System.out.println(line);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
