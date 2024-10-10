package com.youxi.building;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.TextFormat;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/12 10:26
 * @Description:
 */
public class Misc {
	public static final String UNKNOW = "unknow";

	private static final ThreadLocalRandom random = ThreadLocalRandom.current();
//	private static Random random = new Random();


	public static Random getRandom() {
		return random;
	}

	/**
	 * 获取随机数 [ start, end ] 或 [ end, start ]
	 *
	 * @param start  起始值
	 * @param end    结束值
	 * @param random 随机数实体，可能某些功能要用到自己的Random实例，以保证绝对整体随机
	 * @return
	 */
	public static int getRandomBetween(final int start, final int end, Random random) {
		return end > start ? random.nextInt(end - start + 1) + start : random.nextInt(start - end + 1) + end;
	}

	/**
	 * 获取随机数 [ start, end ] 或 [ end, start ]
	 *
	 * @param start 起始值
	 * @param end   结束值
	 * @return
	 */
	public static int getRandomBetween(final int start, final int end) {
		return end > start ? random.nextInt(end - start + 1) + start : random.nextInt(start - end + 1) + end;
	}

	/**
	 * 获取随机数 [ start, end ] 或 [ end, start ]
	 *
	 * @param start 起始值
	 * @param end   结束值
	 * @return
	 */
	public static long getRandomBetween(final long start, final long end) {
		return end > start ? random.nextLong(end - start + 1) + start : random.nextLong(start - end + 1) + end;
	}

	public static double getRandomBetween(double min, double max) {
		if (min >= max) {
			return min;
		}
		return random.nextDouble(min, max);
	}

	/**
	 * 从集合中获取随机一个
	 *
	 * @param collections
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRandom(Collection<T> collections) {
		if (collections == null || collections.isEmpty())
			return null;

		if (collections.size() == 1) {
			return collections.iterator().next();
		}

		int index = getRandomBetween(0, collections.size() - 1);
		Iterator<T> it = collections.iterator();
		for (int i = 0; i <= index && it.hasNext(); i++) {
			T t = it.next();
			if (i == index) {
				return t;
			}
		}
		return null;
	}
	public static <T> T getRandom(List<T> collections) {
		if (collections == null || collections.isEmpty())
			return null;

		int index = getRandomBetween(0, collections.size() - 1);
		return collections.get(index);
	}

	/**
	 * 获取随机百分整数
	 *
	 * @return [1, 100] 的随机数
	 */
	public static int getRatePercent() {

		return getRandomBetween(1, 100);
	}


	/**
	 * 检测指定概率
	 *
	 * @param numerator   分子
	 * @param denominator 分母
	 */
	public static boolean checkRatePercent(int numerator, int denominator) {
		if (numerator <= 0) {
			return false;
		} else if (numerator >= denominator) {
			return true;
		}
		return numerator >= getRandomBetween(1, denominator);
	}



	/**
	 * 检测百分数概率是否成功
	 *
	 * @param value 给定的概率
	 * @return
	 */
	public static boolean checkRatePercent(double value) {
		if (value <= 0) {
			return false;
		}else if (value >= 1) {
			return true;
		}
		return value >= random.nextDouble();
	}


	/**
	 * 获取以[0, base]内的概率随机数值 [base, 0]也没关系 :)
	 *
	 * @param base
	 * @return
	 */
	public static int getRateValue(int base) {

		return getRandomBetween(0, base);
	}

	public static int getRateValue(int base, Random random) {

		return getRandomBetween(0, base, random);
	}

	/**
	 * 检测概率事件是否成功
	 *
	 * @param base  基数
	 * @param value 概率值
	 * @return
	 */
	public static boolean checkRate(int base, int value) {

		if (base <= 0)
			return false;
		// 这里取到的tmp 范围是[0,base]也就是说有base+1个可取值,而不是[0,base-1]也就是base个可取的值
		int tmp = getRateValue(base - 1);
		return value > tmp;
	}

	public static boolean checkRate(int base, int value, Random random) {

		if (base < 0)
			return false;
		int tmp = getRateValue(base, random);
		return value > tmp;
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
		if (min > max || n < 0) {
			return randomlist;
		}

		if(n > range){
			for (int i = min; i <= max; i++) {
				randomlist.add(i);
			}
			return randomlist;
		}

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			//随机产生一个数，若已存在则取其对应的值,每次随机范围减一，即去掉上次的最大值
			int num = random.nextInt(range - i) + min;
			randomlist.add(map.getOrDefault(num, num));

			//将被随机到的数，用map映射当前最大值（表示该值已被取走，用当前最大值替换该值,继续随机）
			int j = max - i;
			map.put(num, map.getOrDefault(j, j));
		}

		return randomlist;

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
			int val = getRandomBetween(min, max);
			while (list.contains(val)) {
				val = min + ((val + 1 - min) % (max - min + 1));
			}

			list.add(val);
		}

		return list;
	}



	/**
	 * 返回一组概率中最后发生的那个概率.比如玩家做完任务后有30%概率获得物品A,30%概率
	 * 获得物品B,40%获得物品C.把概率作为数组传入,然后该方法返回数组的下标. 在上面的例子中,假如用户传入[30,30,40]
	 * 100,返回2，则表示玩家应获得物品C
	 *
	 * @param pros 概率数组,相加之和应该等于base，否则返回-1;
	 * @param base 概率单位,比如百分数就是100
	 * @return 数组下标,对应某个概率在随机状态下发生
	 */
	public static int getProbability(int[] pros, int base) {

		if (pros == null || pros.length == 0)
			return -1;
		int sum = 0;
		for (int i = 0; i < pros.length; i++) {
			sum += pros[i];
		}
		// 相加之和应该等于base，否则返回getProbability(pros);
		if (sum != base)
			return getProbability(pros);
		int value = getRandomBetween(0, base - 1);

		for (int i = 0; i < pros.length; i++) {
			if (value < pros[i])
				return i;
			else
				value = value - pros[i];
		}

		return -1;
	}

	/**
	 * 返回一组概率中最后发生的那个概率.比如玩家做完任务后有30%概率获得物品A,30%概率
	 * 获得物品B,40%获得物品C.把概率作为数组传入,然后该方法返回数组的下标. 在上面的例子中,假如用户传入[30,30,40]
	 * 100,返回2，则表示玩家应获得物品C
	 *
	 * @param pros 概率数组,相加之和应该等于base，否则返回-1;
	 * @param base 概率单位,比如百分数就是100
	 * @return 数组下标,对应某个概率在随机状态下发生
	 */
	public static int getProbability(int[] pros, int base, Random random) {

		if (pros == null || pros.length == 0)
			return -1;
		int sum = 0;
		for (int i = 0; i < pros.length; i++) {
			sum += pros[i];
		}
		// 相加之和应该等于base，否则返回getProbability(pros);
		if (sum != base)
			return getProbability(pros, random);
		int value = getRandomBetween(0, base - 1, random);

		for (int i = 0; i < pros.length; i++) {
			if (value < pros[i])
				return i;
			else
				value = value - pros[i];
		}

		return -1;
	}

	public static int getProbability(long[] pros) {

		if (pros == null || pros.length == 0)
			return -1;
		long sum = 0;
		for (int i = 0; i < pros.length; i++) {
			sum += pros[i];
		}
		if (sum <= 0) {
			return 0;
		}
		long value = getRandomBetween(0, sum - 1);

		for (int i = 0; i < pros.length; i++) {
			if (value < pros[i])
				return i;
			else
				value = value - pros[i];
		}

		return -1;
	}

	/**
	 * 和getProbability(int []pros,int base)类似,优点是不需要提供base,程序会把pros数组之和作为base
	 *
	 * @param pros
	 * @return
	 */
	public static int getProbability(int[] pros) {

		if (pros == null || pros.length == 0)
			return -1;
		int sum = 0;
		for (int i = 0; i < pros.length; i++) {
			sum += pros[i];
		}
		if (sum <= 0) {
			return 0;
		}
		int value = getRandomBetween(0, sum - 1);

		for (int i = 0; i < pros.length; i++) {
			if (value < pros[i])
				return i;
			else
				value = value - pros[i];
		}

		return -1;
	}

	/**
	 * 和getProbability(int []pros,int base)类似,优点是不需要提供base,程序会把pros数组之和作为base
	 *
	 * @param pros
	 * @return
	 */
	public static int getProbability(int[] pros, Random random) {

		if (pros == null || pros.length == 0)
			return -1;
		int sum = 0;
		for (int i = 0; i < pros.length; i++) {
			sum += pros[i];
		}

		int value = getRandomBetween(0, sum - 1, random);

		for (int i = 0; i < pros.length; i++) {
			if (value < pros[i])
				return i;
			else
				value = value - pros[i];
		}

		return -1;
	}

	public static <T> T getProbability(Map<T, Integer> pros){
		List<T> value = new ArrayList<T>(pros.size());
		int[] weight = new int[pros.size()];
		int index = 0;
		for (Map.Entry<T, Integer> entry : pros.entrySet()) {
			value.add(entry.getKey());
			weight[index] = entry.getValue();
			index++;
		}
		return value.get(getProbability(weight));
	}

	public static int getProbability(java.util.List<Integer> prolist, int base) {

		final int[] probs = new int[prolist.size()];
		for (int i = 0; i < probs.length; i++)
			probs[i] = prolist.get(i);

		return getProbability(probs, base);
	}

	public static int getProbability(java.util.List<Integer> prolist) {

		final int[] probs = new int[prolist.size()];
		for (int i = 0; i < probs.length; i++)
			probs[i] = prolist.get(i);

		return getProbability(probs);
	}

	public static int getProbability(java.util.List<Integer> prolist, Random random) {

		final int[] probs = new int[prolist.size()];
		for (int i = 0; i < probs.length; i++)
			probs[i] = prolist.get(i);

		return getProbability(probs, random);
	}

	/**
	 * 根据概率获取 num个数值
	 *
	 * @param pros 每个整数出现的概率
	 * @param num  要取出的结果值数量
	 * @param base 根据基数
	 * @return
	 */
	public static int[] getValuesByRate(int[] pros, int num, int base) {

		final int[] ret = new int[num];
		for (int i = 0; i < num; i++)
			ret[i] = getProbability(pros, base);

		return ret;
	}

	public static java.util.List<Integer> getValuesByRate(java.util.List<Integer> pros, int num, int base) {

		final java.util.List<Integer> list = new java.util.ArrayList<Integer>();
		final int[] probs = new int[pros.size()];
		for (int i = 0; i < probs.length; i++)
			probs[i] = pros.get(i);

		for (int i = 0; i < num; i++)
			list.add(getProbability(probs, base));

		return list;
	}

	/**
	 * 获得一个不重复的随机序列.比如要从20个数中随机出6个不同的数字,可以先把20个数存入数组中,然后调用该方法.注意方法调用完后totals
	 * 里面的elements的顺序是变了的.适用于从一部分数中找出其中的绝大部分
	 *
	 * @param totals 所有数据存放在数组中
	 * @param dest   要返回的序列的长度
	 * @return 生成的序列以数组的形式返回
	 */
	public static int[] getRandomArray(int[] totals, int dest) {
		if (dest <= 0)
			throw new IllegalArgumentException();
		if (dest > totals.length) // 如果要选的数比数组的长度还长,那就直接返回整个数组
			return totals;
		int[] ranArr = new int[dest];
		for (int i = 0; i < dest; i++) {
			// 得到一个位置
			int j = random.nextInt(totals.length - i);
			ranArr[i] = totals[j];
			// 将未用的数字放到已经被取走的位置中,这样保证不会重复
			totals[j] = totals[totals.length - 1 - i];
		}
		return ranArr;
	}

	// 这个方法和上边的一样，只是为了满足一些数组里装的是roleid factionke什么的
	// 比较麻烦 ，所以就抄了一遍
	public static Long[] getRandomArray2(Long[] srcArray, int dest) {

		if (dest <= 0)
			throw new IllegalArgumentException();
		if (dest > srcArray.length) // 如果要选的数比数组的长度还长,那就直接返回整个数组
			return srcArray;
		Long[] ranArr = new Long[dest];
		for (int i = 0; i < dest; i++) {
			// 得到一个位置
			int j = random.nextInt(srcArray.length - i);
			ranArr[i] = srcArray[j];
			// 将未用的数字放到已经被取走的位置中,这样保证不会重复
			srcArray[j] = srcArray[srcArray.length - 1 - i];
		}
		return ranArr;
	}

	/**
	 * 从一个集合中，随机选取几个，构成一个新的List
	 *
	 * @param collection 源集合
	 * @param num        随机选取的个数
	 * @return
	 */
	public static <T> List<T> getRandomList(List<T> collection, int num) {
		if(num <= 0) {
			return Collections.emptyList();
		}
		List<Integer> integers = randomNoRepeatNums(0, collection.size() - 1, num);
		return integers.stream().map(collection::get).collect(Collectors.toList());
	}
	public static <T> List<T> getRandomList(Collection<T> collection, int num) {
		if(num <= 0) {
			return Collections.emptyList();
		}

		// 如果集合小于需要的个数，则直接返回一个乱序的list
		List<T> result = new LinkedList<T>();
		if (collection.size() <= num) {
			result.addAll(collection);
			//randomlizeList(result);
			return result;
		}

		int[] tmpArray = new int[collection.size()];
		for (int i = 0; i < tmpArray.length; i++)
			tmpArray[i] = i;
		int[] chosen = Misc.getRandomArray(tmpArray, num);
		Arrays.sort(chosen);
		int i = 0;
		int j = 0;
		for (T t : collection) {
			if (chosen[j] == i) {
				result.add(t);
				j++;
				if (j >= chosen.length)
					break;
			}
			i++;
		}
		return result;
	}

	/**
	 * 将一个list打乱
	 *
	 * @param      <T>
	 * @param list
	 */
	public static <T> void randomlizeList(java.util.List<T> list) {
		for (int i = 1; i < list.size(); i++) {
			final int idx = getRandomBetween(0, i);
			if (i != idx) {
				T t = list.get(idx);
				list.set(idx, list.get(i));
				list.set(i, t);
			}
		}
	}

	/**
	 * 在一定范围内随机化数值 主要用于随机化伤害等，例如最终伤害随机为原始值的90%~110%
	 *
	 * @param value  初始值
	 * @param minPct 最小比例
	 * @param maxPct 最大比例
	 * @return 在范围随机后的值
	 */
	public static double randomValue(final double value, final double minPct, final double maxPct) {
		double randomBetween = getRandomBetween(minPct, maxPct);
		return value * randomBetween;
	}

	/**
	 * 生成一个不重复的随机整数序列
	 *
	 * @param min     最小值
	 * @param max     最大值
	 * @param step    值之间的间隔
	 * @param retsize 返回序列的长度
	 * @return
	 */
	public static java.util.List<Integer> getRandomNotRepeatIntList(int min, int max, int step, int retsize) {
		if (max < min || step <= 0 || (max - min + 1) / step < retsize) {
			throw new IllegalArgumentException("错误的参数");
		}
		List<Integer> ret = new ArrayList<Integer>();
		int size = (max - min + 1) / step;
		Map<Integer, Integer> swaps = new HashMap<Integer, Integer>();
		for (int i = 0; i < retsize; i++) {
			int idx = getRandomBetween(i, size - 1);
			Integer curval = swaps.get(i);
			if (curval == null) {
				curval = i * step + min;
			}
			if (idx == i) {
				ret.add(curval);
				continue;
			}
			Integer swapval = swaps.get(idx);
			if (swapval == null) {
				swapval = idx * step + min;
			}
			if (idx > i) {
				swaps.put(idx, curval);
			}
			ret.add(swapval);
		}
		return ret;
	}

	public static int compareTo(float o1, float o2) {
		return ((int) (o1 * 1000)) - ((int) (o2 * 1000));
	}

	/**
	 * 获得名字字符串
	 *
	 * @param strList
	 * @param symbol
	 * @return
	 */
	public static String makeNameStr(List<String> strList, Character symbol) {
		StringBuilder sb = new StringBuilder("");
		for (String str : strList) {
			if (sb.length() > 0)
				sb.append(symbol);

			sb.append(str);
		}

		return sb.toString();
	}

	/**
	 * 返回一组概率中最后发生的那个概率.比如玩家做完任务后有10%概率获得物品A,20%概率
	 * 获得物品B,30%获得物品C,40%不获得任何物品.把概率作为数组传入,然后该方法返回数组的下标. 在上面的例子中,假如用户传入[10,20,30]
	 * 100,返回2，则表示玩家应获得物品C
	 *
	 * @param pros
	 * @param base
	 * @return
	 */
	public static int getProbabilityWithBiggerBase(Integer[] pros, int base) {
		if (pros == null || pros.length == 0)
			return -1;
		int sum = 0;
		for (int i = 0; i < pros.length; i++) {
			sum += pros[i];
		}
		// 相加之和应该等于base，否则返回getProbability(pros);
		if (sum >= base)
			base = sum;
		int value = getRandomBetween(0, base - 1);
		for (int i = 0; i < pros.length; i++) {
			if (value < pros[i])
				return i;
			else
				value = value - pros[i];
		}
		return -1;
	}

	/**
	 * 从total个数中随机选出n个数
	 *
	 * @param n
	 * @param total
	 * @return
	 */
	public static List<Integer> getRandomList(int n, int total) {
		List<Integer> res = new ArrayList<Integer>();
		if (n > total)
			return res;

		Random rm = new Random();
		int counter = 0;
		while (res.size() < n) {
			if (counter > n * 50)
				throw new RuntimeException("MISC.getRandomList方法调用次数过多！！！");
			counter++;
			int r = rm.nextInt(100000) % total + 1;
			if (!res.contains(r)) {
				res.add(r);
			}
		}
		return res;
	}

	public static boolean isProbability(Double odds) {
		if (odds.intValue() >= 1) {
			return true;
		}
		if (odds.intValue() < 0) {
			return false;
		}
		return random.nextDouble() <= odds;
	}

	public static float formatFloat(float value, int scale) {

		return (((long) (value * (int) Math.pow(10, scale))) * (float) Math.pow(10, -scale));
//		BigDecimal bd = new BigDecimal(value);
//		bd = bd.setScale(scale, BigDecimal.ROUND_FLOOR); // 向下取整
//		return bd.floatValue();
	}

	public static int getIntIndexByXY(short high, short low) {
		return (((int) low & 0xFFFF) | (((int) high << 16) & 0xFFFF0000));
	}

	public static int getIntIndexByXY(int high, int low) {
		return (((int) low & 0xFFFF) | (((int) high << 16) & 0xFFFF0000));
	}

	public static void main(String[] args) throws InterruptedException {
//		System.out.println(0xFFFF0000);
		/*System.err.println(getRandomBetween(0, 1));
		System.out.println(findDecimalTopDigit(Integer.MAX_VALUE));
		System.out.println(findDecimalTopDigit(233234234));
		System.out.println(Integer.MAX_VALUE);

		IdFactory.setSid(100011);
		long playerId = IdFactory.createPlayerId();
		Thread.sleep(1100);
		long playerId2 = IdFactory.createPlayerId();
		Thread.sleep(1100);
		long playerId3 = IdFactory.createPlayerId();
		Thread.sleep(1100);
		long playerId4 = IdFactory.createPlayerId();

		System.out.println(playerId+ " ___ "  +hashPlayerId(playerId) +"    "+ hashPlayerId(playerId)%2 );
		System.out.println(playerId2+ " ___ " +hashPlayerId(playerId2)+"    "+ hashPlayerId(playerId2)%2);
		System.out.println(playerId3+ " ___ " +hashPlayerId(playerId3)+"    "+ hashPlayerId(playerId3)%2);
		System.out.println(playerId4+ " ___ " +hashPlayerId(playerId4)+"    "+ hashPlayerId(playerId4)%2);

		 playerId = IdFactory.createPlayerId();

		 playerId2 = IdFactory.createPlayerId();
		 playerId3 = IdFactory.createPlayerId();
		 playerId4 = IdFactory.createPlayerId();

		System.out.println("---------------------");

		System.out.println(playerId+ " ___ "  +hashPlayerId(playerId)+"    "+ hashPlayerId(playerId)%2 );
		System.out.println(playerId2+ " ___ " +hashPlayerId(playerId2)+"    "+ hashPlayerId(playerId2)%2);
		System.out.println(playerId3+ " ___ " +hashPlayerId(playerId3)+"    "+ hashPlayerId(playerId3)%2);
		System.out.println(playerId4+ " ___ " +hashPlayerId(playerId4)+"    "+ hashPlayerId(playerId4)%2);*/

		/*String args1 = formatArgs(1, "31", "32");
		System.out.println(args1);
		List<String> strings = parseArgs(args1);
		System.out.println(strings);*/
		System.out.println(Misc.getLongHigh(4295067370L));
		System.out.println(Misc.getLongLow(4295067370L));
	}

	public static short getIntLow(int index) {
		return (short) (0xFFFF & index);
	}

	public static short getIntHigh(int index) {
		return (short) ((0xFFFF0000 & index) >> 16);
	}

	public static long getLongIndexByXY(int high, int low) {
		return ((long) low & 0xFFFFFFFFL) | (((long) high << 32) & 0xFFFFFFFF00000000L);
	}

	public static int getLongLow(long index) {
		return (int) (0xFFFFFFFFL & index);
	}

	public static int getLongHigh(long index) {
		return (int) ((0xFFFFFFFF00000000L & index) >> 32);
	}



	/**
	 * 根据服务器id获取服务器类型
	 */
/*	public static App.ST findServerType(int serverId) {
		if (serverId > 1_000_000) {
			return App.ST.BATTLE;
		}
		if (serverId > 100_000) {
			return App.ST.BATTLEMONITOR;
		}
		if (serverId > 10_000) {
			return App.ST.GATEWAY;
		}
		if (serverId > 1_000) {
			return App.ST.LOGIC;
		}
		if (serverId > 100) {
			return App.ST.LOBBY;
		}
		return null;
	}*/

	// 小于等于100的是全服服务器
	public static boolean isGlobalServer(int serverId) {
		return serverId > 0 && serverId <= 100;
	}

	/**
	 * 获取正数十进制的最高位
	 */
	public static int findDecimalTopDigit(int number) {
		if (number >= 10_000_000) {
			number /= 10_000_000;
		}
		if (number >= 10_000) {
			number /= 10_000;
		}
		if (number >= 100) {
			number /= 100;
		}
		if (number >= 10) {
			number /= 10;
		}
		return number;
	}


	public static String pbToString(final MessageOrBuilder message) {
		if(message == null) {
			return "";
		}

		return TextFormat.shortDebugString(message);
	}

	public static String pbToJson(com.google.protobuf.GeneratedMessage pb) {
		byte[] bytes = pb.toByteArray();
		return Base64.getEncoder().encodeToString(bytes);
	}




	public static <T> List<List<T>> splitList(Collection<T> list, int num) {
		if (list instanceof List && list.size() <= num) {
			return Collections.singletonList((List<T>) list);
		}

		// 向上取整
		int limit = (list.size() + num - 1) / num;
		return Stream.iterate(0, f -> f + 1).limit(limit)
				.map(a -> list.stream().skip((long) a * num).limit(num).collect(Collectors.toList()))
				.collect(Collectors.toList());
	}

	/**
	 * 检测两个map是否相等
	 */
	public static <K, V> boolean mapEquals(Map<K, V> map, Map<K, V> checkMap) {
		if (map.size() != checkMap.size()) {
			return false;
		}

		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (!checkMap.get(entry.getKey()).equals(entry.getValue())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 游戏里面所有排行榜都统一用一样的规则取
	 */
	public static <K, V> V findRankReward(TreeMap<K, V> treeMap, K key) {
		Map.Entry<K, V> kvEntry = treeMap.floorEntry(key);
		return kvEntry == null ? null : kvEntry.getValue();
	}
}
