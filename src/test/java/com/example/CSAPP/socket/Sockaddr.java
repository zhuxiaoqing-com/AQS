package com.example.CSAPP.socket;

public class Sockaddr {
    short sa_family; // protocol family (always AF_INET)
    char[] sa_data = new char[14];// address data
}
