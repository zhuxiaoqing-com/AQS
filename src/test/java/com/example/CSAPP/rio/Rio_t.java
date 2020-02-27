package com.example.CSAPP.rio;

import java.util.ArrayList;
import java.util.List;

public class Rio_t {
    int RIO_BUFFSIZE = 8192;
    int rio_fd; // Descriptor for this internal buf
    int rio_cnt; // unread bytes in internal buf
    int rio_bufptr; // next unread byte in internal buf
    List rio_buf = new ArrayList(RIO_BUFFSIZE); // internal buf

    public int getRIO_BUFFSIZE() {
        return RIO_BUFFSIZE;
    }

    public void setRIO_BUFFSIZE(int RIO_BUFFSIZE) {
        this.RIO_BUFFSIZE = RIO_BUFFSIZE;
    }

    public int getRio_fd() {
        return rio_fd;
    }

    public void setRio_fd(int rio_fd) {
        this.rio_fd = rio_fd;
    }

    public int getRio_cnt() {
        return rio_cnt;
    }

    public void setRio_cnt(int rio_cnt) {
        this.rio_cnt = rio_cnt;
    }

    public int getRio_bufptr() {
        return rio_bufptr;
    }

    public void setRio_bufptr(int rio_bufptr) {
        this.rio_bufptr = rio_bufptr;
    }

    public List getRio_buf() {
        return rio_buf;
    }

    public void setRio_buf(List rio_buf) {
        this.rio_buf = rio_buf;
    }
}
