package com.example.代码优化.设计模式之美.组合和继承;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/5/14 20:18
 * @Description:
 */
public interface EggLayable {
	void lagEgg();
	default int a(){
		System.out.println(2);
		return 1;
	}
}

