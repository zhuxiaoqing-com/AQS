package com.example.demo4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TimeUtil {
	public static final Logger logger = LoggerFactory.getLogger(TimeUtil.class);

	// 一毫秒(单位：纳秒)
	public static final long millsNano = 1_000_000;
	public static final long secondMills = 1000;
	public static final long minuteMills = 1000 * 60L;
	public static final long hourMills = 1000 * 60 * 60L;
	public static final long dayMills = hourMills * 24L;
	public static final long weekMills = dayMills * 7L;

	public static final long TIME_ZONE_OFFSET = Calendar.getInstance().getTimeZone().getRawOffset();// 时区导致的差值

	public static final long addMills = 1000 * 60 * 60 * 24 * 3L;// Java时间的第一天（1970.1.1）是周四，这个是把一周的前三天补全的时间（以周一为第一天）

	/**
	 * 2015-02-23 12:12:12格式
	 */
	public static final String BGLOG_FORMAT = "yyyy-MM-dd'T'HH:mm:ss:SSS";
	/**
	 * HH:mm:ss
	 */
	public static final String HH_MM_SS = "HH:mm:ss";
	public static DateTimeFormatter bglogTimeFromatter = DateTimeFormatter.ofPattern(BGLOG_FORMAT);
	public static DateTimeFormatter HH_MM_SS_TimeFromatter = DateTimeFormatter.ofPattern(HH_MM_SS);

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * <li><b>这里的冒号是半角的</b>
	 */
	public static final String YYYYMMDDHHmmss = "yyyy-MM-dd HH:mm:ss";

	public static final String YYYYMMDD_HHmmss = "yyyy-MM-dd_HH-mm-ss";
	// 时间格式化到毫秒
	public static final String YYYYMMDDHHmmssSSS = "yyyy-MM-dd HH:mm:ss:SSS";
	/**
	 * yyyy-MM-dd HH：mm：ss
	 * <li><b>这里的冒号是全角的</b>
	 */
	public static final String YYYYMMDDHHmmss_1 = "yyyy-MM-dd HH：mm：ss";

	public static final String YYYYMMDDHHmmss_2 = "yyyy-MM-dd HH-mm-ss";

	public static final String YYYYMMDD = "yyyy-MM-dd";

	public static final String YYYYMMDD_2 = "yyyyMMdd";



	/**
	 * yyyy年M月d日 HH:mm
	 */
	public static final String YYYYMMDDHHmm = "yyyy年M月d日  HH:mm";

	public static final ZoneId zoneId = ZoneId.systemDefault();

	public static ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	public static ThreadLocal<SimpleDateFormat> df = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HH:mm");
		}
	};

	/***
	 * 获取时区 int值
	 * 
	 * @return
	 */
	public static int getIntTimeZone() {
		DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("1987-12-12").toString();
		return format.get().getTimeZone().getRawOffset() / (1000 * 3600);
	}

	/**
	 * 获取时区 String值
	 * 
	 * @return
	 */
	public static String getStringTimeZone() {
		return format.get().getTimeZone().getID();

	}

	/** 获取刷新时间 */
	public static long getTime(int addDay, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, addDay);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTimeInMillis();
	}
	
	public static long getTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取一周之内的星期几（周一返回1）
	 * @return
	 */
	public static int getDayOfWeek() {
		return getDayOfWeek(LocalDateTime.now());
	}
	
	/**
	 * 获取一年当中第几周
	 * @return
	 */
	public static int getToWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getDayOfWeek(long mills) {
		return getDayOfWeek(convertLocalDateTime(mills));
	}
	public static int getDayOfWeek(LocalDateTime localDateTime) {
		return localDateTime.get(ChronoField.DAY_OF_WEEK);
	}
	

	public static long getTime(String timeStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(timeStr);
			return date.getTime();
		} catch (ParseException e) {
			return 0;
		}

	}

	public static String getTimeStr(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tiemStr = sdf.format(time);
		return tiemStr;
	}

	public static String getTimeStr(long time, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String tiemStr = sdf.format(time);
		return tiemStr;
	}

	/**
	 * 一年当中第几天
	 * 
	 * @return
	 */
	public static int getToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取今日时间
	 * 
	 * @return yyyyMMdd
	 */
	public static int getNowDay() {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_2);
		return Integer.parseInt(sdf.format(System.currentTimeMillis()));
	}

	/**
	 * 获取小时
	 * 
	 * @return
	 */
	public static int getToDayH(int to_h) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, to_h);
		int hh = c.get(Calendar.HOUR_OF_DAY);
		return hh;
	}

	public static boolean afterTime(Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		try {
			date.setTime(df.get().parse(df.get().format(new Date())));
		} catch (ParseException e) {
			logger.error("",e);
		}
		if (date.getTime().after(beginTime) && date.getTime().before(endTime)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkTime(List<String> timeRanges) throws Exception {
		int nowWeek = TimeUtil.getDayOfWeek();
		String[] timeStr = timeRanges.get(nowWeek - 1).split("-");
		Date beginTime;
		Date endTime;
		beginTime = df.get().parse(timeStr[0]);
		endTime = df.get().parse(timeStr[1]);
		return TimeUtil.afterTime(beginTime, endTime);
	}

	public static long getTimeByHourInFuture(int hour) {
		long destTime = getTime(0, hour, 0, 0);
		if (destTime < System.currentTimeMillis()) {
			// 时间已经过去，读取一天后的
			destTime = getTime(1, hour, 0, 0);
		}
		return destTime;
	}

	public static long getTimeByHourInFuture(String hms) {
		long time = getTimeInMillsForPattern(hms, TimeUtil.HH_MM_SS);
		if (System.currentTimeMillis() > time) {
			time += dayMills;
		}
		return time;
	}


	/**
	 * 获得今天23:59:59的calendar
	 */
	public static Calendar getLastSecondCalendar() {
		Calendar current = Calendar.getInstance();
		current.set(Calendar.HOUR_OF_DAY, 23);
		current.set(Calendar.MINUTE, 59);
		current.set(Calendar.SECOND, 59);
		return current;
	}

	// 获取某天的第一秒 add by lc
	public static long getDayFirstSecond(long time) {
		return time - (time + TIME_ZONE_OFFSET) % dayMills;
	}

	/**
	 * 获取当前分钟的1秒钟
	 */
	public static long getMinuteFirstSecond(long time) {
		LocalDateTime amendDateTime = convertLocalDateTime(time);
		LocalDateTime localDateTime = amendDateTime.toLocalDate().atTime(amendDateTime.getHour(), amendDateTime.getMinute());
		return toEpochMilli(localDateTime);
	}

	// 获取某周的第一秒（以 周一算） add by lc
	public static long getWeekFirstSecond(long time) {
		return time - (time + TIME_ZONE_OFFSET + addMills) % weekMills;
	}

	// 获取该月的第一秒
	public static long getMonthFirstSecond(long time) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	// 获取该月的最后一秒
	public static long getMonthLastSecond(long time) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static boolean inTheSameMonth(long firstT, long secondT) {
		final Calendar cal1 = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();
		cal1.setTimeInMillis(firstT);
		cal2.setTimeInMillis(secondT);

		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
			return true;
		return false;

	}

	public static String formatTimeByType(long time, String format) {
		String myFormat = null == format ? "yyyy年M月d日    HH:mm:ss" : format;
		SimpleDateFormat fomat = new SimpleDateFormat(myFormat);
		return fomat.format(time);
	}

	public static boolean inTheSameYear(long firstT, long secondT) {
		final Calendar cal1 = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();
		cal1.setTimeInMillis(firstT);
		cal2.setTimeInMillis(secondT);

		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
			return true;
		return false;
	}

	/**
	 * 判断当前时间是不是在给定的时间段之间 必须是当天的 end > begin [0:00 ~ 24:00]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean isInTimeSubsection(String begin, String end) {
		try {
			begin = begin.replace("：", ":");
			end = end.replace("：", ":");

			String[] beginArray = begin.split(":");
			String[] endArray = end.split(":");
			if (beginArray.length < 2 || endArray.length < 2) {
				throw new RuntimeException("时间段格式错误：");
			}

			int beginHour = Integer.parseInt(beginArray[0]);
			int beginMin = Integer.parseInt(beginArray[1]);
			int endHour = Integer.parseInt(endArray[0]);
			int endMin = Integer.parseInt(endArray[1]);

			if (beginHour > endHour || beginHour < 0 || beginHour > 24 || endHour < 0 || endHour > 24) {
				throw new RuntimeException("结束时间段 应当大于开始时间段");
			}

			Calendar calendar = Calendar.getInstance();
			calendar.clear(Calendar.HOUR_OF_DAY);
			calendar.clear(Calendar.MINUTE);
			calendar.set(Calendar.HOUR_OF_DAY, beginHour);
			calendar.set(Calendar.MINUTE, beginMin);
			long startTime = calendar.getTimeInMillis();

			calendar.clear(Calendar.HOUR_OF_DAY);
			calendar.clear(Calendar.MINUTE);
			calendar.set(Calendar.HOUR_OF_DAY, endHour);
			calendar.set(Calendar.MINUTE, endMin);
			long endTime = calendar.getTimeInMillis();
			long now = System.currentTimeMillis();
			if (startTime <= now && endTime >= now) {
				return true;
			}

		} catch (Exception e) {
			logger.error("",e);
		}

		return false;
	}

	/**
	 * 返回当天的日期数字，形如2012年9月3号，则返回20120903，java整形值够用了
	 * 
	 * @modified Eric.Ma
	 * @return
	 */
	public static int getDateInteger(long time) {
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String yyyyMMdd = sdf.format(now.getTime());
		return Integer.valueOf(yyyyMMdd);
	}


	/**
	 * 由于SimpleDateFormat不是线程安全的，所以有错误 现在改成每次都新生成一个
	 * 
	 * @param str 格式：yyyy-MM-dd HH:mm:ss 标点可以是全角的也可以是半角的
	 * @return
	 */
	public static long parseDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHmmss);
		try {
			str = str.replace("：", ":");
			return sdf.parse(str).getTime();
		} catch (Exception e) {
			throw new RuntimeException("时间格式错误：" + str, e);
		}
	}

	/**
	 * 由于SimpleDateFormat不是线程安全的，所以有错误 现在改成每次都新生成一个
	 * 
	 * @param str 格式：yyyy-MM-dd HH:mm:ss 标点可以是全角的也可以是半角的
	 * @return
	 */
	public static long parseDate_2(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHmmss_2);
		try {
			str = str.replace("：", ":");
			return sdf.parse(str).getTime();
		} catch (Exception e) {
			throw new RuntimeException("时间格式错误：" + str, e);
		}
	}

	/**
	 * 获得给定时间的时分秒，并生成一个数字（公式：hour*10000+minute*100+second）。例：返回153605
	 * <li>传入-1 直接返回当天的时分秒
	 * 
	 * @param time
	 * @return
	 * @author Eric.Ma
	 */
	public static long getHHmmss(long time) {
		Calendar calendar = Calendar.getInstance();
		if (time > 0)
			calendar.setTimeInMillis(time);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		return hour * 10000 + minute * 100 + second;
	}

	public static SimpleDateFormat YYYYMMDDHHmmssSSS() {
		return new SimpleDateFormat(YYYYMMDDHHmmssSSS);
	}

	public static SimpleDateFormat YYYYMMDDHHmmss() {
		return new SimpleDateFormat(YYYYMMDDHHmmss);
	}

	public static SimpleDateFormat YYYYMMDDHHmmss_1() {
		return new SimpleDateFormat(YYYYMMDDHHmmss_1);
	}

	public static SimpleDateFormat YYYYMMDD() {
		return new SimpleDateFormat(YYYYMMDD);
	}

	public static SimpleDateFormat HH_MM_SS() {
		return new SimpleDateFormat(HH_MM_SS);
	}

	public static SimpleDateFormat YYYYMMDDHHmm() {
		return new SimpleDateFormat(YYYYMMDDHHmm);
	}


	/**
	 * 给定执行时间和当前时间，计算第一次执行的延迟，用于schedule
	 * <li>datetime 格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 * @return >= 0
	 * @author Eric.Ma
	 */
	public static long calcFirstDelay(String datetime) {
		long delta = parseDate(datetime) - Calendar.getInstance().getTimeInMillis();
		return delta < 0 ? 0 : delta;
	}

	public static int getDays(LocalDateTime day1, LocalDateTime day2) {
		return (int) ChronoUnit.DAYS.between(day1.toLocalDate(), day2.toLocalDate());
	}

	/**
	 * 生成时间戳形如yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String genTs() {
		return genTs(System.currentTimeMillis());
	}

	public static String genTs(long time) {
		return TimeUtil.YYYYMMDDHHmmss().format(new Date(time));
	}

	public static String genTs_() {
		return new SimpleDateFormat(YYYYMMDD_HHmmss).format(TimeUtil.getTime());
	}

	/***
	 * 传入Data类型日期，返回字符串类型时间(ISO8601标准时间)
	 */
	public static String getISO8601Timestamp(long time) {
		LocalDateTime localDateTime = convertLocalDateTime(time);
		return bglogTimeFromatter.format(localDateTime);
	}

	public static String getHHMMSS(long time) {
		LocalDateTime localDateTime = convertLocalDateTime(time);
		return HH_MM_SS_TimeFromatter.format(localDateTime);
	}

	/**
	 * 时间格式化到毫秒
	 *
	 * @param time time
	 */
	public static String formatMill(long time) {
		return TimeUtil.YYYYMMDDHHmmssSSS().format(new Date(time));
	}

	/**
	 * 将正常的周[1-7]转化成Calendar的星期几
	 * 
	 * @param i
	 * @return -1 失败
	 */
	public static int convertStdWeekDay(int i) {
		switch (i) {
		case 1:
			return Calendar.MONDAY;
		case 2:
			return Calendar.TUESDAY;
		case 3:
			return Calendar.WEDNESDAY;
		case 4:
			return Calendar.THURSDAY;
		case 5:
			return Calendar.FRIDAY;
		case 6:
			return Calendar.SATURDAY;
		case 7:
			return Calendar.SUNDAY;
		default:
			break;
		}
		return -1;
	}

	/**
	 * 获取时间戳
	 * 
	 * @param time 时间字符串，格式：HH:mm
	 * @return
	 */
	public static long getTimeInMillisForHHmm(String time) {
		return getTimeInMillsForPattern(time, "HH:mm");
	}

	public static long getTimeInMillsForPattern(String time, String pattern) {
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(pattern);
		Date date1 = null;
		try {
			date1 = dateFormat1.parse(time);
		} catch (ParseException e) {
			logger.error("", e);
			throw new RuntimeException("时间格式错误：" + time);
		}

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1.getTime());

		Calendar calendar2 = Calendar.getInstance();

		calendar1.set(Calendar.YEAR, calendar2.get(Calendar.YEAR));
		calendar1.set(Calendar.MONTH, calendar2.get(Calendar.MONTH));
		calendar1.set(Calendar.DAY_OF_MONTH, calendar2.get(Calendar.DAY_OF_MONTH));
		return calendar1.getTimeInMillis();
	}
	
	public static long getVipEndTime(int day,long startTime){
		//把startTime重置成当前日期0点
		SimpleDateFormat f = YYYYMMDD();
		String startTimeStr = f.format(startTime);
		try {
			startTime = f.parse(startTimeStr).getTime();
		} catch (Exception e) {
			logger.error("", e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(startTime);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTimeInMillis();
	}

	/**
	 * 判断是否重置，设置毫秒级时间间隔
	 */
	public static boolean isResetByMillsInterval(long currMill,long lastRefresh, long interval) {
		if (lastRefresh <= 0) {
			return true;
		}
		return lastRefresh + interval <= currMill;
	}


	/**
	 * 获取两天相隔时间; 过了 0 点就算过去一天
	 *
	 * @param startTime startTime
	 * @param endTime   endTime
	 * @return endTime > startTime return +day;  endTime < startTime return -day
	 */
	public static int getIntervalDayByNature(long startTime, long endTime) {
		return getIntervalDayByHour(startTime, endTime, 0);
	}

	/**
	 * 获取两天相隔时间 以 refreshHour 为一天过去的基准
	 *
	 * 例子：以10点为基准  2020-7-7 10:22 和 2020-7-7 11:22 相差0天
	 * 例子：以11点为基准  2020-7-7 10:22 和 2020-7-7 11:22 相差1天
	 * @return  endTime > startTime return +day;  endTime < startTime return -day
	 */
	public static int getIntervalDayByHour(long startTime, long endTime, int refreshHour) {
		long startMill = findNextResetTime(startTime, refreshHour);
		long endMill = findNextResetTime(endTime, refreshHour);
		return (int) ((endMill - startMill) / dayMills);
	}


	/**
	 * 判断是否可以刷新;默认0点刷新
	 *
	 * @param lastResetTime 上一次的刷新时间
	 * @return true:刷新 false: 不刷新
	 */
	public static boolean isResetByNatureDay(long lastResetTime) {
		return isResetByDay(lastResetTime, 0);
	}



	/**
	 * 判断是否可以刷新(每小时刷新)
	 *
	 * @param lastResetTime 上一次的刷新时间
	 * @param cycleDay 每n天刷新一次
	 * @return true:刷新 false: 不刷新
	 */
	public static boolean isResetByOpenServerTime(long currMill, long openServerTime, long lastResetTime, int cycleDay, int hour) {
		if (lastResetTime <= 0) {
			return true;
		}
		if (lastResetTime <= openServerTime) {
			return false;
		}
		LocalDateTime localOpenServerTime = convertLocalDateTime(openServerTime);
		LocalDateTime localLastResetTime = convertLocalDateTime(lastResetTime);
		LocalDateTime nextRefreshTime = LocalDateTime.of(localLastResetTime.toLocalDate(), LocalTime.of(hour, 0));

		int intervalDay = getDays(localOpenServerTime, localLastResetTime);
		long l = intervalDay % cycleDay;
		if (l > 0) { // 如果不在刷新周期那一天就获取下一个刷新周期
			nextRefreshTime = nextRefreshTime.plusDays(cycleDay - l);
		} else {// 在刷新周期那一天，但是这个刷新周期已经刷新过了;要获取下一个周期的刷新时间
			if (localLastResetTime.getHour() >= nextRefreshTime.getHour()) {
				nextRefreshTime = nextRefreshTime.plusDays(cycleDay);
			}
		}

		return currMill >= toEpochMilli(nextRefreshTime);
	}

	/**
	 * 判断是否可以刷新(每小时刷新)
	 *
	 * @param lastResetTime 上一次的刷新时间
	 * @param refreshMinute 每小时几分钟刷新
	 * @return true:刷新 false: 不刷新
	 */
	public static boolean isResetByHour(long currMill, long lastResetTime, int refreshMinute) {
		LocalDateTime resetTime = convertLocalDateTime(lastResetTime);

		LocalDateTime refreshTime = LocalDateTime.of(resetTime.toLocalDate(), LocalTime.of(resetTime.getHour(), refreshMinute));
		// 根据上一次的刷新时间点 获取下一次刷新的时间点
		if (resetTime.getMinute() >= refreshTime.getMinute()) {
			refreshTime = refreshTime.plusHours(1);
		}
		return currMill >= toEpochMilli(refreshTime);
	}
	public static boolean isResetByDay(long lastResetTime, int refreshHour) {
		return isResetByDay(System.currentTimeMillis(), lastResetTime, refreshHour);
	}
	/**
	 * 判断是否可以刷新(每天刷新)
	 *
	 * @param lastResetTime 上一次的刷新时间
	 * @param refreshHour   每天几点刷新
	 * @return true:刷新 false: 不刷新
	 */
	public static boolean isResetByDay(long currTime, long lastResetTime, int refreshHour) {
		long nextResetTimeMill = findNextResetTime(lastResetTime, refreshHour);
		return currTime > nextResetTimeMill;
	}


	public static boolean isResetByWeek(long lastResetTime, int refreshWeek, int refreshHour) {
		return isResetByWeek(System.currentTimeMillis(),lastResetTime,refreshWeek,refreshHour);
	}
	/**
	 * 判断是否可以刷新(每周刷新)
	 *
	 * @param lastResetTime 上一次的刷新时间
	 * @param refreshWeek   每周星期几刷新
	 * @param refreshHour   几点刷新
	 * @return true:刷新 false: 不刷新
	 */
	public static boolean isResetByWeek(long currTime, long lastResetTime, int refreshWeek, int refreshHour) {
		LocalDateTime resetTime = convertLocalDateTime(lastResetTime);

		// 根据上一次的刷新时间点 获取下一次刷新的时间点
		LocalDate nextResetDate = resetTime.toLocalDate().with(ChronoField.DAY_OF_WEEK, refreshWeek);
		int resetWeek = resetTime.get(ChronoField.DAY_OF_WEEK);
		int resetHour = resetTime.getHour();
		if (resetWeek > refreshWeek || (resetWeek == refreshWeek && resetHour >= refreshHour)) {
			nextResetDate = nextResetDate.plusWeeks(1);
		}
		LocalDateTime nextResetTime = LocalDateTime.of(nextResetDate, LocalTime.MIN.plusHours(refreshHour));

		return currTime > toEpochMilli(nextResetTime);
	}

	public static long findNextResetTime(long lastResetTime, int refreshHour) {
		return findNextResetTime(lastResetTime, refreshHour, 0);
	}
	/**
	 * 获取下一次刷新时间
	 *
	 * @param lastResetMill 上一次的刷新时间
	 * @param refreshHour   每天几点刷新
	 * @return true:刷新 false: 不刷新
	 */
	public static long findNextResetTime(long lastResetMill, int refreshHour, int refreshMinute) {
		LocalDateTime lastResetTime = convertLocalDateTime(lastResetMill);
		LocalTime refreshTime = LocalTime.of(refreshHour, refreshMinute);

		// 根据上一次的刷新时间点 获取下一次刷新的时间点
		LocalDate nextResetDate = lastResetTime.toLocalDate();
		if (lastResetTime.toLocalTime().compareTo(refreshTime) >= 0) {
			nextResetDate = nextResetDate.plusDays(1);
		}
		LocalDateTime nextResetTime = LocalDateTime.of(nextResetDate, refreshTime);
		return toEpochMilli(nextResetTime);
	}

	public static LocalTime convertLocalTime(long timestamp) {
		return convertLocalDateTime(timestamp).toLocalTime();
	}


	public static LocalDate convertLocalDate(long timestamp) {
		return convertLocalDateTime(timestamp).toLocalDate();
	}


	public static LocalDateTime convertLocalDateTime(long timestamp) {
		Instant instant = Instant.ofEpochMilli(timestamp);
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);
		return zonedDateTime.toLocalDateTime();
	}

	public static long toEpochMilli(LocalDateTime localDateTime) {
		return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
	}



	public static void main(String[] args) throws Exception {
		/*String iso8601Timestamp = getISO8601Timestamp(TimeUtil.getTime());
		System.out.println(iso8601Timestamp);*/

		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(getDayOfWeek(localDateTime));
		System.out.println(getDayOfWeek(localDateTime.plusDays(1)));
		System.out.println(getDayOfWeek(localDateTime.plusDays(2)));
		System.out.println(getDayOfWeek(localDateTime.plusDays(3)));
		System.out.println(getDayOfWeek(localDateTime.plusDays(4)));
		System.out.println(getDayOfWeek(localDateTime.plusDays(5)));
		//testIsResetByOpenServerTime();
	/*	LocalTime of = LocalTime.of(20, 10);
		LocalDateTime localDateTime = of.atDate(LocalDate.now());
		long nextBornTime = toEpochMilli(localDateTime);
		long nextResetTime = findNextResetTime(nextBornTime, 20, 0);
		System.out.println(new Date(nextResetTime));
		long nextResetTime1 = findNextResetTime(nextBornTime, 20, 20);
		System.out.println(new Date(nextResetTime1));
		String s = formatMill(System.currentTimeMillis());
		System.out.println(s);*/

	/*	LocalTime of = LocalTime.of(16, 20);
		LocalDateTime localDateTime = of.atDate(LocalDate.now());
		long nextBornTime = toEpochMilli(localDateTime);*/
		/*System.out.println(isResetByHour(nextBornTime, 19));
		System.out.println(isResetByHour(nextBornTime, 20));
		System.out.println(isResetByHour(nextBornTime, 21));
		System.out.println(isResetByHour(nextBornTime, 22));*/
	}

	private static void testIsResetByOpenServerTime() {
		// 测试不同日期
		LocalDateTime openServerTime = LocalDateTime.of(2022, 1, 1, 3, 5);
		LocalDateTime lastRestTime = LocalDateTime.of(2022, 1, 2, 4, 5);
		LocalDateTime currTime = LocalDateTime.of(2022, 1, 4, 3, 5);
		isResetByOpenServerTime(toEpochMilli(currTime), toEpochMilli(openServerTime), toEpochMilli(lastRestTime), 3, 3);

		// 测试相同日期 不同小时
		openServerTime = LocalDateTime.of(2022, 1, 1, 3, 5);
		lastRestTime = LocalDateTime.of(2022, 1, 1, 4, 5);
		currTime = LocalDateTime.of(2022, 1, 4, 3, 5);
		isResetByOpenServerTime(toEpochMilli(currTime), toEpochMilli(openServerTime), toEpochMilli(lastRestTime), 3, 3);


		openServerTime = LocalDateTime.of(2022, 1, 1, 3, 5);
		lastRestTime = LocalDateTime.of(2022, 1, 3, 2, 5);
		currTime = LocalDateTime.of(2022, 1, 4, 3, 5);
		isResetByOpenServerTime(toEpochMilli(currTime), toEpochMilli(openServerTime), toEpochMilli(lastRestTime), 3, 3);
	}

}
