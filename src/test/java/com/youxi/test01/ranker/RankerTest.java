package com.youxi.test01.ranker;


import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/17 19:45
 * @Description:
 */
public class RankerTest {
	//Ranker ranker = new MongodbRanker();
	Ranker ranker = new RedisRanker();
	String key = "rank";
	int count = 1_000_000;

	@Test
	public void insertTestData() {
		ranker.del(key);
		for (int i = 0; i < 1_000_000; i++) {
			ranker.zadd(key, i, i + "member");
		}
	}

	private void timeRangeWithScore(int start, int end) {
		long startTime = System.currentTimeMillis();
		ranker.zrangeWithScores(key, start, end);
		long endTime = System.currentTimeMillis();

		String print = "分数区间 {%s : %s }  消耗时间为  %s";
		String format = String.format(print, start, end, endTime - startTime);
		System.out.println(format);
	}

	private void timeRangeWithRank(int start, int end) {
		long startTime = System.currentTimeMillis();
		ranker.zrangeWithScores(key, start, end);
		long endTime = System.currentTimeMillis();

		String print = "排名区间 {%s : %s }  消耗时间为  %s";
		String format = String.format(print, start, end, endTime - startTime);
		System.out.println(format);
	}

	private void timeZrank(int whileCount) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i <= whileCount; i++) {
			ranker.zrank(key, i + "member");
		}

		long endTime = System.currentTimeMillis();

		String print = "通过member取rank  循环次数：%s  消耗时间为  %s";
		String format = String.format(print, whileCount, endTime - startTime);
		System.out.println(format);
	}

	private void timeZScore(int whileCount) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i <= whileCount; i++) {
			ranker.zscore(key, i + "member");
		}

		long endTime = System.currentTimeMillis();

		String print = "通过member取score  循环次数：%s  消耗时间为  %s";
		String format = String.format(print, whileCount, endTime - startTime);
		System.out.println(format);
	}

	@Test
	public void testRangeWithScore1() {
		for (int i = 0; i <= 1; i++) {
			ranker.zrank(key, "");
		}
		int residue = 0;
		residue = 10;
		timeRangeWithScore(0, residue);
		timeRangeWithScore(count - residue, count);
		residue = 100;
		timeRangeWithScore(0, residue);
		timeRangeWithScore(count - residue, count);
		residue = 1000;
		timeRangeWithScore(0, residue);
		timeRangeWithScore(count - residue, count);

		residue = 10000;
		timeRangeWithScore(0, residue);
		timeRangeWithScore(count - residue, count);

		residue = 100_000;
		timeRangeWithScore(0, residue);
		timeRangeWithScore(count - residue, count);

		residue = 1_000_000;
		timeRangeWithScore(0, residue);
		timeRangeWithScore(count - residue, count);
	}

	@Test
	public void testRangeWithRank() {
		for (int i = 0; i <= 1; i++) {
			ranker.zrank(key, "");
		}
		int residue = 0;
		residue = 10;
		timeRangeWithRank(0, residue);
		timeRangeWithRank(count - residue, count);
		residue = 100;
		timeRangeWithRank(0, residue);
		timeRangeWithRank(count - residue, count);
		residue = 1000;
		timeRangeWithRank(0, residue);
		timeRangeWithRank(count - residue, count);

		residue = 10000;
		timeRangeWithRank(0, residue);
		timeRangeWithRank(count - residue, count);

		residue = 100_000;
		timeRangeWithRank(0, residue);
		timeRangeWithRank(count - residue, count);

		residue = 1_000_000;
		timeRangeWithRank(0, residue);
		timeRangeWithRank(count - residue, count);
	}


	@Test
	public void testZrank() {
		for (int i = 0; i <= 1; i++) {
			ranker.zrank(key, "");
		}
		int residue = 0;
		residue = 10;
		timeZScore(residue);

		residue = 100;
		timeZScore(residue);

		residue = 1000;
		timeZScore(residue);

		residue = 10000;
		timeZScore(residue);

		residue = 100_000;
		timeZScore(residue);

		residue = 1_000_000;
		timeZScore(residue);
	}


}
