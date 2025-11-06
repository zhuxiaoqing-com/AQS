import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2022/5/24 09:24
 * @Description: 检测hashmap并发错误 Map 会把访问hashMap的所有线程都打印出来;一旦有两个线程进来就会打印两个线程的堆栈信息
 */
public class CheckThreadHashMap<K, V> extends HashMap<K, V> {
    protected static Logger logger = LoggerFactory.getLogger(CheckThreadHashMap.class);
    Map<String, ArrayDeque<String>> record = new ConcurrentHashMap<>();
    static ThreadLocal threadLocalValue;

    public CheckThreadHashMap() {
    }

    public CheckThreadHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    static {
        // 获取 AbstractCommand 类
        Class<?> clazz = null;
        try {
       clazz = Class.forName("com.sh.concurrent.IQueueDriverCommand");
            // 获取静态字段 threadLocal
            Field threadLocalField = clazz.getDeclaredField("threadLocal");

            // 获取静态字段的值 (static 字段的值是类级别的，因此通过 null 获取)
            threadLocalValue = (ThreadLocal<Integer>) threadLocalField.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public V put(K key, V value) {
        V put = super.put(key, value);
        record(key);
        return put;
    }


    @Override
    public V remove(Object key) {
        V remove = super.remove(key);
        record(key);
        return remove;
    }

    private synchronized void record(Object key) {
        String data = createVauleData(key);

        Object o = threadLocalValue.get();
/*
        if(o != null &&Integer.parseInt(o.toString())==0) {
            HashMap<String, ArrayDeque<String>> copyRecord = new HashMap<>(this.record);
            this.record.clear();
            print(copyRecord);
            return;
        }*/
       /* if(o != null &&Integer.parseInt(o.toString())>0) {

        }*/
        if(o == null){
            return;
        }
        String string = o.toString();
        ArrayDeque<String> strings = record.computeIfAbsent(string, a -> new ArrayDeque<>());
        if (strings.size() > 10) {
            strings.removeFirst();
        }

        strings.add(data);

        if (record.size() >= 2) {
            HashMap<String, ArrayDeque<String>> copyRecord = new HashMap<>(this.record);
            this.record.clear();
            print(copyRecord);
        }

    }

    private String createVauleData(Object key) {
        StringBuilder stringBuilder = new StringBuilder();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        //String time = TimeUtil.genTs();
        stringBuilder.append(time);
        stringBuilder.append(System.getProperty("line.separator"));

        stringBuilder.append("key ====  ").append(key);
        stringBuilder.append(System.getProperty("line.separator"));

        String collect = Stream.of(Thread.currentThread().getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining(System.getProperty("line.separator")));
        stringBuilder.append(collect);
        stringBuilder.append(System.getProperty("line.separator"));
        return stringBuilder.toString();
    }

    private void print(Map<String, ArrayDeque<String>> record) {

        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry<String, ArrayDeque<String>> entry : record.entrySet()) {
            stringBuilder.append(index++).append("  线程 ").append(entry.getKey());
            stringBuilder.append(System.getProperty("line.separator"));

            for (String s : entry.getValue()) {
                stringBuilder.append(s);
                stringBuilder.append(System.getProperty("line.separator"));
                stringBuilder.append(System.getProperty("line.separator"));
                stringBuilder.append(System.getProperty("line.separator"));
            }

        }
        logger.error("battle多线程并发 battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发");
        logger.error(stringBuilder.toString());
	/*	System.out.println("battle多线程并发 battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发battle多线程并发");
		System.out.println(stringBuilder.toString());*/
    }

}
