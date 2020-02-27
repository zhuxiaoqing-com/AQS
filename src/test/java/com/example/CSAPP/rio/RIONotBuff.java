package com.example.CSAPP.rio;

import java.util.List;

/**
 * 对同一个描述符，可以任意交错的调用 rio_readn 和 rio_writen
 * <p>
 * 如果 rio_readn 和 rio_writen 函数被一个从应用信号处理程序返回中断，那么每个函数都会手动的重启 read 或 write。
 * 为了尽可能有较好的可移植性，我们允许被中断的系统调用，且在有必要时重启它们。
 *
 * 不带缓冲的方法，包装 read write 系统调用的主要原因还是帮其自动重启可能会被中断的系统调用。
 */
public class RIONotBuff {
    // 错误码
    public static int errno;

    /**
     * rio_readn 函数从描述符 fd 的当前文件位置最多传送 n 个字节到内存位置 usrbuf。
     * rio_readn 函数在遇到 EOF 时只能返回一个不足值。
     *
     * @param fd     文件描述符
     * @param usrbuf 用来保存读取到的数据的容器
     * @param n      要读取多少个字节
     * @return 若成功则为传送的字节数，若 EOF 则为 0(只对 rio_readn 而言)，若出错则为 -1.
     */
    public static int rio_readn(int fd, List usrbuf, int n) {
        int nleft = n;
        int nread;
        List bufp = usrbuf;

        while (nleft > 0) {
            if ((nread = SystemCallFile.read(fd, bufp, nleft)) < 0) {
                if (errno == SystemCallFile.EINTR) {// interrupted by sig handler return
                    nread = 0; // and call read() again;
                } else {
                    return -1; // errno set by read();
                }
            } else if (nread == 0) { // EOF
                break;
            }
            nleft -= nread;
            bufp.add(nread);
        }

        return n - nleft;

    }

    /**
     * rio_writen 函数从位置 usrbuf 传送 n 个字节到描述符 fd。
     * rio_writen 绝不会返回不足值
     *
     * @param fd     文件描述符
     * @param usrbuf 用来保存读取到的数据的容器
     * @param n      要读取多少个字节
     * @return 若成功则为传送的字节数，若 EOF 则为 0(只对 rio_readn 而言)，若出错则为 -1.
     */
    public static int rio_rwriten(int fd, List usrbuf, int n) {
        int nleft = n;
        int nwritten;
        List bufp = usrbuf;

        while ((nleft > 0)) {
            if((nwritten = SystemCallFile.write(fd,bufp,nleft))<=0){
                if(errno == SystemCallFile.EINTR){ // interrupted by sig handler return
                    nwritten = 0; // and call write() again
                } else {
                    return  -1;// errno set by write()
                }
                nleft -= nwritten;
                bufp.add(nwritten);
            }
        }
        return -1;
    }


}
