package com.example.demo4;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Test03 {
    @Test
    public void test01() {
        Object innerObj = getInnerObj();
        System.out.println("2:.." + innerObj);
        System.out.println("4:.." + innerObj.toString());
        new HashMap(new HashMap());
        // Integer.valueOf()
    }

    private Object getInnerObj() {
        final int x = 3;

        class InnerObj {
            public InnerObj() {
                System.out.println("1:.." + x);
            }

            @Override
            public int hashCode() {
                System.out.println("3:.." + x);
                return x;
            }
        }

        return new InnerObj();
    }

    @Test
    public void test02() {
        long milli = 1000;
        System.out.println(30 * 60 * 1000);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(90);
        Socket accept = serverSocket.accept();
        Scanner scan = new Scanner(System.in);
        String next = scan.next();
        System.out.println(next);
        accept.close();
    }

    @Test
    public void test03() throws IOException {
        System.out.println(new Date(1556559030832L));
        System.out.println(new Date(1556571600000L));
        System.out.println(System.currentTimeMillis());

    }

    long localHour = 60L * 60 * 1000;
    long localDay = 24 * localHour;
    long nowTimeMill;

    @Test
    public void test04() {
        int nowHour = 20;
        nowTimeMill = LocalDateTime.of(2019, 5, 5, nowHour, 1, 0, 0)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        int refreshHour = 19;
        System.out.println(!isTodaySplitByHour(nowTimeMill - localHour, refreshHour));// 前 1 个小时刷新过
        System.out.println(!isTodaySplitByHour(nowTimeMill - localHour * 2, refreshHour));// 前 2 个小时刷新过
        System.out.println(!isTodaySplitByHour(nowTimeMill - localHour * 12, refreshHour));// 前 12 个小时刷新过
        System.out.println(!isTodaySplitByHour(nowTimeMill - localHour * 29, refreshHour));// 前 29 个小时刷新过
    }


    public boolean isTodaySplitByHour(long time, int hour) {
        if (time > nowTimeMill) {
            return true;
        }
        LocalDateTime resetDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN.plusHours(hour));
        long resetTime = resetDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (resetTime - time > localHour * 24) {
            return false;
        }

        return ((time - resetTime) * (nowTimeMill - resetTime)) >= 0;
    }


    public boolean isResetByDay(long lastResetTime, int hour) {
        LocalDateTime resetTime = Instant.ofEpochMilli(lastResetTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
        ;
        LocalDateTime localNextResetTime = LocalDateTime.of(resetTime.toLocalDate(), LocalTime.MIN.plusHours(hour));

        int resetHour = resetTime.getHour();
        if (resetHour >= hour) {
            localNextResetTime = localNextResetTime.plusDays(1);
        }
        long nextResetTime = localNextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTime = System.currentTimeMillis();
        return nowTime > nextResetTime;
    }

    public boolean isResetByWeek(long lastResetTime, int day, int hour) {
        LocalDateTime localResetTime = Instant.ofEpochMilli(lastResetTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localNextResetTime = localResetTime.with(ChronoField.DAY_OF_WEEK, day);
        localNextResetTime = LocalDateTime.of(localNextResetTime.toLocalDate(), LocalTime.MIN.plusHours(hour));

        int resetWeekDay = localResetTime.get(ChronoField.DAY_OF_WEEK);
        int resetHour = localResetTime.getHour();

        if (resetWeekDay > day || (resetWeekDay == day && resetHour >= hour)) {
            localNextResetTime = localNextResetTime.plusWeeks(1);
        }
        long nextResetTime = localNextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTime = System.currentTimeMillis();
        return nowTime > nextResetTime;
    }


    public boolean isResetByDay1(long lastResetTime, int refreshHour) {
        LocalDateTime resetTime = Instant.ofEpochMilli(lastResetTime).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDate nextResetDate = resetTime.toLocalDate();
        if (resetTime.getHour() >= refreshHour) {
            nextResetDate = nextResetDate.plusDays(1);
        }

        LocalDateTime nextResetTime = LocalDateTime.of(nextResetDate, LocalTime.MIN.plusHours(refreshHour));
        long nextResetTimeMill = nextResetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long nowTimeMill = System.currentTimeMillis();
        return nowTimeMill > nextResetTimeMill;
    }

    public boolean isResetByWeek1(long lastResetTime, int refreshWeek, int refreshHour) {
        LocalDateTime resetTime = Instant.ofEpochMilli(lastResetTime).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDate nextResetDate = resetTime.toLocalDate().with(ChronoField.DAY_OF_WEEK, refreshWeek);
        // ChronoUnit
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


    @Test
    public void testDayRefresh06() {
        int nowHour = 20;
        nowTimeMill = LocalDateTime.of(2019, 5, 6, nowHour, 1, 0, 0)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        int previousRefreshHour = 19;
        int nextRefreshHour = 21;
        System.out.println("前面： " + isResetByDay1(nowTimeMill - localHour, previousRefreshHour));// 前 1 个小时刷新过
        System.out.println("后面： " + isResetByDay1(nowTimeMill - localHour, nextRefreshHour));// 前 1 个小时刷新过

        System.out.println("前面： " + isResetByDay1(nowTimeMill - localHour * 2, previousRefreshHour));// 前 2 个小时刷新过
        System.out.println("后面： " + isResetByDay1(nowTimeMill - localHour * 2, nextRefreshHour));// 前 2 个小时刷新过

        System.out.println("前面： " + isResetByDay1(nowTimeMill - localHour * 12, previousRefreshHour));// 前 12 个小时刷新过
        System.out.println("后面： " + isResetByDay1(nowTimeMill - localHour * 12, nextRefreshHour));// 前 12 个小时刷新过

        System.out.println("前面： " + isResetByDay1(nowTimeMill - localHour * 29, previousRefreshHour));// 前 29 个小时刷新过
        System.out.println("后面： " + isResetByDay1(nowTimeMill - localHour * 29, nextRefreshHour));// 前 29 个小时刷新过
    }

    @Test
    public void testWeekRefresh06() {
        int nowHour = 20;
        // 初始化为星期3
        nowTimeMill = LocalDateTime.of(2019, 5, 6, nowHour, 1, 0, 0)
                .with(ChronoField.DAY_OF_WEEK, 3)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();


        int day = 2;
        int previousRefreshHour = 21;
        int nextRefreshHour = 20;
        System.out.println("前面： " + isResetByWeek1(nowTimeMill, day, previousRefreshHour));// 今天刷新过

        System.out.println("前面： " + isResetByWeek1(nowTimeMill - localDay, day, previousRefreshHour));// 这个星期 二 刷新过

        System.out.println("前面： " + isResetByWeek1(nowTimeMill - localDay * 2, day, previousRefreshHour));// 这个星期 1 刷新过

        System.out.println("前面： " + isResetByWeek1(nowTimeMill - localDay * 3, day, previousRefreshHour));// 上个星期 7 刷新过

        System.out.println("前面： " + isResetByWeek1(nowTimeMill - localDay * 6, day, previousRefreshHour));// 上个星期 4 刷新过

        System.out.println("前面： " + isResetByWeek1(nowTimeMill - localDay * 7, day, previousRefreshHour));// 上个星期 3 刷新过
    }


    @Test
    public void test05() {
        System.out.println(new Date(1557316372841L));
    }

    @Test
    public void test06() {
        HashMap map = new HashMap() {
            {
                put(1, 1);
                put(2, 2);
                put(3, 3);
            }
        };

        HashSet<Object> objects = new HashSet<Object>(map.keySet());
        System.out.println(objects);

    }

    @Test
    public void test07() {
        Runtime.getRuntime().halt(0);
        System.exit(0);
        Assert.assertEquals(2, 4);
        int s = 4;
        System.out.println(Integer.toBinaryString(s));
        System.out.println(Integer.toBinaryString(s << 1));
    }

    @Test
    public void test08() {
        long s = 1L << 32;
        System.out.println(s);
        System.out.println(s / 1024 / 1024 / 1024);
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> integers = list.subList(1, 1);
        System.out.println(integers);
    }

    @Test
    public void test09() {
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(-0));
    }

    @Test
    public void test10() {
        int sum = Integer.MAX_VALUE;
        System.out.println(sum);
//        System.out.println(sum - Integer.MAX_VALUE);
//        System.out.println((sum - 1) == Integer.MAX_VALUE - 1);
    }


    @Test
    public void test11() {
        String json = "";
        Type mtype = new TypeToken<Map<String, List<GameServer>>>() {
        }.getType();

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Map<String, List<GameServer>> map = gson.fromJson(json, mtype);
        map.forEach((key, value) -> {

        });
    }

    @Test
    public void test12() {
        byte[] bytes = {103, 109, 95, 119, 101, 98, 58, 112, 108, 97, 116, 102, 111, 114, 109};
        String s = new String(bytes);
        System.out.println(s);

        String ip = "192.168.5.83";
        Jedis jedis = new Jedis(ip, 6379);
    }

    @Test
    public void test13() {
        int y = 1000;
        int x = 100;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < x; i++) {
          /*  if (i % 10 == 0) {
                y++;
            }*/
            list.add(0);
        }
        list.add(-1);
        list.add(-2);
        list.add(2);
        list.add(1);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
        list.sort((o1, o2) -> {
            return o1 > o2 ? 1 : -1;
        });
        System.out.println(list);
    }


    @Test
    public void test14() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= 100; i++) {
            map.put(i, i);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            if (key == 0) {
                map.put(101, 101);
            }
            if (key == 50) {
                map.put(102, 102);
            }
            if (key == 100) {
                map.put(103, 103);
            }
        }
    }

    @Test
    public void test15() {
        String s = "ASDF!@#$%^as)_df3f4uiiu(%&^GM";
        String ss = "AS*DF!@#$%^as)_df3f4uiiu*(%&^GM";
        System.out.println(s.equals(ss));
    }


    @Test
    public void test16() {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.binarySearch(list, 1);
    }

    @Test
    public void test17() {
        //todo s
        // fixme
        //
        int x1 = -21 / 4;
        /**    1 0000 0000
         * -21 = 1001 0101 = 1110 1011
         * -21 >> 2 = 1111 1010
         * 1 0000 0000 - 1111 1010 = 1000 0110 = 6
         */
        // 所有负数右移必须特别注意！！！ -21/4 和 -21>>2 是不同的
        int x2 = -21 >> 2;
        int x3 = 21 >> 2;
        System.out.println(x1);// -5 想上取正
        System.out.println(Integer.toBinaryString(-21));
        System.out.println(x2);// -6 向下取正
        System.out.println(x3);// -6 向下取正
    }

    /**
     * 小数 / 0 不会抛出  java.lang.ArithmeticException: / by zero
     */
    @Test
    public void test18() {
        double s = 1.2;
        double v = s / -0;
        System.out.println(v);

        System.out.println(v * 1);
        System.out.println(v - 0.2);
        System.out.println(v + 0.3);

        int ss = 1;
        System.out.println(ss / 0);
    }


    /**
     *
     */
    @Test
    public void test19() {
        double s = 0.1f;
        int const1 = 1;
        double ss = s * const1;

        System.out.println(s);
        System.out.println(s * const1);
        System.out.println(ss);
    }

    @Test
    public void test20() {
        //todo s
        // fixme
        //
        double x = 0.1;
        int x1 = 1;
        double sum = x * x1;
        System.out.println(sum);
    }

    @Test
    public void test21() {
        //todo s
        // fixme
        //
        double x1 = 2.0;
        doubleSum(x1);
    }

    private void doubleSum(double decimal) {
        double x = 1.1;
        double sum = x * decimal;
        System.out.println(sum);
        System.out.println(0.1 + 0.2);
        System.out.println(1f * 0.2);
    }

    @Test
    public void test22() {
        long rid = 2000_000_000L;
        System.out.println(rid*7f/222222);
        float v = rid * 7f;
        System.out.println(v);
        System.out.println(v/222222);
        long l = rid / 222222;
        System.out.println(l);
        System.out.println(l*7f);
    }


    private static boolean isWin(int power1, int power2) {
        // 低的一方的战斗力 与 高的一方的战斗力 相差 5%以上算输，以内随机
        int percent = (int) ((power1 * 100L) / power2);
        int diffPercent = Math.abs(100 - percent);
        if (diffPercent <= 5) {
            // 随机
            int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
            return randomInt == 0;
        }
        return percent > 100;

    }

    @Test
    public void test23() {
        int power2 = 200_000_000;
        int power1 = 220_000_000;
        boolean win = isWin(power1, power2);
        System.out.println(win);
    }


    @Test
    public void test24() {
        Date date = new Date(1561104600000L);
        System.out.println(date);
        int i = 3;
        switch(i) {
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            case 4:
                System.out.println(4);
        }
    }

}












