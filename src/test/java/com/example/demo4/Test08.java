package com.example.demo4;

import com.example.demo4.testObj1.Child;
import com.sun.javadoc.RootDoc;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;

import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Paths;
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
        System.out.println(getFloat4(3.444444f));
    }


}























