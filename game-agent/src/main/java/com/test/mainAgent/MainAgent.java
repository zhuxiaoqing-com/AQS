package com.test.mainAgent;


import com.test.preAgent.AopAgentTransformer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.HashMap;
import java.util.Map;
/**
 * addTransformer
 * void addTransformer(ClassFileTransformer transformer,
 *                     boolean canRetransform)
 * 注册提供的转换器。所有以后的类定义对于该转换器都是可见的，任何已注册转换器所依赖的类定义除外。
 * 加载类、重定义类时将调用该转换器。如果 canRetransform 为 true，那么重转换类时也将调用该转换器。
 * 有关转换调用的顺序，请参见 ClassFileTransformer.transform。
 * 如果转换器在执行过程中抛出异常，JVM 将仍然按顺序调用其他已注册转换器。
 * 可以多次添加相同的转换器，但建议最好创建一个新的转换器类实例来避免这样做。
 * 此方法旨在用于检测，正如类规范所述。
 *
 * 参数：
 * transformer - 要注册的转换器
 * canRetransform - 此转换器的转换是否可以重转换
 * 抛出：
 * NullPointerException - 如果传递了 null 转换器
 * UnsupportedOperationException - 如果 canRetransform 为 true 但当前 JVM 的配置不允许重转换（isRetransformClassesSupported() 为 false）。
 * 从以下版本开始：
 * 1.6
 */

/**
 * @Auther: Administrator
 * @Date: 2020/4/8 10:58
 * @Description:
 */
public class MainAgent {
    //private static final String PATCH_DIR = System.getProperty("user.dir") + File.separator + "data" + File.separator + "class";
    private static final String PATCH_DIR = "D:";

    private static final String PACKAGE_START = "com.agentTest.preAgentTest.";

    public static void agentmain(String args, Instrumentation inst) throws UnmodifiableClassException {
        AopAgentTransformer aopAgentTransformer = new AopAgentTransformer();
        inst.addTransformer(aopAgentTransformer);
        inst.removeTransformer(aopAgentTransformer);
        //inst.removeTransformer();
        hotswap(inst);
        //retransformClasses(inst, "Test1");
    }

    private static void retransformClasses(Instrumentation inst, String className) {
        Class<?>[] allClazz = inst.getAllLoadedClasses();
        try {
            for (Class<?> clazz : allClazz) {
                if (clazz.getName().endsWith(className)) {
                    inst.retransformClasses(clazz);
                }
            }
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }

    private static void hotswap(Instrumentation inst) {
        Map<String, String> reloadFiles = getFullClassNameFilePathMap();
        Class<?>[] allClazz = inst.getAllLoadedClasses();
        for (Class<?> clazz : allClazz) {
            String filePath = reloadFiles.get(clazz.getName());
            if (filePath == null) {
                continue;
            }
            try {
                File file = new File(filePath);
                byte[] bytes = fileToBytes(file);
                System.out.println("filePath=" + filePath + ", 文件大小：" + bytes.length);
                ClassDefinition classDefinition = new ClassDefinition(clazz, bytes);
                inst.redefineClasses(classDefinition);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] fileToBytes(File file) throws IOException {

        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        return bytes;
    }

    public static Map<String, String> getFullClassNameFilePathMap() {
        Map<String, String> map = new HashMap<String, String>();
        File root = new File(PATCH_DIR);
        File[] fileArray = root.listFiles();
        if (fileArray != null) {
            for (File file : fileArray) {
                if (file.getName().endsWith(".class")) {
                    String filePath = file.getPath();
                    String fullClassName = PACKAGE_START + file.getName().replace(".class", "");
                    map.put(fullClassName, filePath);
                }
            }
        }
        return map;
    }

    public void dynamicAop() {

    }
}
