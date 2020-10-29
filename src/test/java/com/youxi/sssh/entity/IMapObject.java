package com.youxi.sssh.entity;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/10/29 17:36
 * @Description:
 */
public interface IMapObject {
	/**
	 * 对象ID
	 */
	long getId();

	/**
	 * 配置id
	 */
	int getConfigId();

	/**
	 * 对象名字
	 */
	String getName();

	/**
	 * 对象名称
	 */
	int getMapId();

	/**
	 * 所在分线
	 */
	int getLine();


}
