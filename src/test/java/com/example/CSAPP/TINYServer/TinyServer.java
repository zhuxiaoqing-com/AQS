package com.example.CSAPP.TINYServer;

/**
 * tiny.c a simple,iterative HTTP/1.0 web server that uses the GET method to serve static and dynamic content
 */
public class TinyServer {
    static int MAX_LINE = 100;

    void doit(int fd) {

    }

    void read_requesthdrs(Object rp) {

    }

    int parse_uri(String uri, String filename, String cgiargs) {

    }

    void serve_static(int fd, String filename, int filesize) {

    }

    void get_fileType(String filename, String cgiArgs) {

    }

    void clienterror(int fd, String cause, String errnum, String shortMsg, String longMsg) {

    }

    public static void main(String[] args) {
        int listenfd, connfd;
        char hostname[] = new char[MAX_LINE];
        char port[] = new char[MAX_LINE];
        int clientlen;
        Object clientAddr;

        // Check command-line args
        if (args.length != 2) {
            System.out.println("args length error");
        }
    }
}


















