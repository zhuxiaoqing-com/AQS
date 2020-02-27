package com.example.CSAPP.multiplexing;


import com.example.CSAPP.rio.RIOBuff;
import com.example.CSAPP.rio.RIONotBuff;
import com.example.CSAPP.rio.Rio_t;
import com.example.CSAPP.rio.SystemCallFile;
import com.example.CSAPP.socket.Sockaddr;
import com.example.CSAPP.socket.SystemCallSocket;

import java.util.ArrayList;
import java.util.List;

public class Multiplexing {
    static int byte_cnt;

    /**
     * 来自一个新客户端的链接请求到达，
     * 一个已存在的客户端的已连接描述符准备好可以读了。
     * 当一个连接请求到达时，服务器打开连接，并调用 add_client 函数，将该客户端添加到池里。
     * 最后，服务器调用 check_clients 函数，吧来自每个准备好的已连接描述符的一个文本行回送回去。
     *
     * @param args
     */
    public static void main(String[] args) {
        int listenfd = 0, connfd = 0;
        int clientlen = 0;
        Sockaddr clientaddr = null;
        Pool pool = null;

        if (args.length != 2) {
            System.out.println("参数错误");
            System.exit(0);
        }
        listenfd = SystemCallSocket.open_listenfd(args[1]);
        init_pool(listenfd, pool);

        while (true) {
            // wait for listening/connected descriptor(s) to become ready
            pool.ready_set = pool.read_set;
            pool.nready = SystemCallMultiplexing.select(listenfd + 1, pool.ready_set, null, null, 0);

            // if listening descriptor ready, add new client to pool
            if (SystemCallMultiplexing.FD_ISSET(listenfd, pool.ready_set) > 0) {
                connfd = SystemCallSocket.accept(listenfd, clientaddr, clientlen);
                add_client(connfd, pool);
            }
            //echo a text line from each ready connected descriptor
            check_clients(pool);
        }
    }


    /**
     * init_pool 函数 初始化 客户端池。
     * clientfd 数组表示已连接描述符的集合，其中整数 -1 表示一个可用的槽位。
     * 初始时，已连接描述符集合是空的，而且监听描述符是 select 读集合中唯一的描述符
     *
     * @param listenfd
     * @param pool
     */
    private static void init_pool(int listenfd, Pool pool) {
        // initially, there are no connected descriptors
        int i;
        pool.maxi = -1;
        for (i = 0; i < Pool.FD_SETSIZE; i++) {
            pool.clientfd[i] = -1;
        }
        // initally, listenfd is only member of select read set;
        pool.maxfd = listenfd;
        SystemCallMultiplexing.FD_ZERO(pool.read_set);
        SystemCallMultiplexing.FD_SET(listenfd, pool.read_set);
    }

    /**
     * add_client 函数 添加一个新的客户端到活动客户端池中。在 clientfd 数组中找到一个空槽位后，服务器将这个已连接描述符添加到数组中，并初始化相应的
     * RIO 读缓冲区，这样一来我们就能够对这个描述符调用 rio_readlineb。
     * 然后我们将这个已连接描述符添加到 select 读集合，并更新该池的一些全局属性。
     * maxfd 变量记录了 select 的最大文件描述符。
     * maxi 变量记录的是到 clientfd 数组的最大索引，
     * 这样 check_clients 函数就无需搜索整个数组了。
     *
     * @param connfd
     * @param pool
     */
    private static void add_client(int connfd, Pool pool) {
        int i;
        pool.nready--; // 将 listenfd 从 已经准备好的描述符数量中减去;
        for (i = 0; i < Pool.FD_SETSIZE; i++) {
            if (pool.clientfd[i] < 0) {
                // add connected descriptor to the pool
                pool.clientfd[i] = connfd;
                RIOBuff.rio_readinitb(pool.clientrio[i], connfd);

                // add the descriptor to descriptor set
                SystemCallMultiplexing.FD_SET(connfd, pool.read_set);

                // update max descriptor and pool high water mark
                if (connfd > pool.maxfd) {
                    pool.maxfd = connfd;
                }
                if (i > pool.maxi) {
                    pool.maxi = i;
                }
                break;
            }
            if (i == Pool.FD_SETSIZE) {// couldn't find an empty slot
                System.out.println("couldn't find an empty slot");
            }
        }

    }

    /**
     * check_clients 函数会送来自每个准备好的已连接描述符的一个文本行。
     * 如果成功的从描述符读取了一个文本行，那么就将该文本行回送到客户端。
     * 注意，在第15行 我们维护着一个从所有 客户端接收到的全部字节的累计值(byte_cnt)。
     * 如果因为客户端关闭这个连接中它的那一端，检测到 EOF，那么关闭这边连接端，
     * 并从池中清除掉这个描述符
     *
     * @param pool
     */
    private static void check_clients(Pool pool) {
        int MAXLINE = 1;
        int i, connfd, n;
        List buf = new ArrayList<>();
        Rio_t rio;

        for (i = 0; i <= pool.maxi && pool.nready > 0; i++) {
            connfd = pool.clientfd[i];
            rio = pool.clientrio[i];

            // if the descriptor is ready, echo a text line from it
            if (connfd > 0 && SystemCallMultiplexing.FD_ISSET(connfd, pool.ready_set) > 0) {
                pool.nready--;
                if ((n = RIOBuff.rio_readlineb(rio, buf, MAXLINE)) != 0) {
                    byte_cnt += n; // 从所有客户端收到的全部字节的累计值
                    RIONotBuff.rio_rwriten(connfd, buf, n); // 吧读取的文本全部回送给客户端
                } else {
                    //EOF detected, remove descriptor from pool
                    SystemCallFile.close(connfd);
                    SystemCallMultiplexing.FD_CLR(connfd, pool.read_set);
                    pool.clientfd[i] = -1;
                }
            }
        }
    }

}
