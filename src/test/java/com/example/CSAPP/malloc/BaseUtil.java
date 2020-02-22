package com.example.CSAPP.malloc;

public class BaseUtil {
    public final static int WSIZE = 4; //word and header/footer size(bytes)  字的大小
    public final static int DSIZE = 8; //Double word size(bytes)  双字的大小
    public final static int CHUNKSIZE = 1 << 12; //extend heap by this amount(bytes) 16字节 初始空闲块的大小 和 扩展堆时的默认大小

    public static int max(int x, int y) {
        return x > y ? x : y;
    }

    /**
     * pack a size and allocated bit into a word
     *
     * @return
     */
    public static int pack(int size, int alloc) {
        return size | alloc;
    }

    /**
     * read and write a word at address p
     */
    public static int get(int p) {
        return p;
    }

    public static void put(int p, int val) {
        return;
    }

    /**
     * read the size and allocated fields from address p
     */
    public static int getSize(int p) {
        return get(p) & ~0x7;
    }

    public static int getAlloc(int p) {
        return get(p) & 0x1;
    }

    /**
     * Given block ptr bp, compute address of its header and footer
     * <p>
     * block pointer 用 pb 表示，块指针指向第一个有效载荷字节。
     */
    public static int HDRP(int blockPointer) {
        return blockPointer - WSIZE;
    }

    public static int FTRP(int bp) {
        // 块开始指针 + 总size(包含头和尾大小) - 头和尾大小(加起来就是 DSIZE)
        // 就是尾部的开始指针
        return bp + getSize(HDRP(bp)) - DSIZE;
    }

    /**
     * given block ptr bp, compute address of next and previous blocks
     * block_pointer
     */
    public static int NEXT_BLKP(int bp) {
        // 当前块指针 + 这个块大小 = 下一个块的块指针
        return bp + getSize(bp - WSIZE);
    }

    public static int PREV_BLKP(int bp) {
        /*
         获取上一个块的尾巴指针
         然后获取大小
         bp - size 就是上一个块的块指针
         bp - WSIZE - size + WSIZE 就是上一个块的块指针
         */
        return bp - getSize(bp - DSIZE);
    }


}
