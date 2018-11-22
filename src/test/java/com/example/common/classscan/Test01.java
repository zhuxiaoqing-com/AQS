package com.example.common.classscan;

import org.junit.Test;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class Test01 {
   @Test
    public void test01() {
       List<Number> list = new ArrayList<>();
       List<Integer> collect = list.stream()
               .map(x->(Integer)x)
               .collect(Collectors.toList());
       boolean assignableFrom = Collection.class.isAssignableFrom(List.class);
       System.out.println(assignableFrom);
   }

    public static void main(String[] a) {
        Set<Class<?>> com = findClassByPkg("com.example");
        System.out.println(com);
    }

    public static Set<Class<?>> findClassByPkg(String packageName) {
        Set<Class<?>> allClazz = new LinkedHashSet<>();
        // win linux 包路径都是 /
        String packageDir = packageName.replace('.', '/');
        try {
            /*
             URL 主要用来判断 是 jar 还是普通文件 URL 可以用来链接网路 http://www.baidu.com
             getResources 和 getResource 区别
             两者都是以 classpath 为根目录,搜索给定的文件名字
              getResource 只要搜到了一个就返回
              而 getResources 会搜索全部 搜到了一个 还会继续搜索下去直到扫描完毕
              */
            Enumeration<URL> dirs = Test01.class.getClassLoader().getResources(packageDir);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                // 获取协议名 jar http file 等 http://www.baidu.com http 是协议名
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    // 文件夹路径
                    // url.getFile() 获取文件名
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    allClazz.addAll(findClassFromDir(packageName, filePath));
                } else if ("jar".equals(protocol)) {
                    JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
                    try (JarFile jarFile = urlConnection.getJarFile()) {
                        allClazz.addAll(findClassFromJar(jarFile, packageDir));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allClazz;
    }


    /**
     * 加载文件夹里面的文件
     *
     * @param packageName
     * @param filePath
     * @return
     */
    public static Set<Class<?>> findClassFromDir(String packageName, String filePath) {
        Set<Class<?>> ret = new LinkedHashSet<>();
        File dir = new File(filePath);
        // 只要 .class 文件或者是文件夹
        File[] files = dir.listFiles((x, y) -> x.isDirectory() || y.endsWith(".class"));
        for (File f : files) {
            if (f.isDirectory()) {
                ret.addAll(findClassFromDir(packageName + "." + f.getName(), f.getAbsolutePath()));
            } else {
                // forName 不能带 .class 所以将 .class 去除
                String className = f.getName().substring(0, f.getName().length() - 6);
                try {
                    Class<?> clazz = Class.forName(packageName + "." + className, false, Thread.currentThread().getContextClassLoader());
                    ret.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("读取文件夹中的Class文件出错");
                }
            }
        }
        return ret;
    }

    /**
     * jar 归档文件
     *
     * @param jar
     * @param packageDir package 转换成文件目录格式的字符串
     * @return
     */
    public static Set<Class<?>> findClassFromJar(JarFile jar, String packageDir) {
        Set<Class<?>> ret = new LinkedHashSet<>();
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();

            if (entry.isDirectory()) {
                // jar 中的 entry 是所有层级文件都列出来的，所以文件夹实际上一点用都没有
            } else {
                String name = entry.getName();
                //以packageDir开头并且是class文件
                if (!name.startsWith(packageDir) || !name.endsWith(".class")) {
                    continue;
                }
                name = name.replaceAll("/", ".");
                name = name.substring(0, name.length() - 6);
                try {
                    Class<?> clazz = Class.forName(name, false, Thread.currentThread().getContextClassLoader());
                    ret.add(clazz);
                } catch (Throwable e) {
                    System.out.println("读取Jar中的Class文件出错");
                }
            }
        }
        return ret;
    }
}
