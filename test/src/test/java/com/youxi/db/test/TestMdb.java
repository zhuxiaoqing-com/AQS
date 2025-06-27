package com.youxi.db.test;

import com.alibaba.fastjson.JSON;
import com.youxi.IdFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;


public class TestMdb {
    static Connection conn;

   /* String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test1?rewriteBatchedStatements=true";
    String user = "root";
    String password = "123";*/

    static String driverName = "com.mysql.jdbc.Driver";
    //static String url = "jdbc:mysql://10.0.0.105:3306/SECONDWAR_201?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;rewriteBatchedStatements=true&amp;user=root&amp;password=123456";
    static String url = "jdbc:mysql://10.0.0.74:3306/SECONDWAR401_401?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;rewriteBatchedStatements=true&amp;user=root&amp;password=123456";
    //static String url = "jdbc:mysql://localhost:3306/SECONDWAR_112?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;rewriteBatchedStatements=true&amp;user=root&amp;password=123456";
    static String user = "root";
    static String password = "123456";


    static {
        try {
            Class.forName(driverName);

            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        TestMdb testMdb = new TestMdb();
        //testMdb.caclTotalNeedMem();

        ArrayList<Long> objects = getPlayerLongList();

            testMdb.testHash(objects, 15, false, false);
            testMdb.testHash(objects, 15, true, false);
        int index = 20;
        for (int i = 1; i < index; i++) {
          //  testMdb.testHash(objects, i, true, false);
         //   testMdb.testHash(objects, i, false, false);
        }
    }

    private static ArrayList<Long> getPlayerLongList() throws SQLException {
        String sql = "SELECT * FROM " + "lguser";
        Statement statement = conn.createStatement();

        ArrayList<Long> objects = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String string = resultSet.getString(2);
            LgUser o = JSON.parseObject(string, LgUser.class);
            objects.addAll(o.getIds());
        }

        int zeroNum = 0;
        for (Long object : objects) {
            long l = object & (~(-1L << 15));
            if (l == 0) {
                zeroNum++;
            }
        }

        System.out.println("playerIdsSum   " + objects.size());
        System.out.println("playerIdsZeroNum(没有自增id的唯一id) ---  " + zeroNum);
        System.out.println("playerIdsNotZeroNum(有自增id的唯一id) ---  " + (objects.size() - zeroNum));
        System.out.println();
        System.out.println();
        return objects;
    }

    public void testHash(ArrayList<Long> objects, int threadNum, boolean hashMethod, boolean printDetail) throws SQLException {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        HashMap<Integer, Integer> zeroHashMap = new HashMap<>();
        HashMap<Integer, Integer> notZeroHashMap = new HashMap<>();

        for (int i = 0; i < threadNum; i++) {
            hashMap.put(i, 0);
            zeroHashMap.put(i, 0);
            notZeroHashMap.put(i, 0);
        }

        for (Long object : objects) {
            long l = object & (~(-1L << 15));
            long hashId = hashMethod ? IdFactory.hash(object) : object;
            //hashId = object;
            int i = (int) (hashId % threadNum);
            hashMap.merge(i, 1, Integer::sum);
            if (l == 0) {
                zeroHashMap.merge(i, 1, Integer::sum);
            } else {
                notZeroHashMap.merge(i, 1, Integer::sum);
            }
        }

        Integer zeroMax = zeroHashMap.values().stream().max(Comparator.comparingInt(a -> a)).orElse(0);
        Integer zeroMin = zeroHashMap.values().stream().min(Comparator.comparingInt(a -> a)).orElse(0);

        Integer notZeroMax = notZeroHashMap.values().stream().max(Comparator.comparingInt(a -> a)).orElse(0);
        Integer notZeroMin = notZeroHashMap.values().stream().min(Comparator.comparingInt(a -> a)).orElse(0);

        System.out.println();
        String print = "threadNum 【%s】 hashMethod【%s】   zeroMax-zeroMin 【%s】  notZeroMax-notZeroMin 【%s】";
        System.out.println(String.format(print, threadNum, hashMethod,(zeroMax - zeroMin), (notZeroMax - notZeroMin)));
        int limit = (int) (objects.size() * 0.1);
        if (zeroMax - zeroMin > limit || notZeroMax - notZeroMin > limit) {
            if (printDetail) {
                System.out.println("hashMap(总玩家统计hash)---  " + hashMap);
                System.out.println("zeroHashMap(没有自增id的玩家统计hash)---  " + zeroHashMap);
                System.out.println("notZeroHashMap(有自增id的玩家统计hash)---  " + notZeroHashMap);
            }
        }

    }

    private List<String> calcMaxMenData(String tableName) throws Exception {
        String sql = "SELECT * FROM " + tableName;
        Statement statement = conn.createStatement();

        int maxLength = 0;
        String maxK = "";
        String maxV = "";

        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String v = resultSet.getString(2);
            if (v.length() <= maxLength) {
                continue;
            }
            maxLength = v.length();
            maxK = resultSet.getString(1);
            maxV = v;
        }
        return Arrays.asList(maxK, maxV);
    }

    public void caclTotalNeedMem() throws Exception {
        List<String> allTableName = getAllTableName(conn);
        System.out.println("allTableName:  " + allTableName);
        System.out.println("allTableNameSize:  " + allTableName.size());

        List<String> lbTables = findLbTables();
        System.out.println("lbTables : " + lbTables);

        allTableName.removeAll(lbTables);
        System.out.println("lgTables : " + allTableName);

        ArrayList<KV<String, String>> jsonList = new ArrayList<>();
        for (String s : allTableName) {
            String value = selectTable(s);
            if (value != null) {
                jsonList.add(new KV<>(s, value));

            }
        }

        // 这里计算出了所有表的Json的大小  *2是因为 一个char二个字节
        long sum = jsonList.stream().mapToInt(a -> a.getV().length() * 2).sum();

        // 内存里面缓存了一份对象和对象的json
        sum = sum * 2;
        System.out.println("所有表加起来的平均数据--- " + sum);
        System.out.println("所有Lru 满的情况下需要的数据--- " + sum * 8192);// 38 848 069 632

        Map<String, Integer> collect = jsonList.stream().sorted(Comparator.<KV<String, String>>comparingInt(a -> a.getV().length()).reversed())
                .limit(10)
                .collect(Collectors.toMap(KV::getK, a -> a.getV().length(), (a, b) -> a, LinkedHashMap::new));

        System.out.println("数据库表数据量前十——————" + collect.keySet());
        System.out.println("数据库表数据量前十——————" + collect);
    }

    public static List<String> getAllTableName(Connection cnn) throws SQLException {
        List<String> tables = new ArrayList<>();
        DatabaseMetaData dbMetaData = cnn.getMetaData();

        String[] types = {"TABLE"};

        ResultSet tabs = dbMetaData.getTables(null, null, null, types/*只要表就好了*/);

        while (tabs.next()) {
            tables.add((String) tabs.getObject("TABLE_NAME"));
        }
        System.out.println(tables);
        return tables;
    }

/*	private KV<Integer, Integer> findTableClassMemory(String tableName) throws Exception {
		String s = selectTable(tableName);
		String tableClassName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
		Class<?> xbeanClass = findClassNameByTableName(tableClassName);

		return calcMemory(s, xbeanClass);
	}*/

/*	Object o;
	private KV<Integer, Integer> calcMemory(String s, Class<?> xbeanClass) {
		System.gc();
		System.gc();
		long l = Runtime.getRuntime().freeMemory();
		long start = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		o = JSON.parseObject(s, xbeanClass);
		System.gc();
		long l1 = Runtime.getRuntime().freeMemory();
		long end = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		int total  = (int) (end - start);
		return new KV<Integer, Integer>(s.length()*2, total);
	}*/


    private String selectTable(String tableName) throws Exception {
        String sql = "SELECT * FROM " + tableName + " LIMIT 100;";
        Statement statement = conn.createStatement();

        ArrayList<String> vList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String string = resultSet.getString(2);
            vList.add(string);
        }

        String selectV = null;
        List<String> collect = vList.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        if (collect.size() == 1) {
            selectV = collect.get(0);
        }

        if (collect.size() > 1) {
            selectV = collect.get(collect.size() / 2);
        }

        return selectV;
    }


    public Class<?> findClassNameByTableName(String tableName) throws Exception {
        String logicTablePackage = "table.logic.";
        Class<?> email = Class.forName(logicTablePackage + "Email");
        Method add = email.getMethod("get", Long.class);
        return add.getReturnType();
    }


    private List<String> findLbTables() throws Exception {
        ArrayList<String> tables = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2.创建DocumentBuilder对象

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document d = builder.parse("config/mdb/lobby-mdb.xml");
        Element root = d.getDocumentElement();

        NodeList table = root.getElementsByTagName("table");
        for (int i = 0; i < table.getLength(); i++) {
            Element element = (Element) table.item(i);
            tables.add(element.getAttribute("name"));
        }
        return tables;
    }
}
