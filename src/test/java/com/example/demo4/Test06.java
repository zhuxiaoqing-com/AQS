package com.example.demo4;

import org.testng.annotations.Test;

public class Test06 {


    long data[];

    /**
     * @param elems
     * @param stride
     * @return
     */
    long test(int elems, int stride) {
        int i, sx2 = stride * 2, sx3 = stride * 4, sx4 = stride * 4;
        long acc0 = 0, acc1 = 1, acc2 = 0, acc3 = 0;
        long length = elems;
        long limit = length - sx4;

        for (i = 0; i < limit; i += sx4) {
            acc0 = acc0 + data[i];
            acc1 = acc1 + data[i + stride];
            acc2 = acc2 + data[i + sx2];
            acc3 = acc3 + data[i + sx3];
        }

        for (; i < length; i += stride) {
            acc0 = acc0 + data[i];
        }
        return ((acc0 + acc1) + (acc2 + acc3));
    }



    @Test
    public void test01() {
        System.out.println(2100/12000*8);
    }

}



















