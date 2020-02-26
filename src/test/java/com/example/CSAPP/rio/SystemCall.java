package com.example.CSAPP.rio;

import java.util.List;

/**
 * 系统调用函数
 */
public class SystemCall {
    public static final int EINTR = -1; // 慢系统调用被中断
    /**
     * flags 参数说明
     * O_RDONLY: 只读
     * O_WRONLY: 只写
     * O_RDWR  : 可读可写
     *
     * 截断 的意思就是清空文件
     * flags 参数也可以是一个或更多位掩码的或，为写提供给一些额外的指示
     * O_CREATE : 如果文件不存在，就创建它的一个截断的(truncated)(空)文件。
     * O_TRUNC: 如果文件已经存在，就截断它。
     * O_APPEND：在每次写操作前，设置文件位置到文件的结尾处。
     */
    /**
     *
     * @param fileName 文件名字
     * @param flags flags 参数指明了进程打算如何访问这个文件
     * @param mode 指定了新文件的访问权限。
     * @return 若成功则为新文件描述符，若出错则为 -1
     */
  public static int open(String fileName, int flags, int mode) {return -1;}

    /**
     *
     * @param fd
     * @return 进程通过一个 close 函数关闭一个打开的文件。
     */
    public static int close(int fd){return 0;}

    /**
     * read 函数从描述符为 fd 的当前文件位置复制最多 n 个字节到内存位置 buf。
     * 返回值 -1 表示一个错误，而返回值 0 表示 EOF。
     * 否则，返回值表示的是实际传送的字节数量
     * @param fd
     * @param buf
     * @param n
     * @return
     */
    public static int read(int fd, List buf, int n){
        return 0;
    }

    /**
     * write 函数从内存位置 buf 复制至多 n 个字节到描述符 fd 的当前文件位置。
     *
     * @param fd
     * @param buf
     * @param n
     * @return
     */
    public static int write(int fd, List buf, int n){
        return 0;
    }
}













