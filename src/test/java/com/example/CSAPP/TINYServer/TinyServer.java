package com.example.CSAPP.TINYServer;

/**
 * tiny.c a simple,iterative HTTP/1.0 web server that uses the GET method to serve static and dynamic content
 */
public class TinyServer {
    static int MAX_LINE = 100;

    /**
     * doit 函数处理一个 HTTP 事务。
     * 首先，我们读和解析请求行(第11~14行)。注意，我们使用 rio_readlineb 函数读取请请求行。
     * <p>
     * Tiny 只支持 GET 方法。如果客户端请求其他方法(比如 POST)，我们发送给它一个错误信息，并返回到主程序，
     * 主程序随后关闭连接并等待下一个连接请求。
     * 否则，我们读并且(像我们将要看到的那样)忽略任何请求报头。
     * <p>
     * 然后，我们将 URI 解析为一个文件名和一个可能为空的 CGI 参数字符串，并且设置一个标志，表明请求的是静态内容，我们就验证改文件是一个普通文件，而我们是
     * 有读权限的。
     * 如果是这样我们就向客户端提供静态内容。
     * 相似的，如果请求的是动态内容，我们就验证该文件是可执行文件，如果是这样，我们就继续，并且提供动态内容。
     *
     * @param fd
     */
    static void doit(int fd) {
        int is_static;
        int[] sbuf = new int[1];
        char[] buf, method, version;
        String fileName = null, uri = null, cgiargs = null;
        Object rio = null;

        // read request line and headers
        // 比较 method 是否是 get 请求
        if (false) {
            clienterror(fd, fileName, "501", "Not implemented", "Tiny does not implement this method");
            return;
        }
        // 读取请求头 并忽略任何请求报头
        read_requesthdrs(rio);

        // parse URI from GET request  URI 解析为一个文件名和一个可能为空的 CGI 参数字符串
        is_static = parse_uri(uri, fileName, cgiargs);

        // 获取文件元数据 如果小于零 就是获取失败
        if (1 < 0/*stat(fileName, sbuf)*/) {
            clienterror(fd, fileName, "404", "Not found", "Tiny couldn’t find this file");
            return;
        }
        // serve static content
        if (is_static == 1) {
            // 判断是否是普通文件 和 该文件是否可读取
            if (false) {
                clienterror(fd, fileName, "403", "forbiddden", "Tiny couldn't read the file");
                return;
            }
            // 服务静态请求
            serve_static(fd, fileName, sbuf.length);
        } else {
            // 判断是否是普通文件 和 该文件是否可执行
            if (false) {
                clienterror(fd, fileName, "403", "forbiddden", "Tiny couldn't run the CGI program");
                return;
            }
            // 服务动态请求
            serve_dynamic(fd, fileName, cgiargs);
        }


    }


    static void read_requesthdrs(Object rp) {

    }

    static int parse_uri(String uri, String filename, String cgiargs) {
        return 1;
    }

    static void serve_static(int fd, String filename, int filesize) {

    }


    private static void serve_dynamic(int fd, String filename, String cgiargs) {
    }

    static void get_fileType(String filename, String cgiArgs) {

    }

    static void clienterror(int fd, String cause, String errnum, String shortMsg, String longMsg) {

    }

    public static void main(String[] args) {
        int listenfd, connfd = 0;
        char hostname[] = new char[MAX_LINE];
        char port[] = new char[MAX_LINE];
        int clientlen;
        Object clientAddr;

        // Check command-line args
        if (args.length != 2) {
            System.out.println("args length error");
            return;
        }

        //listenfd = open_listenfd(args[1]);

        while (true) {
            //connfd = accept(listenfd, clientAddr, clientlen);
            doit(connfd);
            //close(connfd);
        }
    }
}


















