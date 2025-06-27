package com.example.datastruture.binarysearch;

/**
 * 查找第一个值等于给定值的元素
 */
public class BinarySearchStartValue {

    /**
     array[mid] >= value 等于 value 时候是 high 移动
     最终会变成 mid3 == vlaue  mid1 mid2 mid3 mid4
     然后 high = mid - 1;
     最终变成 high = mid1
     然后 low 也是 mid1;
     因为 如果有 mid != value
     low 就会等于 =  mid +1;
     直到 low 达到 第一个 mid
     然后开始从 high 递减 直到 High < low;
     begin = start   endPoint = end
     例如  start value1 value2 value3 end
     0     1      2      3     4

     1、mid = 0+(4-0)/2 = 2;
     array[2] = value
     符合 array[mid] >= value
     endPoint = 3

     2、mid = 0+(3-0)/2 = 1;
     array[1] = value
     符合 array[mid] >= value
     endPoint = 2

     3、mid = 0+(2-0)/2 = 1;
     array[1] = value
     符合 array[mid] >= value
     endPoint = 2

     4、mid = 0+(1-0)/2 = 0;
     array[0] = value
     不符合 array[mid] >= value
     begin = 0+1 = 1;

     5、mid = 1+(1-1)/2 = 1;
     array[1] = value
     符合 array[mid] >= value
     endPoint = 1;
     。。。。

     6、下面就是 endPoint = 0;
     不符合 	 low <= high 退出循环
     7、
     8、
     9、
     */
    /**
     * mid 只要指向了 == value;
     * high 就开始 -1;
     * 如果不等于 low 就开始 +1;
     * 最终 low 就等于 value;
     * <p>
     * mid == value high - 1;
     * mid != value low + 1;
     * over : high < low
     *
     * @param array
     * @param value
     * @return
     */
    public int bSearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            // >> 的优先级 低于 +
            int mid = low + ((high - low) >> 1);

            if (array[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low < array.length && array[low] == value) return low;
        else return -1;
    }

    public int BSearch02(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid+  1;
            } else {
                // 如果相等了查看 array[mid - 1] 是不是 ==value 不等于就相当于 mid 是最小的 value 了
                // 如果 mid == 0 了不能减了 说明是第一个已经是最小了
                if ((mid == 0) || (array[mid - 1] != value)) {
                    return mid;
                } else {
                    // 不是的话肯定在 [0, mid -1] 里面
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
