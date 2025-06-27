package com.example.datastruture.heap.margesmailfile.pojo;

public class ByteHeadInfo {
    /**
     * 当前存储的 Byte 信息
     */
    private byte value;

    /**
     * 文件所有的索引信息
     */
    private int index;

    public ByteHeadInfo(byte value, int index) {
        this.value = value;
        this.index = index;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
