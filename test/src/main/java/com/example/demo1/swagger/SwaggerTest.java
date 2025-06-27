package com.example.demo1.swagger;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;

import java.net.URI;
import java.nio.file.Paths;

public class SwaggerTest {
    public static void main(String[] args) {
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
}
