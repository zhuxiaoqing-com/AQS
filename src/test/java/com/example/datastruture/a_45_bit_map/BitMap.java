package com.example.datastruture.a_45_bit_map;

public class BitMap {
    private char[] bytes;
    private int nBits;

    public BitMap(int nBits) {
        this.nBits = nBits;
        this.bytes = new char[nBits / 8 + 1];
    }

    public void set(int k) {
        if (k > nBits) return;
        int byteIndex = k >> 3;
        int bitIndex = k & 7;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean get(int k) {
        if (k > nBits) return false;
        int byteIndex = k >> 3;
        int bitIndex = k & 7;
        return (bytes[byteIndex] & (1 >> bitIndex)) != 0;
    }
}
