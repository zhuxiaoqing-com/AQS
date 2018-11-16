package com.example.common.stringMatch;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadWords {
    public static List<String> load() {
        FileReader fileReader = null;
        BufferedReader br = null;
        ArrayList<String> words = new ArrayList<>();
        try {
            URL resource = LoadWords.class.getClassLoader().getResource("words.test");
            //System.out.println(resource);
            br = new BufferedReader(new FileReader(resource.getFile()));
            String str;
            while ((str = br.readLine()) != null) {
                words.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    @Test
    public void test01() {
        System.out.println(load());
    }

}
