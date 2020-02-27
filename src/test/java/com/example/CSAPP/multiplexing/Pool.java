package com.example.CSAPP.multiplexing;

import com.example.CSAPP.rio.Rio_t;

import java.util.HashSet;
import java.util.Set;

/**
 * represents a pool of connected descriptors
 */
public class Pool {
   public static final int FD_SETSIZE = 10;
    /**
     * largest descriptor in read_set
     */
    int maxfd;

    /**
     * set of all active descriptors
     */
    Set<Integer> read_set = new HashSet<>();
    ;

    /**
     * subset of descriptors ready for reading
     */
    Set<Integer> ready_set = new HashSet<>();
    ;

    /**
     * Number of ready descriptors from select
     */
    int nready;

    /**
     * high water index into client array
     */
    int maxi;

    /**
     * set of active descriptor
     */
    int[] clientfd = new int[FD_SETSIZE];

    /**
     * set of active read buffer
     */
    Rio_t[] clientrio = new Rio_t[FD_SETSIZE];


    public int getFD_SETSIZE() {
        return FD_SETSIZE;
    }

    public int getMaxfd() {
        return maxfd;
    }

    public void setMaxfd(int maxfd) {
        this.maxfd = maxfd;
    }

    public Set<Integer> getRead_set() {
        return read_set;
    }

    public void setRead_set(Set<Integer> read_set) {
        this.read_set = read_set;
    }

    public Set<Integer> getReady_set() {
        return ready_set;
    }

    public void setReady_set(Set<Integer> ready_set) {
        this.ready_set = ready_set;
    }

    public int getNready() {
        return nready;
    }

    public void setNready(int nready) {
        this.nready = nready;
    }

    public int getMaxi() {
        return maxi;
    }

    public void setMaxi(int maxi) {
        this.maxi = maxi;
    }

    public static int getFdSetsize() {
        return FD_SETSIZE;
    }

    public int[] getClientfd() {
        return clientfd;
    }

    public void setClientfd(int[] clientfd) {
        this.clientfd = clientfd;
    }

    public Rio_t[] getClientrio() {
        return clientrio;
    }

    public void setClientrio(Rio_t[] clientrio) {
        this.clientrio = clientrio;
    }
}