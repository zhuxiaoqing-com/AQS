package com.example.CSAPP.socket;

public class AddrInfo {
    int ai_flags; // hits arguments flags
    int ai_family; // first arg to socket function
    int ai_socketType; // second args to socket function
    int ai_protocol; // third arg to socket function
    int ai_canOnName;// Canonical hostname;
    int addrlen; // size of ai_addr struct;
    Sockaddr ai_addr; // ptr to socket address structure  ptr = pointer;
    AddrInfo ai_next; // ptr to next item in linked list

    public int getAi_flags() {
        return ai_flags;
    }

    public void setAi_flags(int ai_flags) {
        this.ai_flags = ai_flags;
    }

    public int getAi_family() {
        return ai_family;
    }

    public void setAi_family(int ai_family) {
        this.ai_family = ai_family;
    }

    public int getAi_socketType() {
        return ai_socketType;
    }

    public void setAi_socketType(int ai_socketType) {
        this.ai_socketType = ai_socketType;
    }

    public int getAi_protocol() {
        return ai_protocol;
    }

    public void setAi_protocol(int ai_protocol) {
        this.ai_protocol = ai_protocol;
    }

    public int getAi_canOnName() {
        return ai_canOnName;
    }

    public void setAi_canOnName(int ai_canOnName) {
        this.ai_canOnName = ai_canOnName;
    }

    public int getAddrlen() {
        return addrlen;
    }

    public void setAddrlen(int addrlen) {
        this.addrlen = addrlen;
    }

    public Sockaddr getAi_addr() {
        return ai_addr;
    }

    public void setAi_addr(Sockaddr ai_addr) {
        this.ai_addr = ai_addr;
    }

    public AddrInfo getAi_next() {
        return ai_next;
    }

    public void setAi_next(AddrInfo ai_next) {
        this.ai_next = ai_next;
    }
}
