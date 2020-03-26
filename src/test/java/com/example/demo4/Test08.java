package com.example.demo4;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo4.testObj1.Child;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test08 {


    @Test
    public void test01() {
        Child child = new Child(1, "");
        Field[] fields = child.getClass().getDeclaredFields();
        Field field = fields[0];
        System.out.println(field.getClass());
        System.out.println(field.getType());
        System.out.println(field.getDeclaringClass());
    }

    @Test
    public void generateAsciiDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        //Swagger2MarkupConverter.from(new URL("file:///D://swagger.json"))
        Swagger2MarkupConverter.from(URI.create("file:///D://swagger.json"))
                .withConfig(config)
                .build()
                //.toFolder(Paths.get("src/docs/asciidoc/generated/all"));
                .toFile(Paths.get("src/docs/asciidoc/generated/all"));
    }


    @Test
    public void test02() {
        /*ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (true) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }*/
    }

    @Test
    public void test03() {
        System.out.println(getFloat4(3.444444f));
    }

    static public float getFloat4(float souse) {
        return Math.round(souse * 10000f) / 10000f;
    }


    @Test
    public void test04() {
        char a = 46;
        System.out.println(a);
    }

    @Test
    public void test05() throws ClassNotFoundException, IOException {
        System.out.println(JSONField.class.getName());
    }

    @Test
    public void test06() {
        double a = 221111323231424232323242134214214124124141000000000000000000000000000000000000000000000000000000000d;
        System.out.println(a);
    }

    @Test
    public void test07() {
        update();
        test();
    }

    private int numone = 0;
    private Boolean flag = false;

    public void update() {
        numone = 8;
        flag = true;
    }

    public void test() {
        if (flag) {
            System.out.println(numone);
        }
    }


    HashMap<Object, Object> hashMap = new HashMap<>();

    @Test
    public void test08() {
        ExecutorService dataManager = Executors.newSingleThreadExecutor();
        ExecutorService dataProcessor = Executors.newFixedThreadPool(10);

        dataProcessor.execute(() -> {
            // 满足条件
            if (true) {
                dataManager.execute(() -> {
                    // 更新map
                    hashMap.put(1, 1);
                    // 这里再加一个时间 每秒推送一次
                    if (true) {
                        // 推送数据
                    }
                });
            }
        });


    }

    @Test
    public void test09() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 1; i < 100; i++) {
            executorService.execute(() -> thread1());
            executorService.execute(() -> thread2());
        }
    }


    int a = 0;
    boolean b = false;

    public void thread1() {
        a = 3;
        b = true;
    }

    public void thread2() {
        while (!b) {
        }
        System.out.println(a);
        a = 0;
        b = false;
    }


    @Test
    public void test10() {
        System.out.println(new Date(1583500454000L));
    }

    @Test
    public void test11() {
        double v = Math.atan2(6, 4);
        System.out.println(v);

        double v1 = Math.atan2(-6, -4);
        System.out.println(v1 + Math.PI * 2);
        //System.out.println(v1*);
    }

    @Test
    public void test12() {
        System.out.println(1584713873580L - 1584713850889L);
        System.out.println(1584713850889L + 200f * 3000);
        System.out.println(1584713873580L + 199f * 3000);
        System.out.println(1584713850889L + 200 * 3000);
        System.out.println(1584713873580L + 199 * 3000);
    }

    /**
     * 根据坐标系可以看出来
     * source 攻击 target 计算方法应该是 y2-y1, x2-x1
     */
    @Test
    public void test13() {
        // source
        int x1 = 3;
        int y1 = 4;

        // target
        int x2 = -4;
        int y2 = -2;

        System.out.println("y2-y1: " + (y2 - y1) + "  x2-x1: " + (x2 - x1));
        System.out.println("y1-y2: " + (y1 - y2) + "  x1-x2: " + (x1 - x2));
        double v1 = Math.atan2((y2 - y1), (x2 - x1));
        double v2 = Math.atan2((y1 - y2), (x1 - x2));
        System.out.println(v1);
        System.out.println(v1 * (180 / Math.PI) + 360);

        System.out.println(v2);
        System.out.println(v2 * (180 / Math.PI));
    }


    /**
     * 根据坐标系可以看出来
     * source 攻击 target 计算方法应该是 y2-y1, x2-x1
     */
    @Test
    public void test14() {
        Map<Object, Object> hashMap = new ConcurrentHashMap<>();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(4, 4);

        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry.getKey().equals(2)) {
                hashMap.put(5, 5);
            }
            System.out.println(entry.getKey() + "...." + entry.getValue());
            if (entry.getKey().equals(4)) {
                hashMap.put(6, 6);
            }

            if (entry.getKey().equals(2)) {
                hashMap.remove(2);
            }

        }
    }
}























