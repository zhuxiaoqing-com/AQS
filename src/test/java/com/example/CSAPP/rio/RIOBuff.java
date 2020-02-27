package com.example.CSAPP.rio;

import java.util.List;

public class RIOBuff {
    // 错误码
    static int errno;

    /**
     * 创建一个空的读缓冲区，并且将一个打开的描述符和这个缓冲区联系起来
     *
     * @param rp
     * @param fd
     */
    public static void rio_readinitb(Rio_t rp, int fd) {
        rp.setRio_fd(fd);
        rp.setRio_cnt(0);
        rp.setRio_bufptr(rp.rio_buf.size());
    }

    /**
     * rio 读程序的核心是 rio_read 函数。
     * rio_read 函数是 Linux read 函数的带缓冲版本。
     * 当调用 rio_read 要求读 n 个字节时，读缓冲区内有 rp.getRio_cnt 个未读字节，
     * 如果缓冲区为空，那么会通过调用 read 再填满它。
     * 这个 read 调用收到一个不足值并不是错误，只不过读缓冲区是填充了一部分。
     * 一旦缓冲区非空，rio_read 就从读缓冲区复制 n 和 rp->rio_cnt 中较小值个字节到用户缓冲区，并返回复制的字节数。
     *
     * @param rp
     * @param usrbuf
     * @param n
     * @return
     */
    public static int rio_read(Rio_t rp, List usrbuf, int n) {
        int cnt;

        while (rp.getRio_cnt() <= 0) { // refill if buf is empty
            int currendReadByte = SystemCallFile.read(rp.getRio_fd(), rp.getRio_buf(), rp.getRio_buf().size());
            rp.setRio_cnt(currendReadByte);

            if (rp.getRio_cnt() < 0) {
                if (errno != SystemCallFile.EINTR) { // interrupted by sig handler return
                    return -1;
                }
            } else if (rp.getRio_cnt() == 0) {// EOF
                return 0;
            } else {
                rp.setRio_bufptr((char) rp.getRio_buf().size());
            }
        }
        // copy min(n, rp.getRio_cnt()) btyes from internal buf to user buf
        cnt = n;
        if (rp.getRio_cnt() < n) {
            cnt = rp.getRio_cnt();
        }
        usrbuf = rp.getRio_buf().subList(rp.getRio_bufptr(), cnt);
        rp.setRio_bufptr(rp.getRio_bufptr() + cnt);
        rp.setRio_cnt(rp.getRio_cnt() - cnt);
        return cnt;
    }

    /**
     * 第一行数据，最多读 maxlen， 超过 maxlen 就当两行
     *
     * @param rp
     * @param usrbuf
     * @param maxlen
     * @return
     */
    public static int rio_readlineb(Rio_t rp, List usrbuf, int maxlen) {
        int n, rc;
        int c, bufp = usrbuf.size();

        for (n = 1; n < maxlen; n++) {
            if ((rc = rio_read(rp, usrbuf, 1)) == 1) {
                if ((Character) (usrbuf.get(usrbuf.size() - 1)) == '\n') {
                    n++;
                    break;
                }
            } else if (rc == 0) {
                if (n == 1) {
                    return 0; // EOF, no data read
                } else {
                    break; // EOF, some data was read;
                }
            } else {
                return -1; //ERROR
            }
        }
        bufp = 0;
        return n - 1;
    }

    public static int rio_readnb(Rio_t rp, List usrbuf, int n) {
        int nleft = n;
        int nread;
        int bufPointer = usrbuf.size();

        while (nleft > 0) {
            if ((nread = rio_read(rp, usrbuf, nleft)) < 0) {
                return -1; //ERROR set by read()
            } else if (nread == 0) {
                break; //EOF
            }
            nleft -= nread;
            bufPointer += nread;
        }
        return n - nleft; // return >= 0
    }
}








