package com.youxi.ranker.rank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.*;
import java.util.stream.Collectors;

public class RedisUtil {
	static JedisPool jedisPool;
	static  {

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(20);
		config.setMinIdle(20);
		config.setMaxWaitMillis(3000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		config.setTestWhileIdle(true);
		// 连接池耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时，会抛出超时异常，默认为true
		config.setBlockWhenExhausted(true);

		// 这里连接超时时间默认走2s
		jedisPool = new JedisPool(config, "127.0.0.1", 6379, 3000, "123456");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	private static final String STR_SUCCESS = "OK";
	private static final Long LONG_SUCCESS = 1L;
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_MILL_EXPIRE_TIME = "PX";
	private static final String SET_SECOND_EXPIRE_TIME = "EX";

	private static final int SCAN_BATCH_SIZE = 100;

	public static boolean ping() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String res = jedis.ping();
			return "PONG".equalsIgnoreCase(res);
		} catch (Exception e) {
			logger.error("set expire error", e);
		} finally {
			close(jedis);
		}

		return false;
	}

	public static boolean ping(Jedis jedis) {
		try {
			String res = jedis.ping();
			return "PONG".equalsIgnoreCase(res);
		} catch (Exception e) {
			logger.error("set expire error", e);
		} finally {
			close(jedis);
		}

		return false;
	}

	// 重新设置有效期
	// 参数只有key和有效期，因为只需要根据key设置有效期即可
	public static Long expire(String key, long mills) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = jedisPool.getResource();
			// 设置有效期
			result = jedis.pexpire(key, mills);
		} catch (Exception e) {
			logger.error("set expire error", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	public static boolean set(String key, String value) {
		return set(key, value, 0);
	}

	public static boolean set(String key, String value, long mills) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = jedisPool.getResource();
			// 设置key-value
			if (mills > 0) {
				result = jedis.psetex(key, mills, value);
			} else {
				result = jedis.set(key, value);
			}
		} catch (Exception e) {
			logger.error("set value error", e);
		} finally {
			close(jedis);
		}
		return "OK".equals(result);
	}

	public static String get(String key) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = jedisPool.getResource();
			// 根据key获取value值
			result = jedis.get(key);
		} catch (Exception e) {
			logger.error("get value error", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	public static Long del(String key) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = jedisPool.getResource();
			// 根据key删除key-value
			result = jedis.del(key);
		} catch (Exception e) {
			logger.error("del error", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	public static void close(Jedis jedis) {
		if (jedis != null)
			jedis.close();
	}

	public static Long setnx(String key, String value) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = jedisPool.getResource();
			// 根据key删除key-value
			result = jedis.setnx(key, value);
		} catch (Exception e) {
			logger.error("set value not exists error", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	public static boolean setnxex(String key, String value, long second) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.set(key, value, SET_IF_NOT_EXIST, SET_SECOND_EXPIRE_TIME, second);
		} catch (Exception e) {
			logger.error("set value not exists error", e);
		} finally {
			close(jedis);
		}
		return result != null && result.equals(STR_SUCCESS);
	}

	/**
	 * @param value 加减值 正数加 负数减
	 */
	public static void hincrBy(String key, String field, long value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 根据key获取value值
			jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			logger.error("hset error", e);
		} finally {
			close(jedis);
		}
	}


	public static void hmset(String key, Map<String, String> map) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hmset(key, map);
		} catch (Exception e) {
			logger.error("hmset error", e);
		} finally {
			close(jedis);
		}
	}

	public static void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hset(key, field, value);
		} catch (Exception e) {
			logger.error("hset error", e);
		} finally {
			close(jedis);
		}
	}

	public static Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		Map<String, String> result = Collections.emptyMap();
		try {
			jedis = jedisPool.getResource();
			result = jedis.hgetAll(key);
		} catch (Exception e) {
			logger.error("hgetAll error", e);
		} finally {
			close(jedis);
		}
		return result;
	}
	public static List<String> hmget(String key, String... field) {
		Jedis jedis = null;
		List<String> result = Collections.emptyList();
		try {
			jedis = jedisPool.getResource();
			result = jedis.hmget(key, field);
		} catch (Exception e) {
			logger.error("hmget error", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	public static Map<String,String> hscanAll(String key){
		Jedis jedis = null;
		Map<String, String> result = new HashMap<>();
		try {
			jedis = jedisPool.getResource();
			String cursor = "0";
			ScanParams scanParams = new ScanParams().count(SCAN_BATCH_SIZE);
			while (true){
				ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(key, cursor,scanParams);
				for (Map.Entry<String, String> entry : scanResult.getResult()) {
					result.put(entry.getKey(), entry.getValue());
				}
				cursor = scanResult.getStringCursor();
				if (cursor.equals("0"))
					break;
			}

		} catch (Exception e) {
			logger.error("hscanAll error", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	public static String hget(String key, String fields) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hget(key, fields);
		} catch (Exception e) {
			logger.error("hget error", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	public static void hdel(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hdel(key, fields);
		} catch (Exception e) {
			logger.error("hdel error", e);
		} finally {
			close(jedis);
		}
	}

	public static boolean hsetnx(String key, String fields, String value) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hsetnx(key, fields, value);
		} catch (Exception e) {
			logger.error("hsetnx error", e);
		} finally {
			close(jedis);
		}
		return LONG_SUCCESS.equals(result);
	}



	public static void hSetJsonString(String key, String fidlds, Object object) {
		hset(key, fidlds, FastJsonUtils.toJSONString(object));
	}

	public static <T> T hGetJsonString(String key, String fidlds, Class<T> clazz) {
		String hget = hget(key, fidlds);
		return FastJsonUtils.parseObject(hget, clazz);
	}

	public static <T> List<T> hGetAllJsonStringByScan(String key, Class<T> clazz) {
		Map<String, String> map = hgetAll(key);
		return map.values().stream().map(a->FastJsonUtils.parseObject(a, clazz)).collect(Collectors.toList());
	}

	public static <T> List<T> hGetAllJsonString(String key, Class<T> clazz){
		Map<String, String> map = hscanAll(key);
		return map.values().stream().map(a->FastJsonUtils.parseObject(a, clazz)).collect(Collectors.toList());
	}

	public static boolean zadd(String key, Map<String, Double> map) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.zadd(key, map);
		} catch (Exception e) {
			logger.error("zadd all error", e);
		} finally {
			close(jedis);
		}
		return LONG_SUCCESS.equals(result);
	}

	public static boolean zadd(String key, double score, String value) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.zadd(key, score, value);
		} catch (Exception e) {
			logger.error("zadd error", e);
		} finally {
			close(jedis);
		}
		return LONG_SUCCESS.equals(result);
	}

	public static boolean zrem(String key, String value) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.zrem(key, value);
		} catch (Exception e) {
			logger.error("zrem error", e);
		} finally {
			close(jedis);
		}
		return LONG_SUCCESS.equals(result);
	}

	/**
	 * 根据排行区间获取数据 正序
	 * @param start [0,max]
	 * @param end [0,max]
	 */
	public static Set<Tuple> zrangeWithScores(String key, int start, int end) {
		Jedis jedis = null;
		Set<Tuple> tuples = Collections.emptySet();
		try {
			jedis = jedisPool.getResource();
			tuples = jedis.zrangeWithScores(key, start, end);
		} catch (Exception e) {
			logger.error("zrangeWithScores error", e);
		} finally {
			close(jedis);
		}
		return tuples;
	}

	/**
	 * 根据排行区间获取数据 倒序
	 * @param start [0,max]
	 * @param end [0,max]
	 */
	public static Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
		Jedis jedis = null;
		Set<Tuple> tuples = Collections.emptySet();
		try {
			jedis = jedisPool.getResource();
			tuples = jedis.zrevrangeWithScores(key, start, end);
		} catch (Exception e) {
			logger.error("zrevrangeWithScores error", e);
		} finally {
			close(jedis);
		}
		return tuples;
	}

	/**
	 * 根据分数区间获取数据 正序
	 */
	public static Set<Tuple> zrangeByScoreWithScores(String key, double minScore, double maxScore) {
		Jedis jedis = null;
		Set<Tuple> tuples = Collections.emptySet();
		try {
			jedis = jedisPool.getResource();
			tuples = jedis.zrangeByScoreWithScores(key, minScore, maxScore);
		} catch (Exception e) {
			logger.error("zrevrangeWithScores error", e);
		} finally {
			close(jedis);
		}
		return tuples;
	}
	/**
	 * 根据分数区间获取数据 倒序
	 */
	public static Set<Tuple> zrevrangeByScoreWithScores(String key, double minScore, double maxScore) {
		Jedis jedis = null;
		Set<Tuple> tuples = Collections.emptySet();
		try {
			jedis = jedisPool.getResource();
			tuples = jedis.zrevrangeByScoreWithScores(key, minScore, maxScore);
		} catch (Exception e) {
			logger.error("zrevrangeByScoreWithScores error", e);
		} finally {
			close(jedis);
		}
		return tuples;
	}

	public static Set<Tuple> zrangeByScore(String key, double minScore, double maxScore,int offset,int count) {
		if (count > SCAN_BATCH_SIZE) {
			logger.error("zrangeByScore 单次查询获取的数量太大 num: {} key:{}",count,key);
			return Collections.emptySet();
		}
		Jedis jedis = null;
		Set<Tuple> tuples = Collections.emptySet();
		try {
			jedis = jedisPool.getResource();
			tuples = jedis.zrangeByScoreWithScores(key, minScore, maxScore, offset, count);
		} catch (Exception e) {
			logger.error("zrangeByScoreWithScores error", e);
		} finally {
			close(jedis);
		}
		return tuples;
	}

	public static Set<Tuple> zrevrangeByScore(String key, double minScore, double maxScore,int offset,int count) {
		if (count > SCAN_BATCH_SIZE) {
			logger.error("zrevrangeByScore 单次查询获取的数量太大 num: {} key:{}",count,key);
			return Collections.emptySet();
		}
		Jedis jedis = null;
		Set<Tuple> tuples = Collections.emptySet();
		try {
			jedis = jedisPool.getResource();
			tuples = jedis.zrevrangeByScoreWithScores(key, minScore, maxScore, offset, count);
		} catch (Exception e) {
			logger.error("zrevrangeByScore error", e);
		} finally {
			close(jedis);
		}
		return tuples;
	}

	public static Set<Tuple> zrangeByScore(String key, double minScore, double maxScore) {
		Jedis jedis = null;
		Set<Tuple> tuples = Collections.emptySet();
		try {
			jedis = jedisPool.getResource();
			tuples = jedis.zrangeByScoreWithScores(key, minScore, maxScore);
		} catch (Exception e) {
			logger.error("zrangeByScoreWithScores error", e);
		} finally {
			close(jedis);
		}
		return tuples;
	}

	public static Long zcard(String key) {
		Jedis jedis = null;
		long num = 0;
		try {
			jedis = jedisPool.getResource();
			num = jedis.zcard(key);
		} catch (Exception e) {
			logger.error("zcard error", e);
		} finally {
			close(jedis);
		}
		return num;
	}

	public static Long zcount(String key, double minScore, double maxScore) {
		Jedis jedis = null;
		long num = 0;
		try {
			jedis = jedisPool.getResource();
			num = jedis.zcount(key,minScore,maxScore);
		} catch (Exception e) {
			logger.error("zcount error", e);
		} finally {
			close(jedis);
		}
		return num;
	}


	public static Long zrank(String key, String value) {
		Jedis jedis = null;
		Long result = -1L;
		try {
			jedis = jedisPool.getResource();
			result = jedis.zrank(key, value);
		} catch (Exception e) {
			logger.error("zrank error", e);
		} finally {
			close(jedis);
		}
		return result == null ? -1 : result;
	}

	public static Long zrevrank(String key, String value) {
		Jedis jedis = null;
		Long result = -1L;
		try {
			jedis = jedisPool.getResource();
			result = jedis.zrevrank(key, value);
		} catch (Exception e) {
			logger.error("zrank error", e);
		} finally {
			close(jedis);
		}
		return result == null ? -1 : result;
	}

	public static Double zscore(String key, String value) {
		Jedis jedis = null;
		Double result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.zscore(key, value);
		} catch (Exception e) {
			logger.error("zrank error", e);
		} finally {
			close(jedis);
		}
		return result;
	}


	public static boolean setJsonString(String key, Object object) {
		return setJsonString(key, object, 0);
	}

	public static boolean setJsonString(String key, Object object, long mills) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = jedisPool.getResource();
			if (mills > 0) {
				res = jedis.psetex(key, mills, FastJsonUtils.toJSONString(object));
			} else {
				res = jedis.set(key, FastJsonUtils.toJSONString(object));
			}
		} catch (Exception e) {
			logger.error("set json String error", e);
		} finally {
			close(jedis);
		}
		return "OK".equalsIgnoreCase(res);
	}

	public static <T> T getJsonObject(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return FastJsonUtils.parseObject(value, clazz);
		} catch (Exception e) {
			logger.error("get json String error", e);
		} finally {
			close(jedis);
		}
		return null;
	}

	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("exists error", e);
		} finally {
			close(jedis);
		}
		return false;
	}


	/**
	 * local old = redis.call('get', KEYS[1])
	 * if old then
	 *	 redis.call('del', KEYS[1])
	 *   return old
	 * else
	 *   redis.call('set', KEYS[1], ARGV[1])
	 *   return 1
	 * end
	 */
	/**
	 * redis原子操作，如果不存在，设置当前值返回null, 如果当前存在，清除并返回 
	 * 
	 * @param key
	 * @param clazz
	 * @param obj
	 * @return
	 */
	public static <T> T setIfNotExistsElseDelRtOld(String key, Class<T> clazz, Object obj) {
		Jedis jedis = jedisPool.getResource();
		try {
			String script = "local old = redis.call('get', KEYS[1]) if old then redis.call('del', KEYS[1])  "
					+ "return old  else redis.call('set', KEYS[1], ARGV[1])  return 'NO' end ";
			Object result = jedis.eval(script, Collections.singletonList(key),
					Collections.singletonList(FastJsonUtils.toJSONString(obj)));
			if ("NO".equals(result)) {
				// 不存在,并且设置成功
				return null;
			} else {
				return FastJsonUtils.parseObject(result, clazz);
			}

		} catch (Exception exception) {
			logger.error("setIfNotExistsElseDelRtOld error", exception);
		}finally {
			close(jedis);
		}
		return null;
	}

	/**
	 * redis原子操作，获取旧值, 并将新值set进去 <br/>
	 * <br/>
	 * local old = redis.call('get', KEYS[1])
	 * redis.call('set', KEYS[1], ARGV[1])
	 * return old
	 */
	public static <T> T getAndSwap(String key, Class<T> clazz, T obj) {
		Jedis jedis = jedisPool.getResource();
		try {
			String script = "local old = redis.call('get', KEYS[1])  redis.call('set', KEYS[1], ARGV[1])  return old";
			Object result = jedis.eval(script, Collections.singletonList(key),Collections.singletonList(FastJsonUtils.toJSONString(obj)));
			if ("NO".equals(result)) {
				return null;
			} else {
				return FastJsonUtils.parseObject(result, clazz);
			}
		}catch (Exception exception) {
			logger.error("getAndSwap error", exception);
		}finally {
			close(jedis);
		}
		return null;
	}
}