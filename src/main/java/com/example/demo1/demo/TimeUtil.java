package com.example.demo1.demo;

import com.example.demo1.util.FileUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TimeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtil.class);

    /**
     * 一分钟的秒时长
     */
    public static final long ONE_MINUTE_IN_SECONDS = 60L ;

    /**
     * 一小时的秒时长
     */
    public static final int ONE_HOUR_IN_SECONDS = 60 * 60;

    /**
     * 一分钟的毫秒时长
     */
    public static final long ONE_MINUTE_IN_MILLISECONDS = 60L * 1000;
    /**
     * 一小时的毫秒时长
     */
    public static final long ONE_HOUR_IN_MILLISECONDS = 60L * ONE_MINUTE_IN_MILLISECONDS;
    /**
     * 一天的毫秒时长
     */
    public static final long ONE_DAY_IN_MILLISECONDS = 24L * ONE_HOUR_IN_MILLISECONDS;
    /**
     * 一天的秒时长
     */
    public static final long ONE_DAY_IN_SECONDS = 24L * 60 * 60;

    /**
     * 1秒的时长
     */
    public static final long ONE_MILLS = 1000L;

    /**
     * 一周的毫秒时长
     */
    public static final long ONE_WEEK_IN_MILLISECONDS = 7 * ONE_DAY_IN_MILLISECONDS;

    /**
     * 2015-02-23 12:12:12格式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);

    /**
     * 2019-01-02 格式
     */
    public static final String DEFAULT_DAY_FORMAT = "yyyy-MM-dd";
    public static DateTimeFormatter SIMPLE_DAY_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_DAY_FORMAT);


    /**
     * 2019010221 格式
     */
    public static final String DEFAULT_DAY_HOUR_FORMAT = "yyyyMMddHH";
    public static DateTimeFormatter SIMPLE_DAY_HOUR_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_DAY_HOUR_FORMAT);

    /**
     * 判断两个时间是否是同一天
     *
     * @param sourceTime
     * @param targetTime
     * @return
     */
    public static boolean isSameDay(long sourceTime, long targetTime) {


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sourceTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long sourceTimeZero = calendar.getTimeInMillis();

        calendar.setTimeInMillis(targetTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long targetTimeZero = calendar.getTimeInMillis();

        return sourceTimeZero == targetTimeZero;
    }

    /**
     * 判断指定的时间是否是今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        return isTodaySplitByHour(time, 0);
    }


    /**
     * 是否为今天
     *
     * @param millis
     * @return
     */
    public static boolean isTodayNew(long millis) {
        Calendar today = Calendar.getInstance();
        Calendar compareday = Calendar.getInstance();
        compareday.setTimeInMillis(millis);
        if (today.get(Calendar.YEAR) == compareday.get(Calendar.YEAR) && today.get(Calendar.DAY_OF_MONTH) == compareday.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }


    /**
     * 根据指定小时划分，判断是否同一天
     *
     * @param time
     * @param hour
     * @return
     */
    public static boolean isTodaySplitByHour(long time, int hour) {
        long nowTime = System.currentTimeMillis();
        if (time > nowTime) {
            return true;
        }
        LocalDateTime resetDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN.plusHours(hour));
        long resetTime = resetDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        return ((time - resetTime) * (nowTime - resetTime)) >= 0;
    }

    /**
     * 判断传入的时间是否是同年同月
     *
     * @return
     */
    public static boolean isTheSameMonth(long targetTime, long sourceTimes) {
        Calendar calendarTarget = Calendar.getInstance();
        calendarTarget.setTimeInMillis(targetTime);
        Calendar calendarSource = Calendar.getInstance();
        calendarSource.setTimeInMillis(sourceTimes);
        return calendarTarget.get(Calendar.YEAR) == calendarSource.get(Calendar.YEAR) && calendarTarget.get(Calendar.MONTH) == calendarSource.get(Calendar.MONTH);
    }

    /**
     * 获取当前时间在当月是第几天
     *
     * @return
     */
    public static int getTodayInMonth() {
        int dayindex = getTheDayInMonth(new Date());
        return dayindex;
    }

    /**
     * 获取指定日期在当月是第几天
     *
     * @param date
     * @return
     */
    public static int getTheDayInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定时间在当月是第几天
     *
     * @param times
     * @return
     */
    public static int getTheDayInMonthForTimes(long times) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(times);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当月的第n天是星期几
     *
     * @param dayIndex
     * @return
     */
    public static int getTheDayInMonthForWeek(int dayIndex) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dayIndex - 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取这周是当月的第几周
     * (设置周一为每周的第一天)
     *
     * @param dayIndex
     * @return
     */
    public static int getTheWeekInMonthForDay(int dayIndex) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_MONTH, dayIndex);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }


    /**
     * 获取两个时间的逻辑间隔天数,以源时间为基准,目标时间小于源时间则返回大于或等于天数，反之返回小于等于天数
     * <p>
     * 举例：hour=5,sourceTime=今天凌晨4点59分59秒,targetTime=昨天晚上5点0分1秒,则返回1
     *
     * @param sourceTime
     * @param targetTime
     * @param hour
     * @return
     */
    public static int getLogicIntervalDaysByHour(long sourceTime, long targetTime, int hour) {
        long sourceHourClockTime = getHourClockTime(sourceTime, hour);
        long targetHourClockTime = getHourClockTime(targetTime, hour);

        return getRealIntervalDays(sourceHourClockTime, targetHourClockTime);
    }

    /**
     * 获取两个时间的逻辑间隔天数,以源时间为基准,目标时间小于源时间则返回大于或等于天数，反之返回小于等于天数
     * <p>
     * 举例：sourceTime=今天凌晨0点0分1秒,targetTime=昨天晚上11点59分59秒,则返回1
     *
     * @param sourceTime
     * @param targetTime
     * @return
     */
    public static int getLogicIntervalDays(long sourceTime, long targetTime) {
        long source0ClockTime = getZeroClockTime(sourceTime);
        long target0ClockTime = getZeroClockTime(targetTime);

        return getRealIntervalDays(source0ClockTime, target0ClockTime);
    }

    /**
     * 获取两个时间的实际间隔天数
     *
     * @param sourceTime
     * @param targetTime
     * @return
     */
    public static int getRealIntervalDays(long sourceTime, long targetTime) {
        return (int) getIntervalTime(sourceTime, targetTime, ONE_DAY_IN_MILLISECONDS);
    }

    /**
     * 根据指定的时间单位获取相差的单位时间，如时间单位为一天的毫秒数则该函数跟{@link#getRealIntervalDays} 则是相同的效果
     *
     * @param sourceTime
     * @param targetTime
     * @param timeUnit   时间单位(毫秒)
     * @return
     */
    public static long getIntervalTime(long sourceTime, long targetTime, long timeUnit) {
        return (sourceTime - targetTime) / timeUnit;
    }

    /**
     * 获取在指定时间戳和指定小时，分钟，秒，毫秒数的时间
     *
     * @param time        时间戳
     * @param hour        小时(24小时制)
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     * @return
     */
    public static long getTimeInMillis(long time, int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        return calendar.getTimeInMillis();
    }

    public static long getTimeInMillis(long time, int hour, int minute) {
        return getTimeInMillis(time, hour, minute, 0, 0);
    }

    public static long getTimeInMillis(long time, int hour) {
        return getTimeInMillis(time, hour, 0, 0, 0);
    }

    /**
     * 获取指定日期的时间戳
     *
     * @param year
     * @param month       从1开始
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param milliSecond
     * @return
     */
    public static long getTimeInMillis(int year, int month, int day, int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        return calendar.getTimeInMillis();
    }

    public static long getNowOfMills() {
        return System.currentTimeMillis();
    }

    public static int getNowOfSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static int getNowOfMinutes() {
        return TimeUtil.getNowOfSeconds() / 60;
    }

    /**
     * 获取今日指定的时间
     *
     * @param hour        小时(24小时制)
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     * @return
     */
    public static long getTodayTime(int hour, int minute, int second, int milliSecond) {
        return getTimeInMillis(System.currentTimeMillis(), hour, minute, second, milliSecond);
    }

    /**
     * 获取指定时间的零点时间
     *
     * @param time
     * @return
     */
    public static long getZeroClockTime(long time) {
        return getTimeInMillis(time, 0, 0, 0, 0);
    }

    /**
     * 获取次日0点时间
     * @return
     */
    public static long getZeroTomorrow() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, 1);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTimeInMillis();
    }

    /**
     * 获取指定时间的整点时间
     *
     * @param time
     * @return
     */
    public static long getHourClockTime(long time, int hour) {
        //往前挪hour小时
        time -= hour * ONE_HOUR_IN_MILLISECONDS;
        return getTimeInMillis(time, hour, 0, 0, 0);
    }




    /**
     * 返回指定时间和格式的时间字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String getTimeString(long time, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time));
    }

    /**
     * 从字符串中获取时间
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static long getTimeFromString(String timeStr, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(timeStr).getTime();
        } catch (ParseException e) {
            LOGGER.error("", e);
        }
        return Long.MIN_VALUE;
    }

    /**
     * 获取格式化的剩余时间
     * <p>
     * 例如:1天20小时5分0秒,20小时0分0秒,1秒
     *
     * @param leftTime
     * @return
     */
    public static String getLeftTimeString(long leftTime) {
        StringBuilder sb = new StringBuilder();
        // int leftSecond = (int) (leftTime / 1000);// 剩余秒数
        // int second = leftSecond % 60;// 秒数
        // if (second > 0) {
        // sb.insert(0, second + "秒");
        // }
        // int leftMinute = leftSecond / 60;// 剩余分钟数
        // int minute = leftMinute % 60;// 分钟数
        // if (minute > 0) {
        // sb.insert(0, minute + "分");
        // }
        // int leftHour = leftMinute / 60;// 剩余小时
        // int hour = leftHour % 24;
        // if (hour > 0) {
        // sb.insert(0, hour + "小时");
        // }
        // int leftDay = leftHour / 24;// 剩余天数
        // if (leftDay > 0) {
        // sb.insert(0, leftDay + "天");
        // }
        int day = (int) (leftTime / ONE_DAY_IN_MILLISECONDS);// 获取剩余天数
        if (day > 0) {// 1天及以上的显示剩余天
            sb.append(day).append("天");
            leftTime -= (day * ONE_DAY_IN_MILLISECONDS);
        }
        int hour = (int) (leftTime / ONE_HOUR_IN_MILLISECONDS);
        if (hour > 0 || sb.length() > 0) {// 1小时及以上或者前面显示了天数则后面需要小时
            sb.append(hour).append("小时");
            leftTime -= (hour * ONE_HOUR_IN_MILLISECONDS);
        }
        int minute = (int) (leftTime / ONE_MINUTE_IN_MILLISECONDS);
        if (minute > 0 || sb.length() > 0) {
            sb.append(minute).append("分");
            leftTime -= (minute * ONE_MINUTE_IN_MILLISECONDS);
        }
        sb.append(leftTime / 1000).append("秒");
        return sb.toString();
    }

    /**
     * 判断两个时间是否是在同一个周期内
     *
     * @param sourceTime
     * @param targetTime
     * @param baseTime   基于判断的初始时间
     * @param cycleDays  周期天数
     * @return
     */
    public static boolean isSameCycle(long sourceTime, long targetTime, long baseTime, int cycleDays) {
        int sourceCycle = TimeUtil.getLogicIntervalDays(sourceTime, baseTime) / cycleDays;
        int targetCycle = TimeUtil.getLogicIntervalDays(targetTime, baseTime) / cycleDays;
        return sourceCycle == targetCycle;
    }

    /**
     * 判断今天是否为同一个月
     *
     * @return
     */
    public static boolean isSameMouth(int oldMonth) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month == oldMonth) {
            return true;
        }
        return false;
    }

    /**
     * 获得当前小时
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得当前是星期几
     * 1：星期一
     * 2：星期二
     * 3：星期三
     * 4：星期四
     * 5：星期五
     * 6：星期六
     * 7：星期七
     *
     * @return
     */
    public static int getCurrentDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    /**
     * 获取当前时间
     *
     * @return long
     */
    public static long getNow_long() {
        return System.currentTimeMillis();
    }

    /**
     * @return int
     */
    public static int getNow_int() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Time getNow_Time() {
        return new Time(System.currentTimeMillis());
    }


    public static String setDatetime(String date, String time) {
        String osName = System.getProperty("os.name");
        String dateTimeMessage = date + " " + time;
        try {
            if (osName.matches("^(?i)Windows.*$")) { // Window 系统
                String cmd;

                cmd = " cmd /c date " + date; // 格式：yyyy-MM-dd
                Runtime.getRuntime().exec(cmd);

                cmd = " cmd /c time " + time; // 格式 HH:mm:ss
                Process process = Runtime.getRuntime().exec(cmd);


            } else if (osName.matches("^(?i)Linux.*$")) {// Linux 系统
                Runtime.getRuntime().exec("date -s " + date);
                Runtime.getRuntime().exec("date -s " + time);
            }
        } catch (IOException e) {
            return e.getMessage();
        }
        return dateTimeMessage;
    }



    /**
     * 格式化日期
     *
     * @param time 时间戳
     * @return
     */
    public static String timeFormat(long time) {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return ldt.format(SIMPLE_DATE_FORMAT);
    }

    /**
     * 格式化日期
     *
     * @param time 时间戳
     * @return
     */
    public static String timeFormatDay(long time) {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return ldt.format(SIMPLE_DAY_FORMAT);
    }

    /**
     * 格式化日期
     * yyyyMMddHH
     * @param time 时间戳
     * @return
     */
    public static String timeFormatDayAndHour(long time) {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return ldt.format(SIMPLE_DAY_HOUR_FORMAT);
    }




    /**
     * 时间单位转换
     *
     * @param sourceUnit 源时间单位
     * @param targetUnit 目标时间单位
     * @param timeData   时间
     * @return
     */
    public static long timeChange(TimeUnit sourceUnit, TimeUnit targetUnit, long timeData) {
        return targetUnit.convert(timeData, sourceUnit);
    }

    /**
     * 是否同一周
     *
     * @param sourceTime 开始时间
     * @param targetTime 结束时间
     * @return 是否同一周
     */
    public static boolean isSameWeek(long sourceTime, long targetTime) {
        long startWeekTime = getWeekFirstDayZeroHourTimestamp(sourceTime);
        long endWeekTime = getWeekFirstDayZeroHourTimestamp(targetTime);
        return startWeekTime == endWeekTime;
    }




    /**
     * 判断是否可以刷新(每天刷新)
     *
     * @param lastResetTime 上一次的刷新时间
     * @param refreshHour   每天几点刷新
     * @return true:刷新 false: 不刷新
     */
    private static boolean isResetByDay(long lastResetTime, int refreshHour) {
        LocalDateTime resetTime = Instant.ofEpochMilli(lastResetTime).atZone(ZoneId.systemDefault()).toLocalDateTime();

        // 根据上一次的刷新时间点 获取下一次刷新的时间点
        LocalDate nextResetDate = resetTime.toLocalDate();
        if (resetTime.getHour() >= refreshHour) {
            nextResetDate = nextResetDate.plusDays(1);
        }
        LocalDateTime nextResetTime = LocalDateTime.of(nextResetDate, LocalTime.MIN.plusHours(refreshHour));

        long nextResetTimeMill = nextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTimeMill = System.currentTimeMillis();
        return nowTimeMill > nextResetTimeMill;
    }

    /**
     * 判断是否可以刷新(每周刷新)
     *
     * @param lastResetTime 上一次的刷新时间
     * @param refreshWeek   每周星期几刷新
     * @param refreshHour   几点刷新
     * @return true:刷新 false: 不刷新
     */
    private static boolean isResetByWeek(long lastResetTime, int refreshWeek, int refreshHour) {
        LocalDateTime resetTime = Instant.ofEpochMilli(lastResetTime).atZone(ZoneId.systemDefault()).toLocalDateTime();

        // 根据上一次的刷新时间点 获取下一次刷新的时间点
        LocalDate nextResetDate = resetTime.toLocalDate().with(ChronoField.DAY_OF_WEEK, refreshWeek);
        int resetWeek = resetTime.get(ChronoField.DAY_OF_WEEK);
        int resetHour = resetTime.getHour();
        if (resetWeek > refreshWeek || (resetWeek == refreshWeek && resetHour >= refreshHour)) {
            nextResetDate = nextResetDate.plusWeeks(1);
        }
        LocalDateTime nextResetTime = LocalDateTime.of(nextResetDate, LocalTime.MIN.plusHours(refreshHour));

        long nextResetTimeMill = nextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTimeMill = System.currentTimeMillis();
        return nowTimeMill > nextResetTimeMill;
    }



    /**
     * 获取修正时间
     *
     * @param now  时间搓
     * @param hour 修正小时点
     */
    public static long getFixTimeStamp(long now, int hour) {
        return toMillSecond(getFixTime(now, hour));
    }

    /**
     * 获取修正时间
     *
     * @param now  时间
     * @param hour 修正时间点
     */
    public static LocalDateTime getFixTime(long now, int hour) {
        Instant instant = Instant.ofEpochMilli(now);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        int currentHour = localDateTime.getHour();
        localDateTime.withHour(hour).withMinute(0).withSecond(0).withNano(0);
        if (currentHour < hour) {
            localDateTime = localDateTime.minusDays(1);
        }
        return localDateTime;
    }




    /**
     * 根据时间点获取这个月有几天
     *
     * @param time 时间点
     */
    public static int getMonthDays(long time) {
        Calendar a = Calendar.getInstance();
        a.setTimeInMillis(time);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return a.get(Calendar.DATE);
    }

    /**
     * 是否超过一星期
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public static boolean isMoreThanOneWeek(long startTime, long endTime) {
        return endTime - startTime >= ONE_WEEK_IN_MILLISECONDS;
    }


    /**
     * 根据时间点获取周一时间
     *
     * @param now  时间点
     * @param hour 修正小时
     */
    public static long getWeekFirstDayFixHour(long now, int hour) {
        LocalDateTime fixTime = getFixTime(now, hour);
        return toMillSecond(fixTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));
    }

    /**
     * 根据时间点获取每月第一天修正时间
     *
     * @param now  时间点
     * @param hour 修正小时
     */
    public static long getFirstMonthDayFixHour(long now, int hour) {
        LocalDateTime fixTime = getFixTime(now, hour);
        return toMillSecond(fixTime.with(TemporalAdjusters.firstDayOfMonth()));
    }

    /**
     * 根据时间点获取每星期最后一天0点时间
     *
     * @param now 时间点
     */
    public static long getLastWeekDayTimestamp(long now) {
        LocalDateTime fixTime = getFixTime(now, 0);
        return toMillSecond(fixTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)));
    }

    /**
     * 根据时间点获取每月最后一天0点时间
     *
     * @param now 时间点
     */
    public static long getLastMonthDayTimestamp(long now) {
        LocalDateTime fixTime = getFixTime(now, 0);
        return toMillSecond(fixTime.with(TemporalAdjusters.lastDayOfMonth()));
    }

    /**
     * 根据时间点获取每月第一天0点时间
     *
     * @param now 时间点
     */
    public static long getFirstMonthDayZeroHourTimestamp(long now) {
        return getFirstMonthDayFixHour(now, 0);
    }

    /**
     * 根据时间点获取周一0点时间
     *
     * @param now 时间点
     */
    public static long getWeekFirstDayZeroHourTimestamp(long now) {
        return getWeekFirstDayFixHour(now, 0);
    }



    /**
     * LocalDateTime转MillSecond
     */
    public static long toMillSecond(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * millSecond转LocalDateTime
     */
    public static LocalDateTime toLocalDate(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }


    /**
     * 获取当前时间精确到分钟的时间戳
     */
    public static long getNowTimeToMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
