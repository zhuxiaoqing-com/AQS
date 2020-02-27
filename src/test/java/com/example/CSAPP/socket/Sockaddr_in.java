package com.example.CSAPP.socket;

public class Sockaddr_in {
    short sin_family; // protocol family (always AF_INET)
    short sin_port; // port number in network byte order;
    In_addr sin_addr; // IP address in network byte order;
    char[] sin_zero=new char[8];// pad to sizeof(sockaddr)
}
