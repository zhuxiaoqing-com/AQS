package com.example.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.time.Instant;
import java.util.*;

public class JedisTest1 {

	Jedis jedis = new Jedis("127.0.0.1", 6379);
	//Jedis jedis = new Jedis("192.168.5.83", 6379);
	//Jedis jedis = new Jedis("10.0.0.105", 6379);

	{
		jedis.auth("123456");
	}

	@Test
	public void fun1() {
		jedis.zadd("zset1", 22, "a");
		jedis.zadd("zset1", 23, "b");
		HostAndPort hostAndPort = new HostAndPort("", 1);
		JedisCluster jedisCluster = new JedisCluster(hostAndPort);
	}

	/**
	 * 通过 区间取值
	 */
	@Test
	public void fun2() {
		Set<Tuple> zset1 = jedis.zrangeByScoreWithScores("zset1", 22, 23);
		System.out.println(zset1);
	}

	/**
	 * 通过 value 取 sorted
	 */
	@Test
	public void fun3() {
		Double zscore = jedis.zscore("zset1", "a");
		System.out.println(zscore);
	}

	/**
	 * 替换  只要将 value 重新存取就会替换新的 分数;
	 */
	@Test
	public void fun4() {
		jedis.zadd("zset1", 23, "a");
		System.out.println(jedis.zrangeByScoreWithScores("zset1", 23, 23));
	}

	@Test
	public void fun5() {
		double s = 11.11;
		System.out.println((int) s);
	}

	List list = new ArrayList<>();

	{
		for (int i = 0; i <= 10000; i++) {
			list.add(i);
		}
	}

	@Test
	public void fun6() throws InterruptedException {
		new Thread(() -> {
			try {
				Thread.yield();
			} catch (Exception e) {
				e.printStackTrace();
			}
			List list2 = new ArrayList<>();
			list2.iterator();
			for (int i = 0; i <= 10000; i++) {
				list2.add(i + "ss");
			}
			System.out.println("------------------------");
			list = list2;
		}).start();

		System.out.println(list.size());

		for (Object s : list) {
			Thread.yield();
			System.out.println(s.toString());
		}
      /*  for(int i = 0; i < list.size(); i++) {
            Thread.yield();
            System.out.println(list.get(i));
        }*/
	}

	/**
	 * 通过 value 取 sorted
	 */
	@Test
	public void fun7() {
		Long zscore = jedis.zrank("zset", "dd");
		System.out.println(zscore);
	}

	@Test
	public void fun8() {
		Long zrem = jedis.zrem("zset", "ddddddd");
		System.out.println(zrem);
		Set<Tuple> xx = jedis.zrangeWithScores("xx", 1, 2);
		Iterator<Tuple> iterator = xx.iterator();
		iterator.hasNext();
		Tuple next = iterator.next();
		next.getScore();
		next.getElement();
	}

	@Test
	public void fun9() {
		int s = 123 / 100;
		System.out.println(s);
	}

	// arena:ArenaRank:1:1
	@Test
	public void fun10() {
		jedis.zadd("a", 1, "c");
		jedis.zadd("a", 2, "b");
		jedis.zadd("a", 3, "a");
		Set<Tuple> xx = jedis.zrangeWithScores("a", 0, 3);
		Iterator<Tuple> iterator = xx.iterator();
		while (iterator.hasNext()) {
			Tuple next = iterator.next();
			System.out.println(next.getElement());
			System.out.println(next.getScore());
		}
	}

	@Test
	public void fun11() throws InterruptedException {
		JedisPool jedisPool = new JedisPool();
		Jedis jedis = jedisPool.getResource();
		Thread.sleep(4444);
		String key = "haha";
		Instant start = Instant.now();
		Pipeline pipelined = jedis.pipelined();
		for (int i = 0; i < 52220; i++) {
			Response<Long> haha = pipelined.zadd("haha", i, i + key);
			if (i == 995) {
				throw new RuntimeException("11");
			}
		}
		Instant end = Instant.now();
       /* pipelined.sync();
        //pipelined.syncAndReturnAll();
        jedis.close();*/

		System.out.println(end.toEpochMilli() - start.toEpochMilli());
	}

	@Test
	public void fun12() {
		JedisPool jedisPool = new JedisPool("192.168.5.83", 6379);
		Jedis jedis = jedisPool.getResource();
		String index = "0";
		ScanParams scanParams = new ScanParams().count(1000);
		List<Map.Entry<String, String>> result = null;
		while (true) {
			ScanResult<Map.Entry<String, String>> hscan = jedis.hscan("arena_robot:4", index, scanParams);
			result = hscan.getResult();
			index = hscan.getStringCursor();
			System.out.println(result.size());
			if ("0".equals(index)) {
				return;
			}
		}
	}

	@Test
	public void fun13() {
		jedis.del("a");
		jedis.zadd("a", 1.001, "11");
		jedis.zadd("a", 23.232321, "22");
		jedis.zadd("a", 23.232322, "23");
		jedis.zadd("a", 23.232324, "24");

		Set<Tuple> xx = jedis.zrangeWithScores("a", 0, 3);
		Iterator<Tuple> iterator = xx.iterator();
		while (iterator.hasNext()) {
			Tuple next = iterator.next();
			System.out.println(next.getElement());
			System.out.println(next.getScore());
		}
		// System.out.println(jedis.zcount("a", 3,-1));
		//jedis.zremrangeByRank("a", 3, 3);

	}


	@Test
	public void fun14() {
		Double zscore = jedis.zscore("arena:1:1:1", "3");
		GeoRadiusParam param = GeoRadiusParam.geoRadiusParam().sortAscending().count(1).withCoord().withDist();
		GeoCoordinate geoCoordinate = new GeoCoordinate(22, 22);
		// jedis2.geoadd("location",geoCoordinate.getLongitude(),geoCoordinate.getLatitude(),"b");
		//List<GeoRadiusResponse> geoRadiusResponses = jedis.georadiusByMember("location", "32ds", 1, GeoUnit.KM, param);
		jedis.close();
	}

	@Test
	public void fun15() {
		HashMap<String, String> map = new HashMap<>();
		map.put("101", "player1");
		map.put("102", "player2");
		map.put("103", "player3");
		String d = jedis.hmset("1_xkjdsjkadjklsajl$bilap", map);
		Map<String, String> stringStringMap = jedis.hgetAll("1_xkjdsjkadjklsajl$bilap");
		System.out.println(stringStringMap);
		Long pp2 = jedis.hset("1_xkjdsjkadjklsajl$bilap", "102", "pp2");
		System.out.println(pp2);
		List<String> hmget = jedis.hmget("1_xkjdsjkadjklsajl$bilap", "102");
		System.out.println(hmget);
		jedis.close();
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			Jedis jedis = new Jedis("127.0.0.1", 6379);
			jedis.auth("123456");
			jedis.set("1", "1");
		}
		new Thread(() -> {
			while (true) {
			}
		}).start();


		try {
			Thread.sleep(4444);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * bitmap 测试
	 *
	 * offset 参数必须大于或等于 0 ，小于 2^32 (bit 映射被限制在 512 MB 之内)。
	 */
	@Test
	public void testBitMapl5() {
		/*
		@param value 只能是 0 或者 1 不然会报错;
		@return 如果返回这个偏移位上的旧的值
		 */
		//jedis.del("key1");
		Boolean key1 = jedis.setbit("key1", 11, "1");
		System.out.println(key1);
		jedis.setbit("key1", 20, "1");
		jedis.setbit("key1", 0, "1");
		jedis.setbit("key1", 7, true);
		jedis.setbit("key1", 8, true);
		Boolean getReturn = jedis.getbit("key1", 1);
		System.out.println(getReturn);

		Long bitCountReturn1 = jedis.bitcount("key1");
		System.out.println(bitCountReturn1);
		Long bitCountReturn2 = jedis.bitcount("key1", 0, -1);
		System.out.println(bitCountReturn2);
		/*
		redis的setbit设置或清除的是bit位置，而bitcount计算的是byte位置，
		@param start:指的是起始字节(一个字节有8个bit)
		@param end:指的是结束字节(一个字节有8个bit)
		 */
		Long bitCountReturn3 = jedis.bitcount("key1", 0, 0);
		System.out.println(bitCountReturn3);
	}

	/**
	 * bitmap 测试
	 *
	 * offset 参数必须大于或等于 0 ，小于 2^32 (bit 映射被限制在 512 MB 之内)。
	 */
	@Test
	public void testBitMapl6() {
		String key1 = "key1";
		String key2 = "key2";
		jedis.del(key1);
		jedis.del(key2);

		jedis.setbit(key1, 12, "1");
		jedis.setbit(key1, 20, "1");
		jedis.setbit(key1, 11, "1");
		jedis.setbit(key1, 1111, "1");

		jedis.setbit(key2, 11, "1");
		jedis.setbit(key2, 21, "1");
		jedis.setbit(key2, 0, "1");
		jedis.setbit(key2, 7, true);
		jedis.setbit(key2, 8, true);

		/*
		对源key 进行 bitOP 操作; 然后将结果保存在 destKey 中; 并且将输入的字符串(字节)的大小返回;
		@param BitOp  操作
		@param destKey  目标key
		@param String... srcKeys 源key
		@return 存储在目标密钥中的字符串的大小，即等于最长输入字符串的大小。
		 */
		Long key1_key2 = jedis.bitop(BitOP.AND, "key1_key2", key1, key2);
		System.out.println(key1_key2);

		Long key1_key2Count = jedis.bitcount("key1_key2");
		System.out.println(key1_key2Count);

		/*
		bitpos 获取该key 的第一个值为 value 的偏移量;
		start 和 end 也是以字节为单位的
		 */
		Long bitpos = jedis.bitpos(key1, true);
		System.out.println(bitpos);

		Long bitpos2 = jedis.bitpos(key1, true, new BitPosParams(1, 2));
		System.out.println(bitpos2);


	}
}
