package com.youxi.match;

import com.example.demo4.TestUtil;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/1/25 10:40
 * @Description:
 */
public class Test01 {
	@Test
	public void test01() {
		LinkedHashMap<Integer, Integer> hashMap = new LinkedHashMap<>();
		ArrayList<Integer> arrayList = new ArrayList<>();


		int count = 20000;
		ArrayList<Integer> array2List = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			array2List.add(count);
		}
		Collections.shuffle(array2List);

		TestUtil.testTime(()->{
			for (int i = 0; i < count; i++) {
				arrayList.add(i);
			}
		},1,"arrayadd");

		TestUtil.testTime(()->{
			for (int i = 0; i < count; i++) {
				hashMap.put(i,i);
			}
		},1,"linkedadd");


		TestUtil.testTime(()->{
			for (Integer integer : arrayList) {

			}

		},1,"arrayfor");

		TestUtil.testTime(()->{
			for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {

			}
		},1,"linkedfor");


		/*TestUtil.testTime(()->{
			for (Integer integer : array2List) {
				arrayList.remove(integer);
			}

		},1,"arrayremove");

		TestUtil.testTime(()->{
			for (Integer integer : array2List) {
				hashMap.remove(integer);
			}
		},1,"linkedremove");*/

	}
}
