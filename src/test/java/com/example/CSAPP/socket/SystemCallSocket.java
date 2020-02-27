package com.example.CSAPP.socket;

import com.example.CSAPP.rio.SystemCallFile;

public class SystemCallSocket {

    /**
     * socket 返回的描述符仅仅是部分打开的，还不能用于读写。
     * 如果完成打开套接字的工作，取决于我们是客户端还是服务器
     *
     * @param domain   AF_INEF AF_INEF6 使用的域名是 32位地址 还是64位地址
     * @param type     为数据传输方式/套接字类型 SOCK_STREAM（流格式套接字/面向连接的套接字） 和 SOCK_DGRAM（datagram 数据报套接字/无连接的套接字）
     * @param protocol 表示传输协议，常用的有 IPPROTO_TCP 和 IPPTOTO_UDP，分别表示 TCP 传输协议和 UDP 传输协议。
     * @return 若成功则返回非负描述符，若出错则为 -1;
     */
    public static int socket(int domain, int type, int protocol) {
        return -1;
    }

    /**
     * connect 函数试图与套接字地址为 addr 的服务器建立一个因特网连接，其中 addrlen 是 sizeof(sockaddr_in)。
     * connect 函数会阻塞，一直到连接成功建立或是发生错误。
     * 如果成功，clientfd 描述符现在就准备好可以读写了。
     */
    public static int connect(int clientfd, Sockaddr addr, int addrLen) {
        return -1;
    }

    /**
     * bind 函数告诉内核将 addr 中的服务器套接字描述符 sockfd 联系起来。
     * 参数 addrlen 就是 sizeof(sockaddr_in)。
     *
     * @param sockfd
     * @param addr
     * @param addrlen
     * @return
     */
    public static int bind(int sockfd, Sockaddr addr, int addrlen) {
        return -1;
    }

    /**
     * 服务器调用 listen 函数告诉内核，描述符是被服务器而不是客户端使用的
     * listen 函数将 sockfd 从一个主动套接字转化为一个监听套接字(listening socket),
     * 该套接字可以接受来自客户端的链接请求。
     * backlog 参数暗示了内核在开始拒绝连接请求之前，队列中要排队的未完成的连接请求的数量。
     *
     * @param socked
     * @param backlog
     * @return
     */
    public static int listen(int socked, int backlog) {
        return -1;
    }

    /**
     * accept 函数等待来自客户端的连接请求到达监听描述符 listenfd, 然后在 addr 中填写客户端的套接字地址，
     * 并返回一个已连接描述符(connected descriptor),这个描述符可被用来利用 Unix I/O 函数与客户端通信。
     *
     * @param listenfd
     * @param addr
     * @param addrlen
     * @return 若成功则为非负连接描述符，若出错则为 -1
     */
    public static int accept(int listenfd, Sockaddr addr, int addrlen) {
        return -1;
    }

    public static void getaddrinfo(String host, String service, AddrInfo hints, AddrInfo result) {
    }

    public static void freeaddrinfo(AddrInfo result) {
    }

    public static String gai_strerror(int errcode) {
        return "";
    }


    public static int getNameInfo(Sockaddr sa, int salen, String hostPointer, int hostLen, String servicePointer, int serviceLen, int flags) {
        return -1;
    }


    public static int open_clientfd(String hostname, String port) {
        int clientfd = 0;
        AddrInfo hints = null, listp = null, p;

        SocketUtil.memset(hints, 0, 0);
        hints.ai_socketType = SocketUtil.SOCK_STREAM; // Open a connection
        hints.ai_flags = 1;//AI_NUMERICSERV;  numeric  using a numeric port arg
        hints.ai_flags |= 1;//AI_ADDRCONFIG; recommended for connections
        getaddrinfo(hostname, port, hints, listp);

        // walk the list for one that we can successfully connect to
        for (p = listp; p != null; p = p.getAi_next()) {
            //create a socket descriptor
            if ((clientfd = socket(p.getAi_family(), p.getAi_socketType(), p.getAi_protocol())) < 0) {
                continue;
            }

            // connect to the server
            if (connect(clientfd, p.getAi_addr(), p.getAddrlen()) != -1) {
                break; // success;
            }
            SystemCallFile.close(clientfd);// connect failed ，try another
        }

        // clear up
        freeaddrinfo(listp);
        if (p == null) { // all connects failed;
            return -1;
        } else { // the last connect succeeded
            return clientfd;
        }
    }

    public static int open_listenfd(String port) {
        AddrInfo hints = null, listp = null, p;
        int listenfd = 0, optval = 1;

        // get a list of potential server addresses
        SocketUtil.memset(hints, 1, 1);
        hints.ai_socketType = SocketUtil.SOCK_STREAM; // accept connections
        hints.ai_flags = 1;//AI_PASSIVE | AI_ADDRCONFIG // on any IP address
        hints.ai_flags |= 1;// AI_NUMERICSERV; using port number
        getaddrinfo(null, port, hints, listp);

        // walk the list for on that we can bind to
        for (p = listp; p != null; p = p.getAi_next()) {
            //create a socket descriptor
            if ((listenfd = socket(p.getAi_family(), p.getAi_socketType(), p.getAi_protocol())) < 0) {
                continue; // socket failed, try the next;
            }

            // Eliminates "Address already in use" error from bind
            // setsockopt(listenfd, SOL_SOCKTE, SO_REUSEADDR, optval, sizeof(int));

            //bind the descriptor to the address
            if (bind(listenfd, p.getAi_addr(), p.getAddrlen()) == 0) {
                break;// success
            }
            SystemCallFile.close(listenfd);// bind failed, try the next
        }

        // make it a listening socket ready to accept connection requests
        if (listen(listenfd, 1024) < 0) {
            SystemCallFile.close(listenfd);
            return -1;
        }
        return listenfd;
    }

}
