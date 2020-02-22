package com.example.CSAPP.malloc;

import org.junit.Test;

import static com.example.CSAPP.malloc.BaseUtil.*;

public class Malloc {
    /**
     * private global variables
     */
    static int mem_heap; // points to first byte of heap
    static int mem_brk; // points to last byte of heap plus 1
    static int mem_max_addr; // max legal heap addr plus 1
    static int heap_listp;

    static int MAX_HEAP;

    /**
     * mem_init -Initialize the memory system model
     */
    static void mem_init() {
        // 这里应该是申请内存吧
        mem_heap = MAX_HEAP; //(char *)Malloc(MAX_HEAP);
        mem_brk = mem_heap;
        mem_max_addr = mem_heap + MAX_HEAP;
    }


    /**
     * mem_sbrk - Simple model of the sbrk function。
     * extends the heap by incr bytes and returns the start address of the new area.
     * in this model, the heap cannot be shrunk.
     */
    static int mem_sbrk(int incr) {
        int old_brk = mem_brk;
        if (incr < 0 || mem_brk + incr > mem_max_addr) {
            return -1;
        }
        mem_brk += incr;
        return old_brk;
    }


    /**
     * 初始化堆
     * <p>
     * 第一个字是一个双字边界 用来对齐的不使用的填充字。
     * 填充后面紧跟着一个特殊的序言块(prologue block),这是一个8字节的已分配的块，只由一个头部和一个脚部组成。
     * 序言块是在初始化时创建的，并且永不释放。
     * 在序言块后紧跟的是零个或者多个由 malloc 或者 free 调用创建的普通块。
     * 堆总是以一个特殊的结尾块(epilogue block)来结束，这个块是一个大小为零的已分配块，是由一个头部组成。
     * 序言块和结尾块是一种消除合并时边界条件的技巧。
     * 分配器使用一个单独的私有(static)全局变量(heap_listp),它总是指向序言块。
     */
    static int mm_init() {
        // create the initial empty heap
        if ((heap_listp = mem_sbrk(4 * WSIZE)) == -1) {
            return -1;
        }
        // Alignment padding
        put(heap_listp, 0);
        // prologue header
        put(heap_listp + (1 * WSIZE), pack(DSIZE, 1));
        // prologue footer
        put(heap_listp + (2 * WSIZE), pack(DSIZE, 1));
        // epilogue header
        put(heap_listp + (3 * WSIZE), pack(0, 1));
        heap_listp += (2 * WSIZE);

        // extend the empty heap with a free block of  CHUNKSIZE bytes
        if (extend_heap(CHUNKSIZE / WSIZE) == -1) {
            return -1;
        }
        return 0;
    }

    static int extend_heap(int words) {
        int bp;
        int size;

        // Allocate an even number of words to maintain alignment
        size = (words % 2) == 1 ? (words + 1) * WSIZE : words * WSIZE;

        if ((bp = mem_sbrk(size)) == -1) {
            return -1;
        }

        // initialize free block header/footer and the epilogue header
        // free block header
        put(HDRP(bp), pack(size, 0));
        // free block footer
        put(FTRP(bp), pack(size, 0));
        // new epilogue header
        put(HDRP(NEXT_BLKP(bp)), pack(0, 1));

        // coalesce if the previous block was free
        return coalesce(bp);
    }

    static void mm_free(int bp) {
        int size = getSize(HDRP(bp));

        put(HDRP(bp), pack(size, 0));
        put(FTRP(bp), pack(size, 0));
        coalesce(bp);
    }


    static int coalesce(int bp) {
        int prevAlloc = getAlloc(FTRP(PREV_BLKP(bp)));
        int nextAlloc = getAlloc(HDRP(NEXT_BLKP(bp)));
        int size = getSize(HDRP(bp));

        if (prevAlloc == 1 && nextAlloc == 1) { //case 1
            return bp;
        } else if (prevAlloc == 1 && nextAlloc == 0) { //case 2
            size += getSize(HDRP((NEXT_BLKP(bp))));
            put(HDRP(bp), pack(size, 0));
            put(FTRP(NEXT_BLKP(bp)), pack(size, 0));
        } else if (prevAlloc == 0 && nextAlloc == 1) { //case 3
            size += getSize(HDRP((PREV_BLKP(bp))));
            put(HDRP(PREV_BLKP(bp)), pack(size, 0));
            put(FTRP(bp), pack(size, 0));
            bp = PREV_BLKP(bp);
        } else {
            size += getSize(HDRP((PREV_BLKP(bp)))) + getSize(FTRP((NEXT_BLKP(bp))));
            put(HDRP(PREV_BLKP(bp)), pack(size, 0));
            put(FTRP(NEXT_BLKP(bp)), pack(size, 0));
            bp = PREV_BLKP(bp);
        }

        return bp;
    }

    static int mm_malloc(int size) {
        int asize; // adjusted block size
        int extendsize;// amount to extend heap if no fit
        int bp;

        // ignore spurious requests
        if (size == 0) {
            return -1;
        }

        // adjust block size to include overhead and alignment reqs
        if (size <= DSIZE) { // 最小块大小是 16 字节  8字节用来满足对齐要求，而另外8个用来放头部和脚部
            asize = 2 * DSIZE;
        } else { // 对于超过 8 字节的请求，一般的规则是加上开销字节，然后向上舍入到最接近的8个整数倍。
            // + DSIZE 是头和脚部
            asize = DSIZE * ((size + DSIZE + (DSIZE - 1)) / DSIZE);
        }

        // search the free list for a fit
        if ((bp = find_fit(asize)) != -1) {
            place(bp, asize);
            return bp;
        }

        // no fit found. get more memory and place the block
        extendsize = max(asize, CHUNKSIZE);

        if ((bp = extend_heap(extendsize / WSIZE)) == -1) {
            return -1;
        }

        place(bp, asize);

        return bp;
    }

    /**
     * 找可以存放 asize 的空白的块
     */
    public static int find_fit(int asize) {
        // first-fit search
        int bp = -1;
        for (bp = heap_listp; getSize(HDRP(bp)) > 0; bp = NEXT_BLKP(bp)) {
            if (getAlloc(HDRP(bp)) == 0 && asize <= getSize(HDRP(bp))) {
                return bp;
            }
        }
        return -1; // no fit
    }

    /**
     * 注意对于这个分配器，最小块大小是16字节。
     * 如果分割后剩下的块大于或者等于最小块大小，那么我们就分割这个块。
     * 这里唯一有技巧的部分是要意识到在移动到下一块之前，你必须放置新的已分配块。
     */
    public static void place(int bp, int asize) {
        int totalSize = getSize(HDRP(bp));
        int residueSize = totalSize - asize;
        if (residueSize >= 2 * DSIZE) {
            put(HDRP(bp), pack(asize, 1));
            put(FTRP(bp), pack(asize, 1));
            bp = NEXT_BLKP(bp);
            put(HDRP(bp), pack(totalSize - asize, 0));
            put(FTRP(bp), pack(totalSize - asize, 0));
        } else {
            put(HDRP(bp), pack(totalSize, 1));
            put(FTRP(bp), pack(totalSize, 1));
        }
    }

    @Test
    public void test() {
        System.out.println(DSIZE * ((8 + DSIZE + (DSIZE - 1)) / DSIZE));
        System.out.println(DSIZE * ((9 + DSIZE + (DSIZE - 1)) / DSIZE));
        System.out.println(DSIZE * ((10 + DSIZE + (DSIZE - 1)) / DSIZE));
        System.out.println(DSIZE * ((16 + DSIZE + (DSIZE - 1)) / DSIZE));
        System.out.println(DSIZE * ((17 + DSIZE + (DSIZE - 1)) / DSIZE));
    }

}












