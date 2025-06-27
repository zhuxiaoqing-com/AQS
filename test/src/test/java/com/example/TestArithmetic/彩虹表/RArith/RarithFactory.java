package com.example.TestArithmetic.彩虹表.RArith;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/9/27 15:09
 * @Description:
 */
public class RarithFactory {
	private static final List<RArith> list = new ArrayList<>();

	static {
		list.add(new R1());
		list.add(new R2());
	}

}
