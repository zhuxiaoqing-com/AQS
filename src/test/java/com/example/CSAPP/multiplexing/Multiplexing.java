package com.example.CSAPP.multiplexing;


import java.util.HashSet;
import java.util.Set;

/**
 * represents a pool of connected descriptors
 */
class Pool {
    final int FD_SETSIZE = 10;
    /**
     * largest descriptor in read_set
     */
    int maxfd;

    /**
     * set of all active descriptors
     */
    Set<Integer> read_set = new HashSet<>();;

    /**
     * subset of descriptors ready for reading
     */
    Set<Integer> ready_set = new HashSet<>();;

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
    Set<Integer> clientfd = new HashSet<>(FD_SETSIZE);

    /**
     * set of active read buffer
     */
    Set<Integer> clientrio = new HashSet<>(FD_SETSIZE);
}

public class Multiplexing {

}
