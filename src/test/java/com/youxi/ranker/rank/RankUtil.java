package com.youxi.ranker.rank;

import com.example.demo4.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/5/15 15:56
 * @Description:
 */
public class RankUtil {
	public static final Logger logger = LoggerFactory.getLogger(RankUtil.class);

	public static long MAX_TIME = 0;

	public static long MAX_SCORE = ~(-1L << 53);

	static {
		MAX_TIME = TimeUtil.toEpochMilli(LocalDate.of(2050, 1, 1).atStartOfDay());

		if (MAX_TIME - TimeUtil.getTime() <= TimeUtil.dayMills * 365) {
			logger.error("MAX_TIME 时间长度不够了！！！！ 请检查代码！！！");
		}
	}

	/**
	 * 将需要排行的值按时间先后加工，存入redis
	 * Double类型，小数点前存放值，小数点后存放到指定时间点的剩余时间（毫秒）
	 * 先到榜在前
	 * score 不能超过52位
	 * <p>
	 * ASC, //正序 从小到大
	 * DESC, //倒序 从大到小
	 *
	 * @param score 要处理的排行数值
	 * @return 加工后的排行数值
	 */
	public static double encodeScoreDESC(long score) {
		if (score > MAX_SCORE) {
			logger.error("score: {} 过大，请及时检查！！！", score);
		}
		String time = String.valueOf(MAX_TIME - System.currentTimeMillis());
		String val = score + "." + time;
		return Double.parseDouble(val);
	}

	/**
	 * ASC, //正序 从小到大
	 * DESC, //倒序 从大到小
	 */
	public static double encodeScoreASC(long score) {
		String val = score + "." + TimeUtil.getTime();
		return Double.parseDouble(val);
	}


	public static long decodeScore(double score) {
		return (long) score;
	}

	/**
	 * 检测long转double精度是否会丢失
	 *
	 * @return true 不会丢失 false：会丢失
	 */
	public static double longToDoubleExact(long value) {
		// double 最大为53位有效;如果大于53位就会丢失整数的精度
		if (value > MAX_SCORE) {
			logger.error("score: {} doubleScore {} 过大 转成double 会造成进度丢失，请及时检查！！！", value, (double) value, new RuntimeException());
			return (double) value;
		}
		return (double) value;
	}

}
