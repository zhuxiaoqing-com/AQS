package com.example.datastruture.heap;

import com.example.datastruture.a_44_shortest_path_algorithm2.Heap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 11:41
 * @Description:
 */
public class HeapTest {
	@Test
	public void test01() {
		CreateHeap createHeap = new CreateHeap();
		int[] ints = {0, 2, 5, 1, 3, 11, 18, 10,32321,324243,546,2,23213,2,3234,123,1};
		createHeap.buildHeap(ints);
		System.out.println(Arrays.toString(ints));
	}
}
