package com.example.demo4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.custom_annotation.IndexDesc;
import com.google.common.base.Splitter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Test;
import sun.misc.FloatingDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Test04 {

    @Test
    public void test01() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.currentTimeMillis();
        }

        long end = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.currentTimeMillis();
        }

        long end2 = System.currentTimeMillis();
        System.out.println((end2 - start2));
    }

    @Test
    public void test02() {
        String s = "\\|";
        String s1 = "|";
        String aa = "1|3|2|3|1";
        System.out.println(Arrays.toString(aa.split(s)));
        System.out.println(Arrays.toString(aa.split(s1)));
    }

    @Test
    public void test03() {
        String s = "0|203015|1&0|103|10&0|42001|10&0|42100|5&0|42123|5&0|101|150000";
        System.out.println(s.length());
    }

    @Test
    public void test04() {
        String s = "383490927757018";
        System.out.println(s.length());
    }

    @Test
    public void test05() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i <= 10000; i++) {
            list.add(i);
        }

        System.out.println(list.toString().length());
        System.out.println(list.toString());
    }


    @Test
    public void test06() {
        long a = 100_000_000_000_000L;
        long b = 123456789;
        StringBuilder builder = new StringBuilder(200);
        HashMap<Long, Long> hashMap = new LinkedHashMap<>();

        for (int i = 0; i <= 600; i++) {
            hashMap.put(a + i, b);
        }
        Set<Map.Entry<Long, Long>> entries = hashMap.entrySet();
        int rank = 1;
        for (Map.Entry<Long, Long> entry : entries) {

            builder.append(entry.getKey());
            builder.append("|");
            builder.append(rank);
            builder.append("|");
            builder.append(entry.getValue());
            builder.append("&");
        }
        System.out.println(builder.subSequence(0, builder.length() - 1));
    }

    @Test
    public void test07() {
        System.out.println(Integer.toBinaryString(-1 >> 2));
        System.out.println(1 >> 2);
        System.out.println(-1 << 2);
    }

    @Test
    public void test08() {
        int num = 10000;
        System.out.println(num * 10 * 8);
    }

    @Test
    public void test09() {
        IndexDesc indexDesc = new IndexDesc();
        indexDesc.setName("name");
        indexDesc.setColumnNames(new String[]{"a", "b"});
        indexDesc.setUnique(true);
        System.out.println(indexDesc.toIndexDDL());
    }

    @Test
    public void test10() {
        List<Object> list = new ArrayList<>();
        list.add(0, 1);
        list.add(0, 2);
        System.out.println(list);
    }

    @Test
    public void test11() {
        int s = 50;
        int radio = 10;
        int radio1 = s / radio;
        for (int i = s; i >= 0; i = i - radio1) {
            System.out.println(i);
        }

    }

    @Test
    public void test12() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(integers.size());
    }


    @Test
    public void test13() {
        float s = 1.0f;
        s = s / 0f;
        s = s - Integer.MAX_VALUE;
        if (s == 0f) {
            System.out.println("==");
        }

        if (s < 0f) {
            System.out.println("<");
        }

        if (s > 0f) {
            System.out.println(">");
        }
    }

    @Test
    public void test14() {
        System.out.println(0x3FF_FFFFF_FFFFFL);
        Long l = -1L ^ (-1L << 50);
        System.out.println(-1L ^ (-1L << 50));
        System.out.println(Long.toHexString(l));
        System.out.println(0x2FF_FFFFF_FFFFFL);
        System.out.println(0x3FF_FFFFF_FFFFFL);
        System.out.println(Long.toBinaryString(l).length());
        System.out.println(Long.toBinaryString(0x2FF_FFFFF_FFFFFL).length());
        System.out.println(Long.toBinaryString(0x3FF_FFFFF_FFFFFL).length());
        System.out.println(Long.toBinaryString(0x2FF_FFFFF_FFFFFL));
        System.out.println(Long.toBinaryString(0x3FF_FFFFF_FFFFFL));
        //long s = 11111_11111_11111_11111_11111_11111_11111_11111_11111_11111;
    }

    @Test
    public void test15() {
        double s = 1.001;
        double ss = s * 3;
        System.out.println(s * 3);
        System.out.println(ss);
        double v = FloatingDecimal.parseDouble(String.valueOf(s));
        System.out.println(ss);
        System.out.println(String.valueOf(ss));
        System.out.println(s);
        String.valueOf(s).split("\\.");
    }

    @Test
    public void test16() {
        LocalDateTime now = LocalDateTime.now();
        int nano = now.getNano();
        System.out.println(now);
        System.out.println(nano);
    }

    @Test
    public void test17() {
        System.out.println(1 / 70.0);
        System.out.println(1 / 120.0);
        System.out.println(1 / 360.0);
        System.out.println(1 / 320.0);
    }

    @Test
    public void test18() {
        float i = 0.9f;
        float b = 0.3f;
        System.out.println(i == (b * b));

    }

    @Test
    public void test19() {
        int s = "546110133".split("1").length ^ 1;
        System.out.println(s);
    }

    @Test
    public void test20() {
        long s = 0xffff_ffff_ffff_fff8L;
        System.out.println(s);
        System.out.println(s - 8);
    }

    @Test
    public void test21() {
        double s = 1.0 - 0.9;
        double ss = 1.0 - 0.1;
        System.out.println(s);
        System.out.println(ss);

        double a = 0.9;
        double aa = 0.1;
        System.out.println(0.0001);
        System.out.println(0.0009);
        System.out.println(a == 0.9);
        System.out.println(aa == 0.1);

    }

    @Test
    public void test22() {
        LocalTime now = LocalTime.now();
        LocalTime minute = LocalTime.of(10, 40);
        LocalTime second = LocalTime.of(10, 40, 1);
        System.out.println(now);
        System.out.println(minute.isBefore(minute));
        System.out.println(minute.isAfter(minute));
        System.out.println(60 * 60 * 1000);
    }

    @Test
    public void test23() {
        String timeStr = "12:11:11";
        LocalTime parse = LocalTime.parse(timeStr);
        System.out.println(parse);

        String dateStr = "2018-01-02";
        LocalDate parse1 = LocalDate.parse(dateStr);
        System.out.println(parse1);


        String dateTimeStr = "2018-01-02T12:11:11";
        LocalDateTime parse2 = LocalDateTime.parse(dateTimeStr);
        System.out.println(parse2);

        System.out.println(7080 - 1880 - 1880);
    }


    @Test
    public void test24() {
        LocalTime now = LocalTime.now();
        System.out.println(now.plusMinutes(111));
        System.out.println(now);
    }

    // 1880 -1080 800 12080 - 3700  4080 - 1880-1880
    @Test
    public void test25() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(60);
        list.add(120);
        list.add(300);
        list.add(600);
        list.add(1000);
        list.add(2000);
        list.add(3000); //2390 + 610 = 3000
        list.add(5000);
        list.add(7000);
        list.add(10000);

        for (int index = 0; index < list.size(); index++) {
            int a = index + 1;
            System.out.println(a + "....." + fun1(list, index));
        }
    }

    private int fun1(ArrayList<Integer> list, int index) {
        int sum = 0;
        for (int x = 0; x <= index; x++) {
            sum += list.get(x);
        }
        return sum;
    }

    @Test
    public void test26() {
        String ss = "{\"a\",\"b\\x";
        System.out.println(ss);
        Object o = JSON.parse(ss);
        System.out.println(o);
    }

    @Test
    public void test27() {
        Iterable<String> split = Splitter.on(" ").trimResults().split("null nd ds ds   dsdf");
        System.out.println(split);
    }

    @Test
    public void test28() {
        Map<Integer, Integer> set = new HashMap<>();
        set.put(1, 1);
        set.put(2, 2);
        set.put(3, 3);
        set.put(4, 4);
        String o = JSONObject.toJSONString(set);
        JSONObject object = JSONObject.parseObject(o);
        String pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        System.out.println(pretty);
    }
}
























