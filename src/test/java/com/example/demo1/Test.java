package com.example.demo1;


import java.sql.*;
import java.util.Calendar;
import java.sql.Date;
import java.util.Random;
import java.util.UUID;


public class Test {
    Connection conn;
    PreparedStatement preStmt = null;
    ResultSet rt = null;

    public volatile int resuleTime;
    long end;
    long start;

    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test1?rewriteBatchedStatements=true";
    String user = "root";
    String password = "123";
    {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void fun1() {
        start = System.currentTimeMillis();
        //init();
        Runnable run1 = () -> insertUserBehaviour();
        try {
        for (int i=0; i<10; i++) {
            Thread t1 = new Thread(run1);
            t1.start();
            // 默认主线程执行完毕会结束子线程,使用 Join来使子线程等待
            t1.join();
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void fun2() {
        start = System.currentTimeMillis();
        insertUserBehaviour();
        end = System.currentTimeMillis();
        System.out.println((end-start)/1000 + "秒");
    }
    public void insertUserBehaviour() {


        try {
        conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "insert into user1 (id,name,age,gender,create_time)"
                + " values";
        String condition = " (null,?,?,?,?),";


        int num = 100;

        // 拼接语句
        for (int i = 0; i < num; i++) {
            sql += condition;
        }

        // 去除最后一个逗号
        sql = sql.substring(0,sql.length()-1);

        try {
            preStmt = conn.prepareStatement(sql);

            conn.setAutoCommit(false);
           for (int y = 0; y < 1000; y++) {
                for (int i = 0; i < 100; i++) {
                    int index = 0;
                    while (true) {
                        preStmt.setString(++index, UUID.randomUUID().toString().replace("-", "").substring(0,6));
                        preStmt.setInt(++index, new Random().nextInt(100));
                        preStmt.setInt(++index, new Random().nextInt(1));
                        preStmt.setDate(++index, getTime());
                        if(index == num * 4) {
                            break;
                        }
                    }
                    preStmt.addBatch();
                }
                preStmt.executeBatch();
               conn.commit();
            }
            conn.close();


            resuleTime++;
            if(resuleTime == 10) {
                end = System.currentTimeMillis();
                System.out.println((end-start)/1000 + "秒");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void getName() {
        Calendar c = Calendar.getInstance();
        c.set(2000, 0, 0, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        System.out.println(c.getTime().getTime());
        //return "1";
    }

    public Date getTime() {
        Long start = 946569600000L; //(2000)
        Long end = 1514649600000L;//(2018)

        long result = (long) (start + Math.random() * (end - start));
        return new Date(result);
    }

}
