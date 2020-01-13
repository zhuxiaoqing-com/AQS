package com.example.malloc;

public class Malloc {
    /**
     * private global variables
     */
    static int mem_heap; // points to first byte of heap
    static int mem_brk; // points to last byte of heap plus 1
    static int mem_max_addr; // max legal heap addr plus 1

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
        int heap_listp;
        if ((heap_listp = mem_sbrk(4 * BaseUtil.WSIZE)) == -1) {
            return -1;
        }
        // Alignment padding
        BaseUtil.put(heap_listp, 0);
        // prologue header
        BaseUtil.put(heap_listp + (1 * BaseUtil.WSIZE), BaseUtil.pack(BaseUtil.DSIZE, 1));
        // prologue footer
        BaseUtil.put(heap_listp + (2 * BaseUtil.WSIZE), BaseUtil.pack(BaseUtil.DSIZE, 1));
        // epilogue header
        BaseUtil.put(heap_listp + (3 * BaseUtil.WSIZE), BaseUtil.pack(0, 1));
        heap_listp += (2 * BaseUtil.WSIZE);

        // extend the empty heap with a free block of  CHUNKSIZE bytes
        if (extend_heap(BaseUtil.CHUNKSIZE / BaseUtil.WSIZE) == -1) {
            return -1;
        }
        return 0;
    }

    static int extend_heap(int words) {
        int bp;
        int size;

        // Allocate an even number of words to maintain alignment
        size = (words % 2) == 1 ? (words + 1) * BaseUtil.WSIZE : words * BaseUtil.WSIZE;

        if ((bp = mem_sbrk(size)) == -1) {
            return -1;
        }

        // initialize free block header/footer and the epilogue header
        // free block header
        BaseUtil.put(BaseUtil.HDRP(bp), BaseUtil.pack(size, 0));
        // free block footer
        BaseUtil.put(BaseUtil.FTR(bp), BaseUtil.pack(size, 0));
        // new epilogue header
        BaseUtil.put(BaseUtil.HDRP(BaseUtil.NEXT_BLKP(bp)), BaseUtil.pack(0, 1));

        
    }

}




