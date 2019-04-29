package com.example.demo1.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/7/20.
 */
public class FileUtil {


    private static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static final String CODE_UTF_8 = "utf8";
    public static final String CODE_GBK = "gbk";
    public static final String TYPE_TXT = "txt";
    public static final String TYPE_JAVA = "java";
    public static final String TYPE_CVS = "CVS";

    /**
     * 代转码操作的复制文件夹
     *
     * @param @param  srcUrl
     * @param @param  targetUrl
     * @param @throws IOException
     * @Description: 转码方式在成员变量中设置
     * @author HuangDongliang
     */
    private static void copyDirectoryAndJavaChangeCod(String srcUrl, String targetUrl)
            throws IOException {
        System.out.println("开始复制文件夹中内容，并且进行转码：" + CODE_GBK + "->" + CODE_UTF_8);
        System.out.println("资源文件夹：" + srcUrl + "-->目标文件夹" + targetUrl);
        // 创建目标文件夹
        (new File(targetUrl)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(srcUrl)).listFiles();
        for (int i = 0; i < file.length; i++) {
            System.out.println("处理文件：" + file[i].getName());
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetUrl).getAbsolutePath() + File.separator + file[i].getName());
                // 复制文件
                String type = file[i].getName().substring(
                        file[i].getName().lastIndexOf(".") + 1);


                if (type.equalsIgnoreCase(TYPE_JAVA) || type.equalsIgnoreCase(TYPE_TXT)) {
                    copyFile(sourceFile, targetFile, CODE_GBK, CODE_UTF_8);
                    System.out.println("转码文件" + file[i].getName());

                } else
                    copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                String fileName = file[i].getName();
                if (fileName.equalsIgnoreCase(TYPE_CVS)) {
                    System.out.println("跳过复制SVN目录");
                    continue;
                }
                // 复制目录
                String sourceDir = srcUrl + File.separator + file[i].getName();
                String targetDir = targetUrl + File.separator + file[i].getName();
                copyDirectoryAndJavaChangeCod(sourceDir, targetDir);
            }
        }


    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    // 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + File.separator + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + File.separator + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * @param srcFileName
     * @param destFileName
     * @param srcCoding    源文件字符集
     * @param destCoding   目标文件字符集
     * @throws IOException
     */
    public static void copyFile(File srcFileName, File destFileName,
                                String srcCoding, String destCoding) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    srcFileName), srcCoding));
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(destFileName), destCoding));
            char[] cbuf = new char[1024 * 5];
            int len = cbuf.length;
            int off = 0;
            int ret = 0;
            while ((ret = br.read(cbuf, off, len)) > 0) {
                off += ret;
                len -= ret;
            }
            bw.write(cbuf, 0, off);
            bw.flush();
        } finally {
            if (br != null)
                br.close();
            if (bw != null)
                bw.close();
        }

    }

    /**
     * @param filepath
     * @throws IOException
     */
    public static void del(String filepath) throws IOException {
        File f = new File(filepath);// 定义文件路径
        if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
            if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
                f.delete();
            } else {// 若有则把文件放进数组，并判断是否有下级目录
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;
                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                        del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
                    }
                    delFile[j].delete();// 删除文件
                }
            }
        }
    }


    /**
     * 获取路径下所有文件名
     *
     * @param path
     * @return
     */
    public static String[] getFile(String path) {
        File file = new File(path);
        String[] name = file.list();
        return name;
    }

    /**
     * @param sourceDirPath
     * @param targetDirPath
     * @throws IOException
     */
    public static void copyDir(String sourceDirPath, String targetDirPath) throws IOException {
        // 创建目标文件夹
        (new File(targetDirPath)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDirPath)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 复制文件
                String type = file[i].getName().substring(file[i].getName().lastIndexOf(".") + 1);

                if (type.equalsIgnoreCase("txt"))
                    FileUtil.copyFile(file[i], new File(targetDirPath + file[i].getName()), FileUtil.CODE_UTF_8,
                            FileUtil.CODE_GBK);
                else
                    FileUtil.copyFile(file[i], new File(targetDirPath + file[i].getName()));
            }
            if (file[i].isDirectory()) {
                // 复制目录
                String sourceDir = sourceDirPath + File.separator + file[i].getName();
                String targetDir = targetDirPath + File.separator + file[i].getName();
                FileUtil.copyDirectiory(sourceDir, targetDir);
            }
        }
    }

    /**
     * 读取文件中内容
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String readFileToString(String path) {
        String result = null;
        FileInputStream in;

        try {
            in = new FileInputStream(path);
            InputStreamReader inReader = new InputStreamReader(in);
            BufferedReader bufReader = new BufferedReader(inReader);
            try {

                result = bufReader.readLine();
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取文件中内容
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static BufferedReader readFileToReader(File file) {
        FileInputStream in;
        BufferedReader bufReader = null;
        InputStreamReader inReader = null;
        try {
            in = new FileInputStream(file);
            inReader = new InputStreamReader(in, CODE_UTF_8);
            bufReader = new BufferedReader(inReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
//    	finally{
//			if(bufReader!=null) {try{bufReader.close();} catch(IOException e){e.printStackTrace();} }
//			if(inReader!=null) {try{inReader.close();} catch(IOException e){e.printStackTrace();} }
//		}
        return bufReader;

    }

    public static String loadAFileToStringDE2(File f) throws IOException {
//        long beginTime = System.currentTimeMillis();
        InputStream is = null;
        String ret = null;
        try {
            is = new FileInputStream(f);
            long contentLength = f.length();
            byte[] ba = new byte[(int) contentLength];
            is.read(ba);
            ret = new String(ba);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }

        return ret;
    }


    public static String readFromFileToString(File f) {
        InputStream is = null;
        String ret = null;
        try {
            try {
                is = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            long contentLength = f.length();
            byte[] ba = new byte[(int) contentLength];
            try {
                is.read(ba);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ret = new String(ba);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
        return ret;
    }

    /**
     * 文件转成字节数组
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readFileToBytes(String path) throws IOException {
        byte[] b = null;
        InputStream is = null;
        File f = new File(path);
        try {
            is = new FileInputStream(f);
            b = new byte[(int) f.length()];
            is.read(b);
        } finally {
            if (is != null)
                is.close();
        }
        return b;
    }

    /**
     * 将byte写入文件中
     *
     * @param fileByte
     * @param filePath
     * @throws IOException
     */
    public static void byteToFile(byte[] fileByte, String filePath) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(filePath));
            os.write(fileByte);
            os.flush();
        } finally {
            if (os != null)
                os.close();
        }
    }


    /**
     * 折分数组
     *
     * @param ary
     * @param subSize
     * @return
     */
    public static List<List<Object>> splitAry(Object[] ary, int subSize) {
        int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;

        List<List<Object>> subAryList = new ArrayList<List<Object>>();

        for (int i = 0; i < count; i++) {
            int index = i * subSize;

            List<Object> list = new ArrayList<Object>();
            int j = 0;
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }

            subAryList.add(list);
        }

        return subAryList;
    }

    /**
     * @param mobile
     * @return
     */
    public static String ArrayToString(Object[] mobile) {
        String destId = "";
        for (Object phone : mobile) {
            destId += " " + phone;
        }
        return destId.trim();
    }


    public boolean isUtf8(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[3];
        in.read(b);
        in.close();
        if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
            System.out.println(file.getName() + "：编码为UTF-8");
            return true;
        } else {
//          System.out.println(file.getName() + "：可能是GBK，也可能是其他编码");
            return false;
        }
    }


    /**
     * 找出指定目录及其子目录下，满足指定后缀的文件的绝对路径。
     * 提示：方法中出现异常被内部捕获。
     *
     * @param dir    指定目录
     * @param suffix 文件名后缀
     * @throws IllegalArgumentException
     */
    public static List<String> find(String dir, String suffix) {
        List<String> list = new ArrayList<String>();
        try {
            File file = new File(dir);
            if (file.exists() && file.isDirectory()) {
                find(file, suffix, list);
            } else {
                throw new IllegalArgumentException("param \"dir\" must be an existing directory .dir = " + dir);
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * 递归遍历，查找满足后缀的文件
     *
     * @param dirFile 必须为一个存在的目录.不能为null
     * @param suffix
     * @param list    递归遍历目录记录满足后缀的文件的绝对路径。
     */
    private static void find(File dirFile, String suffix, List<String> list) {
        if (dirFile.exists() && dirFile.isDirectory()) {
            File[] subFiles = dirFile.listFiles();
            for (File subFile : subFiles) {
                if (subFile.isDirectory()) {
                    find(subFile, suffix, list);
                } else {
                    String path = subFile.getAbsolutePath();
                    if (path.endsWith(suffix)) {
                        list.add(path);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("param \"dir\" must be an existing directory .dir = " + dirFile.getAbsolutePath());
        }
    }


    /**
     * 获取配置文件
     *
     * @param subDir
     * @return
     */
    public static File getConfigPath(String subDir) {
        File configFile;

        /**
         * 先从根目录获取配置文件
         */
        String path = System.getProperty("user.dir") + File.separator + subDir;
        configFile = new File(path);
        if (!configFile.exists()) {
            /**
             *  从classpath 获取
             */
            path = FileUtil.class.getResource("/").getPath() + subDir;
            configFile = new File(path);
        }


        return configFile;
    }


}