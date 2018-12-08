package com.example.datastruture.heap.margesmailfile;

import com.example.datastruture.heap.margesmailfile.pojo.FileMargeBusi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件合并的处理
 */
public class FileMargeProc {
    /**
     * 实例信息
     */
    public static final FileMargeProc INSTANCE = new FileMargeProc();

    /**
     * 缓冲区的大小
     */
    private static final int MAX_BUFF_SIZE = 10;

    /**
     * 打开文件
     *
     * @param path 文件路径
     * @return 文件合并处理器
     */
    public FileMargeBusi openFile(File path) {
        FileMargeBusi margeBean = new FileMargeBusi();
        if (path.exists()) {
            try {
                margeBean.setInput(new FileInputStream(path));
                margeBean.setReadPath(path.getPath());
                margeBean.setBuffer(new byte[MAX_BUFF_SIZE]);
                margeBean.setBufferReadIndex(0);
                margeBean.setFileReadIndex(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return margeBean;
    }


    /**
     * 读取文件中的byte字节信息
     *
     * @param bean
     */
    public void readFile(FileMargeBusi bean) {
        if (!bean.isFinish()) {
            try {
                int readLength = bean.getInput().read(bean.getBuffer());
                bean.setFileReadIndex(readLength);
                bean.setBufferReadIndex(0);

                if (readLength == -1) {
                    bean.setFinish(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(FileMargeBusi[] bean) {
        for (int i = 0; i < bean.length; i++) {
            if (null != bean[i]) {
                try {
                    bean[i].getInput().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



















