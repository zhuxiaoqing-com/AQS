package com.mdb;

/**
 * 上层服务器当前的玩家状态
 */
public interface PlayerFlushStateChecker {

	/**
	 * 判断上一层的服务器玩家状态是否是支持flush的，支持的话
	 *
	 * @param key
	 * @return true:可以进行，false：不可以进行
	 */
	public boolean canOperate(Object key, String tableName);
	
}
