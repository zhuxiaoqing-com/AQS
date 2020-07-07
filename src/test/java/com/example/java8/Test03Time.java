package com.example.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Test03Time {
	@Test
	public void test01() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime parse = LocalTime.of(4, 22);
		System.out.println(parse);
	}

	@Test
	public void test02() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String s = "24:00:00";
		TemporalAccessor parse1 = dateTimeFormatter.parse(s);
		LocalTime parse = LocalTime.parse(s);
		System.out.println(parse);
	}

	@Test
	public void test03() {
		// LocalDateTime 转时间戳
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
		long nextBornTime = zonedDateTime.toInstant().toEpochMilli();

		// 时间戳转 localDataTime localData LocalTime
		Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
		ZonedDateTime zonedDateTime1 = instant.atZone(ZoneId.systemDefault());
		LocalDateTime localDateTime2 = zonedDateTime1.toLocalDateTime();

		// LocalDate 转 LocalDateTime
		LocalDateTime localDateTime1 = LocalDate.now().atStartOfDay();
		LocalDateTime localDateTime3 = LocalDate.now().atTime(LocalTime.now());

		// LocalTime 转 LocalDateTime
		LocalDateTime localDateTime5 = LocalTime.now().atDate(LocalDate.now());
		System.out.println(localDateTime5);

	}

	@Test
	public void test04() {
		LocalDateTime now = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalTime localTime = now.toLocalTime();

		LocalTime localTime1 = localTime.plusMinutes(111);
		System.out.println(localTime1);
	}

	@Test
	public void test05() {
		LocalDate openServerDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate with = openServerDate.with(ChronoField.DAY_OF_WEEK, 1);
		System.out.println(with);
	}

	@Test
	public void test06() {
		//localDate 转换为时间戳
		LocalDate localDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
		long l = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		System.out.println(new Date(l));
	}

	@Test
	public void test07() {
		// 获取两天像相隔天数
		int refreshHour = 10;
		LocalDateTime o1 = LocalDateTime.of(2020, 7, 7, 10, 0,  3);
		LocalDateTime o2 = LocalDateTime.of(2020, 7, 7, 11, 0,  3);
		int distance = getIntervalDayByHour(toConvert(o1), toConvert(o2), refreshHour);
		System.out.println(distance);

		 o1 = LocalDateTime.of(2020, 7, 6, 10, 0,  3);
		 o2 = LocalDateTime.of(2020, 7, 7, 11, 0,  3);
		 distance = getIntervalDayByHour(toConvert(o1), toConvert(o2), refreshHour);
		System.out.println(distance);

		o1 = LocalDateTime.of(2020, 7, 7, 11, 0,  3);
		o2 = LocalDateTime.of(2020, 7, 7, 11, 0,  3);
		distance = getIntervalDayByHour(toConvert(o1), toConvert(o2), refreshHour);
		System.out.println(distance);

	}

	/**
	 * 获取两天相隔时间 以 refreshHour 为一天过去的基准
	 *
	 * 例子：以10点为基准  2020-7-7 10:22 和 2020-7-7 11:22 相差0天
	 * 例子：以11点为基准  2020-7-7 10:22 和 2020-7-7 11:22 相差1天
	 * @return  endTime > startTime return +day;  endTime < startTime return -day
	 */
	public int getIntervalDayByHour(long startTime, long endTime, int refreshHour) {
		LocalDateTime startDateTime = Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDate satartDate = startDateTime.toLocalDate();
		if (startDateTime.getHour() < refreshHour) {
			satartDate = satartDate.minusDays(1);
		}
		LocalDateTime amendStartDateTime = satartDate.atStartOfDay().withHour(refreshHour);

		LocalDateTime endDateTime = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDate endlocalDate = endDateTime.toLocalDate();
		if (endDateTime.getHour() < refreshHour) {
			endlocalDate = endlocalDate.minusDays(1);
		}
		LocalDateTime amendEndDateTime = endlocalDate.atStartOfDay().withHour(refreshHour);

		long startMill = amendStartDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		long endMill = amendEndDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		return (int) ((endMill - startMill)/1000/60/60/24);
	}

	private long toConvert(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
}
















