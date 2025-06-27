package com.example.common.loadHash;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/4 10:41
 * @Description: FNV1_32_HASH
 */
public class HashUtil {
	/**
	 * 计算hash值，使用 FNV1_32_HASH 算法
	 *
	 * @param str str
	 * @return hash
	 */
	public static int getHash(String str) {
		final int p = 16777619;
		int hash = (int) 2166136261L;
		for (int i = 0; i < str.length(); i++) {
			hash = (hash ^ str.charAt(i)) * p;
		}
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;

		if (hash < 0) {
			hash = Math.abs(hash);
		}
		return hash;
	}
}
