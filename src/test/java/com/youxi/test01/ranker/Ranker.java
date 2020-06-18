package com.youxi.test01.ranker;

import java.util.Map;
import java.util.Set;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/17 13:53
 * @Description:
 */
public interface Ranker<T> {
	/**
	 * 根据排名区间
	 */
	Set<T> zrange(String key, int startRank, int endRank);


	/**
	 * 根据分数区间
	 */
	Set<T> zrangeWithScores(String key, int startScore, int endScore);

	/**
	 * 获取该元素的排名
	 */
	long zrank(String key, String member);

	/**
	 * 获取该元素的分数
	 */
	double zscore(String key, String member);

	void zadd(String key, double score, String member);

	void zadd(String key, Map<String, Double> scoreMembers);

	void del(String key);
}
