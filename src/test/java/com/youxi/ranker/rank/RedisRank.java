package com.youxi.ranker.rank;

import com.youxi.ranker.rank.entity.RankObjRedis;
import redis.clients.jedis.Tuple;

import java.util.*;

public abstract class RedisRank<E> extends AbstractRank<RankObjRedis<E>> {
	String key;


	public RedisRank(RankerOrder order, String key) {
		super(0, order);
		this.key = key;
	}


	@Override
	protected long findIdByRankObj(RankObjRedis<E> rankObj) {
		return rankObj.getValue();
	}

	@Override
	protected double findScoreByRankObj(RankObjRedis<E> rankObj) {
		return rankObj.getScore();
	}

	@Override
	protected void setScoreByRankObj(RankObjRedis<E> rankObj, double newScore) {
		rankObj.setScore(newScore);
	}


	@Override
	protected void _addMultiRank(Collection<RankObjRedis<E>> rankObjs) {
		HashMap<String, Double> map = new HashMap<>();
		for (RankObjRedis<E> rankObj : rankObjs) {
			map.put(String.valueOf(rankObj.getValue()), rankObj.getScore());
		}
		RedisUtil.zadd(key, map);
	}

	/**
	 * 添加排行
	 *
	 * @param rankObj rankObj
	 */
	@Override
	protected void _addRank(RankObjRedis<E> rankObj) {
		List<RankObjRedis<E>> rankInfosByRanking = findRankInfosByRanking(-1, 0);
		RedisUtil.zadd(key, rankObj.getScore(), String.valueOf(rankObj.getValue()));
	}

	/**
	 * 删除排行
	 *
	 * @param memberId memberId
	 */
	@Override
	public void removeRank(long memberId) {
		RedisUtil.zrem(key, String.valueOf(memberId));
	}

	/**
	 * 根据排行获取对象
	 *
	 * @param start 0开始
	 * @param end   -1 在为all
	 * @return RankInfo
	 */
	@Override
	public List<RankObjRedis<E>> findRankInfosByRanking(int start, int end) {
		Set<Tuple> tuples;
		if (getRankerOrder() == RankerOrder.ASC) {
			tuples = RedisUtil.zrangeWithScores(key, start, end);
		} else {
			tuples = RedisUtil.zrevrangeWithScores(key, start, end);
		}
		return redisToList(tuples, start);
	}

	@Override
	public RankObjRedis<E> findLastRank() {
		List<RankObjRedis<E>> rankInfosByRanking = findRankInfosByRanking(-1, -1);
		if(rankInfosByRanking.isEmpty()) {
			return null;
		}
		return rankInfosByRanking.get(0);
	}

	@Override
	protected List<RankObjRedis<E>> _findRankInfosByScore(double minScore, double maxScore) {
		Set<Tuple> tuples;
		if (getRankerOrder() == RankerOrder.ASC) {
			tuples = RedisUtil.zrangeByScoreWithScores(key, minScore, maxScore);
		} else {
			tuples = RedisUtil.zrevrangeByScoreWithScores(key, minScore, maxScore);
		}
		return redisToList(tuples, 1);
	}

	/**
	 * 获取对象的排行
	 *
	 * @param member member
	 * @return 排行; -1为排行不存在
	 */
	@Override
	public int findRank(long member) {
		if (getRankerOrder() == RankerOrder.ASC) {
			return Math.toIntExact(RedisUtil.zrank(key, String.valueOf(member)));
		} else {
			return Math.toIntExact(RedisUtil.zrevrank(key, String.valueOf(member)));
		}
	}

	public RankObjRedis<E> findRankByObj(long member) {
		int rank = findRank(member);
		if (rank < 0) {
			return null;
		}
		E data = getData(member);
		/*if(data == null) {
			return null;
		}*/
		return new RankObjRedis<>(member, 0, rank, data);
	}

	/**
	 * 获取分数
	 *
	 * @param member member
	 * @return 不存在返回 null
	 */
	@Override
	public Double findScore(long member) {
		return RedisUtil.zscore(key, String.valueOf(member));
	}


	public RankObjRedis<E> findScoreByObj(long member) {
		Double zscore = RedisUtil.zscore(key, String.valueOf(member));
		if (zscore == null) {
			return null;
		}
		E data = getData(member);
		return new RankObjRedis<>(member, zscore, 0, data);
	}

	/**
	 * 清空排行榜
	 */
	@Override
	public void clear() {
		RedisUtil.del(key);
	}

	public abstract E getData(long id);

	public abstract Map<String, E> getMultiDataMap(String... idStrArray);

	private List<RankObjRedis<E>> redisToList(Set<Tuple> tuples, int start) {
		List<RankObjRedis<E>> list = new ArrayList<>(tuples.size());
		if(tuples.isEmpty()) {
			return list;
		}
		String[] multiDataArrays = tuples.stream().map(Tuple::getElement).toArray(String[]::new);
		Map<String, E> multiDataMap = getMultiDataMap(multiDataArrays);
		int rank = start + 1;
		for (Tuple tuple : tuples) {
			long value = Long.parseLong(tuple.getElement());
			RankObjRedis<E> obj = new RankObjRedis<>(value, tuple.getScore(), rank++, null);
			E data = multiDataMap.get(tuple.getElement());
			obj.setData(data);
			list.add(obj);
		}
		return list;
	}

}
