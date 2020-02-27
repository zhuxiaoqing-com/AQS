package com.example.CSAPP.multiplexing;

import java.util.Set;

public class SystemCallMultiplexing {
    /**
     * @param n        ，所有集合里面的文件描述符的最大值+1。在windows中不需要管这个。
     * @param readfds  读取是不是不阻塞了。
     * @param writefds 写入是不是不阻塞了。
     * @param errorfds 用来监视发生错误异常文件
     * @param timeout  select返回之前的时间上限。
     * @return 成功时：返回三中描述符集合中”准备好了“的文件描述符数量。
     * 超时：返回0
     * 错误：返回-1，并设置 errno
     */
    public static int select(int n, Set readfds, Set writefds, Set errorfds, long timeout) {
        return -1;
    }

    /**
     * clear all bits in fdset
     *
     * @param fdset
     */
    public static void FD_ZERO(Set fdset) {
    }

    /**
     * Clear bit fd in fdset
     *
     * @param fd
     * @param fdset
     */
    public static int FD_CLR(int fd, Set fdset) {
        return -1;
    }

    /**
     * Turn on bit fd in fdset
     *
     * @param fd
     * @param fdset
     */
    public static int FD_SET(int fd, Set fdset) {
        return -1;
    }

    /**
     * is bit fd in fdset on  就是 set 里面是否有 fd
     *
     * @param fd
     * @param fdset
     */
    public static int FD_ISSET(int fd, Set fdset) {
        return -1;
    }

}
