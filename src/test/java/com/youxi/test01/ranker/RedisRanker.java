package com.youxi.test01.ranker;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/17 14:01
 * @Description:
 */
public class RedisRanker implements Ranker<Object> {
	private static Jedis jedis = new Jedis("127.0.0.1", 6379);

	/**
	 * 根据排名区间
	 *
	 * @param key
	 * @param startRank
	 * @param endRank
	 */
	@Override
	public Set zrange(String key, int startRank, int endRank) {
		return jedis.zrange(key, startRank, endRank);
	}


	/**
	 * 根据分数区间
	 *
	 * @param key
	 * @param startScore
	 * @param endScore
	 */
	@Override
	public Set zrangeWithScores(String key, int startScore, int endScore) {
		return jedis.zrangeWithScores(key, startScore, endScore);
	}


	/**
	 * 获取该元素获的排名
	 *
	 * @param key
	 * @param member
	 */
	@Override
	public long zrank(String key, String member) {
		Long rank = jedis.zrank(key, member);
		return rank == null ? -1 : rank;
	}

	/**
	 * 获取该元素的分数
	 *
	 * @param key
	 * @param member
	 */
	@Override
	public double zscore(String key, String member) {
		Double zscore = jedis.zscore(key, member);
		return zscore == null ? -1 : zscore;
	}

	@Override
	public void zadd(String key, double score, String member) {
		jedis.zadd(key, score, member);
	}

	@Override
	public void zadd(String key, Map<String, Double> scoreMembers) {
		jedis.zadd(key, scoreMembers);
	}

	@Override
	public void del(String key) {
		jedis.del(key);
	}
}
