package com.mdb;

/**
 * 操作校验器
 * @author Eric.Ma
 *
 */
public interface OperationChecker {

	/**
	 * 判断操作是否可以进行
	 * @param key
	 * @return true:可以进行，false：不可以进行
	 */
	public boolean canOperate(Object key);
	
}
