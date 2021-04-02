package com.youxi.db;


/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/4/2 14:46
 * @Description:
 */
public class GameSqlBuilder {

	public Object[] buildInsertParams(Object object, Class clazz) {
		byte[] bytes = SerializerUtil.encode(object, clazz);
		return new Object[]{1, bytes};
	}


	public Object[] buildUpdateParams(Object object, Class clazz) {
		byte[] bytes = SerializerUtil.encode(object, clazz);
		return new Object[]{bytes, 1};
	}

}
