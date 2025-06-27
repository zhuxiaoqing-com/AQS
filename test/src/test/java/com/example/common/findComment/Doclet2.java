package com.example.common.findComment;

import com.sun.javadoc.*;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * 类说明：打印类及其字段、方法的注释<br>
 * 使用javadoc实现<br>
 * 需要在工程中加载jdk中的包$JAVA_HOME/lib/tools.jar
 */
public class Doclet2 {

    public static final String PACKAGE = "com";


    /**
     * 测试
     */
    public static void main(String[] args) {
        println();
    }

    /**
     * 打印类及其字段、方法的注释
     *
     */
    public static void println() {
        setRootDoc();
        StringBuilder buffer = new StringBuilder();

        ClassDoc[] classes = Doclet2.root.classes();
        for (ClassDoc classDoc : classes) {
            buffer.append(classDoc.name()).append('\n');
            buffer.append('\t').append(classDoc.commentText()).append('\n');
            buffer.append('\t').append("字段").append('\n');
            // classDoc.fields(false) 读取 private 字段
            FieldDoc[] fields = classDoc.fields(false);
            for (FieldDoc field : fields) {
                System.out.println(field.type());
                buffer.append('\t').append('\t').append(field.name()).append('\n');
                buffer.append('\t').append('\t').append('\t').append(field.commentText()).append('\n');
                AnnotationDesc[] annotations = field.annotations();
                for(AnnotationDesc annotationDesc : annotations){
                    annotationDesc.annotationType();
                    System.out.println(annotationDesc.annotationType());
                    for (AnnotationDesc.ElementValuePair elementValuePair: annotationDesc.elementValues()){
                        System.out.println(elementValuePair.value());
                        System.out.println(elementValuePair.toString());
                        System.out.println(elementValuePair.element().name());
                    }
                }
            }
            buffer.append('\t').append("方法").append('\n');
            MethodDoc[] methods = classDoc.methods();
            for (MethodDoc method : methods) {
                buffer.append('\t').append('\t').append(method.name()).append('\n');
                buffer.append('\t').append('\t').append('\t').append(method.commentText()).append('\n');
            }
        }
        System.out.println(buffer);
    }

    /**
     * 文档根节点
     */
    private static RootDoc root;

    /**
     * javadoc调用入口
     *
     * @param root
     * @return
     */
    public static boolean start(RootDoc root) {
        Doclet2.root = root;
        return true;
    }

    private static void setRootDoc() {
        // set DoorDoc
        com.sun.tools.javadoc.Main.execute(buildCmdParam());
    }

    private static String[] buildCmdParam() {
        ArrayList<String> list = new ArrayList<>();
        list.add("-doclet");// 指定使用这个类
        list.add(Doclet2.class.getName());
        list.add("-sourcepath");// 指定使用这个目录下面的源文件
        list.add("./src/main/java");
        list.add("-subpackages"); // 指定扫描哪个包
        list.add(PACKAGE);
        return list.toArray(new String[0]);
    }


    @Test
    public void test01(){
        File file = new File("./src/main/java");
        String path = file.getAbsolutePath();
        System.out.println(path);
    }
}
