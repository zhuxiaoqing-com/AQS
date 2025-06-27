package com.youxi.ranker.rank;


import java.util.Collection;
import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/5/14 20:26
 * @Description:
 */
public abstract class AbstractRank<E> {
	/**
	 * 序列
	 */
	public enum RankerOrder {
		ASC, //正序 从小到大
		DESC, //倒序 从大到小
	}

	private long key; // 排行榜类型

	private RankerOrder rankerOrder;


	public AbstractRank(long key, RankerOrder order) {
		this.key = key;
		this.rankerOrder = order;
	}

	/**
	 * 添加排行
	 *
	 * @param rankObj rankObj
	 */
	public final void addRank(E rankObj) {
		if (!checkAddRank(rankObj)) {
			//最新的不到上榜条件,就把之前的上榜数据删除
			removeRank(findIdByRankObj(rankObj));
			return;
		}
		double newScore = encodeScore((long) findScoreByRankObj(rankObj));
		setScoreByRankObj(rankObj, newScore);
		_addRank(rankObj);
	}


	protected abstract long findIdByRankObj(E rankObj);
	protected abstract double findScoreByRankObj(E rankObj);
	protected abstract void setScoreByRankObj(E rankObj, double newScore);

	public void addMultiRank(Collection<E> rankObjs) {
		if(rankObjs.isEmpty()) {
			return;
		}
		for (E rankObj : rankObjs) {
			double newScore = encodeScore((long) findScoreByRankObj(rankObj));
			setScoreByRankObj(rankObj, newScore);
		}
		_addMultiRank(rankObjs);
	}

	protected abstract void _addMultiRank(Collection<E> rankObjs);
	/**
	 * 添加排行
	 *
	 * @param rankObj rankObj
	 */
	protected abstract void _addRank(E rankObj);


	/**
	 * 删除排行
	 *
	 * @param memberId memberId
	 */
	public abstract void removeRank(long memberId);


	/**
	 * 根据排行获取对象
	 *
	 * @param start 0开始
	 * @param end   -1 在为all
	 * @return RankInfo
	 */
	public abstract List<E> findRankInfosByRanking(int start, int end);

	public abstract E findLastRank();

	/**
	 * 根据分数获取对象
	 *
	 * @param minScore 最小分数
	 * @param maxScore 最大分数
	 * @return RankInfo
	 */
	public List<E> findRankInfosByScore(double minScore, double maxScore) {
		// 因为分数拼接过时间戳,所以最大分数直接+1;
		return _findRankInfosByScore(minScore, maxScore + 1);
	}

	protected abstract List<E> _findRankInfosByScore(double minScore, double maxScore);

	/**
	 * 获取对象的排行
	 *
	 * @param member member
	 * @return 排行; -1为排行不存在
	 */
	public abstract int findRank(long member);

	public abstract E findRankByObj(long member);

	/**
	 * 获取分数
	 *
	 * @param member member
	 * @return 不存在返回 null
	 */
	public abstract Double findScore(long member);

	/**
	 * 获取分数
	 *
	 * @param member member
	 * @return 不存在返回 -1
	 */
	public abstract E findScoreByObj(long member);

	/**
	 * 清空排行榜
	 */
	public abstract void clear();

	/**
	 * 检测是否能添加进排行榜
	 */
	protected abstract boolean checkAddRank(E rankObj);

	public abstract int getRankNumLimit();

	public long getKey() {
		return key;
	}



	public RankerOrder getRankerOrder() {
		return rankerOrder;
	}
	public double encodeScore(long value) {
		return getRankerOrder() == RankerOrder.ASC ? RankUtil.encodeScoreASC(value) : RankUtil.encodeScoreDESC(value);
	}
}
