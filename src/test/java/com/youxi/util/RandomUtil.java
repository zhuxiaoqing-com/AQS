/**
 * 文   件  名：  RandomUtil.java
 * 工   程  名：  MainServer
 * 创建日期：  2015年2月5日 下午2:38:48
 * 创建作者：  杨  强 <281455776@qq.com>
 */
package com.youxi.util;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具类
 *
 * @author 杨 强
 */
public class RandomUtil {

	private static final Map<String, Double> curPrivateRate = new ConcurrentHashMap<>();
	private static final Map<String, Integer> lastPrivateRate = new ConcurrentHashMap<>();


	/**
	 * 随机产生min到max之间的一个小数值，包含min和不包含max
	 *
	 * @param min
	 * @param max 不包含
	 * @return
	 */
	public static double random(double min, double max) {
		if (min >= max) {
			return min;
		}
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

	/**
	 * 随机产生min到max之间的一个整数值，包含min和max
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min, int max) {
		if (min >= max) {
			return min;
		}
		return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
	}

	public static long random(long min, long max) {
		if (min >= max) {
			return min;
		}
		return ThreadLocalRandom.current().nextLong(max - min + 1) + min;
	}

	/**
	 * 是否能按照指定几率发生
	 *
	 * @param persent 百分率
	 * @return
	 */
	public static boolean isRandomThingHappen100(int persent) {
		return persent > RandomUtil.getRandom(100);
	}

	/**
	 * 是否 发生 万分比较概率
	 *
	 * @param persent
	 * @return
	 */
	public static boolean isRandomThingHappen10000(int persent) {
		return persent > RandomUtil.getRandom(10000);
	}

	public static int getRandom(int num) {
		return (int) (Math.random() * num);
	}


	/**
	 * 随机一个double类型的数
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static Double randomDouble(Double min, Double max) {
		if (min > max) {
			throw new IllegalArgumentException("传入的范围不合法!最小值不能大于最大值！");
		}
		DecimalFormat df = new DecimalFormat("#.0");
		Double num = ThreadLocalRandom.current().nextDouble(max - min) + min;
		return Double.valueOf(df.format(num));
	}

	/**
	 * 根据几率计算是否生成，成功几率是sucRange/maxRange
	 *
	 * @param maxRange 最大范围，随机范围是[1,maxRange]
	 * @param sucRange 成功范围，成功范围是[1,sucRange]
	 * @return 成功true失败false
	 */
	public static boolean isGenerate(int maxRange, int sucRange) {
		if (maxRange == sucRange) {
			return true;
		}
		if (sucRange <= 0) {
			return false;
		}
		return random(1, maxRange) <= sucRange;
	}

	/**
	 * 从指定的的元素集中随机一个元素
	 *
	 * @param collection 元素集
	 * @return
	 */
	public static <T> T randomElement(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException("元素集不能为空！");
		}
		int index = random(0, collection.size() - 1);
		Iterator<T> it = collection.iterator();
		for (int i = 0; i <= index && it.hasNext(); i++) {
			T t = it.next();
			if (i == index) {
				return t;
			}
		}
		return null;
	}

	/**
	 * 从指定的元素数组中随机出一个元素
	 *
	 * @param array 元素数组
	 * @return
	 */
	public static <T> T randomElement(T[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("元素数组不能为空！");
		}
		return randomElement(Arrays.asList(array));
	}

	/**
	 * 根据每个几率返回随机的一个索引
	 *
	 * @param probs
	 * @return -1失败or随机的索引
	 */
	public static int randomIndexByProb(List<Integer> probs) {
		if (probs.isEmpty()) {
			return -1;
		}
		LinkedList<Integer> newProbs = new LinkedList<Integer>();
		int lastTotalProb = 0;
		for (int i = 0; i < probs.size(); i++) {
			int currentTotalProb = lastTotalProb + probs.get(i);
			newProbs.add(currentTotalProb);
			lastTotalProb = currentTotalProb;
		}
		if (newProbs.isEmpty()) {
			return -1;
		}
		int totalProb = newProbs.getLast();
		if (totalProb == 0) {// 总概率为0
			return -1;
		}
		int random = random(0, totalProb - 1);
		for (int i = 0; i < newProbs.size(); i++) {
			int cuttentTotalProb = newProbs.get(i);
			if (cuttentTotalProb > random) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据每个几率返回随机的一个索引
	 *
	 * @param array
	 * @return -1失败or随机的索引
	 */
	public static int randomIndexByProb(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("元素数组不能为空！");
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int i : array) {
			list.add(i);
		}
		return randomIndexByProb(list);
	}

	/**
	 * 在指定数字范围内，随机获取n个不重复数字
	 *
	 * @param min 指定范围最小值
	 * @param max 指定范围最大值
	 * @param n   随机获取不重复数字个数
	 * @return 随机获取n个不重复数字结果集
	 */
	public static List<Integer> randomNoRepeatNums(int min, int max, int n) {
		int range = max - min + 1;
		//当下列情况任何一个成立时，返回null
		//①指定数字范围的最小值大于最大值，
		//②需要随机获取的数字个数小于0，
		//③需要随机获取的数字个数大于指定数字范围的数字个数
		List<Integer> randomlist = new ArrayList<>();
		if (min > max || n < 0 || n > range) {
			return randomlist;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			//随机产生一个数，若已存在则取其对应的值,每次随机范围减一，即去掉上次的最大值
			int num = ThreadLocalRandom.current().nextInt(range - i) + min;
			randomlist.add(map.getOrDefault(num, num));

			//将被随机到的数，用map映射当前最大值（表示该值已被取走，用当前最大值替换该值,继续随机）
			int j = max - i;
			map.put(num, map.getOrDefault(j, j));
		}

		return randomlist;
	}


	/**
	 * 获得一个不重复的随机序列.比如要从20个数中随机出6个不同的数字,可以先把20个数存入数组中,然后调用该方法.注意方法调用完后totals
	 * 里面的elements的顺序是变了的.适用于从一部分数中找出其中的绝大部分
	 *
	 * @param totals 所有数据存放在数组中
	 * @param dest   要返回的序列的长度
	 * @return 生成的序列以数组的形式返回
	 */
	public static int[] getRandomArray(int min, int max, int n) {
		int range = max - min + 1;
		if (min > max || n < 0 || n > range) {
			return new int[0];
		}

		int[] totals = new int[range + 1];
		for (int i = 0; i <= range; i++) {
			totals[i] = i + min;
		}

		int[] ranArr = new int[n];
		for (int i = 0; i < n; i++) {
			// 得到一个位置
			int j = random(0, range - i);
			ranArr[i] = totals[j];
			// 将未用的数字放到已经被取走的位置中,这样保证不会重复
			totals[j] = totals[range - i];
		}
		return ranArr;
	}

	/**
	 * 从集合中获取随机一个
	 *
	 * @param collections
	 * @return
	 */
	public static <T> T getRandom(Collection<T> collections) {
		if (collections == null || collections.isEmpty())
			return null;

		Object[] os = collections.toArray();
		int index = random(0, os.length - 1);
		return (T) os[index];
	}

	/**
	 * 从集合中获取随机n个不重复的元素
	 *
	 * @param collections
	 * @return
	 */
	public static <T> List<T> getRandom(Collection<T> collections, int n) {
		if (collections == null || collections.isEmpty() || collections.size() < n) {
			return Collections.emptyList();
		}
		List<T> randoms = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			T random = getRandom(collections);
			collections.remove(random);
			randoms.add(random);
		}
		return randoms;
	}

	/**
	 * 从 [min, max]中取出一组不重复的随机数
	 *
	 * @param min
	 * @param max
	 * @param num 个数
	 * @return
	 */
	public static java.util.List<Integer> getRandomValues(int min, int max, int num) {

		final java.util.List<Integer> list = new java.util.LinkedList<Integer>();
		if (max < 0)
			return list;
		if (num <= 0)
			return list;
		if (max < min)
			return list;

		if (num > (max - min + 1)) {
			num = max - min + 1;
		}

		for (int i = 0; i < num; ++i) {
			int val = random(min, max);
			while (list.contains(val)) {
				val = min + ((val + 1 - min) % (max - min + 1));
			}

			list.add(val);
		}

		return list;
	}
}
