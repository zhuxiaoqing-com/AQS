package com.example.common.stream.readxmlname;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadXmlName {

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
        // 将文件名称写入
        writeName(files);
    }

    /**
     * 将文件名称写入
     *
     * @param files
     */
    private void writeName(File[] files) {
        ArrayList<String> names = new ArrayList<>();
        for (File file : files) {
            names.add(file.getName().split("\\.")[0]);
        }
        doWriteName(names);
    }

    private void doWriteName(ArrayList<String> names) {
        File file = new File("d:/a.txt");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String name : names) {
                bw.write(name);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
