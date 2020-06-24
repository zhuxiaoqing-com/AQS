package com.example.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.*;
import redis.clients.util.JedisClusterCRC16;

import java.util.*;

public class redisTest01 {
    /**
     * scan 扫描 redis hash
     */
    @Test
    public void fun1() {
        JedisPool jedisPool = new JedisPool("192.168.5.83", 6379);
        Jedis jedis = jedisPool.getResource();
        String index = "0";
        ScanParams scanParams = new ScanParams().count(1000);
        List<Map.Entry<String, String>> result = null;
        while (true) {
            ScanResult<Map.Entry<String, String>> hscan = jedis.hscan("arena_robot:4", index, scanParams);
            result = hscan.getResult();
            index = hscan.getStringCursor();
            System.out.println(result.size());
            if ("0".equals(index)) {
                return;
            }
        }
    }

    public void delBigHash(String bigKey) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 游标
        String cursor = "0";
        while (true) {
            ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(bigKey, cursor,
                    new ScanParams().count(100));
            // 每次扫描后获取新的游标
            cursor = scanResult.getStringCursor();
            // 获取扫描结果
            List<Map.Entry<String, String>> list = scanResult.getResult();
            if (list == null || list.size() == 0) {
                continue;
            }
            String[] fields = getFieldsFrom(list);
            // 删除多个field
            jedis.hdel(bigKey, fields);
            // 游标为0时停止
            if (cursor.equals("0")) {
                break;
            }
        }
        // 最终删除key
        jedis.del(bigKey);
    }

    /**
     * 获取field数组
     *
     * @param list
     * @return
     */
    private String[] getFieldsFrom(List<Map.Entry<String, String>> list) {
        List<String> fields = new ArrayList<String>();
        for (Map.Entry<String, String> entry : list) {
            fields.add(entry.getKey());
        }
        return fields.toArray(new String[fields.size()]);
    }


    /**
     * redisCluster 多key 操作
     */
    @Test
    public void test2() {


    }


    JedisCluster jedisCluster = new JedisCluster(new HostAndPort("127.0.0.1", 6379));
    Map<String, JedisPool> nodeMap = jedisCluster.getClusterNodes();


    Map<String, String> serialIOMget(List<String> keys) {
        // 结果集
        HashMap<String, String> keyValueMap = new HashMap<>();
        // 属于各个节点的key列表,JedisPool要提供基于ip和port的hashcode方法
        Map<JedisPool, List<String>> nodeKeyListMap = new HashMap<>();
        // 遍历所有 key


        for (String key : keys) {
            // 使用 CRC16
            int slot = JedisClusterCRC16.getSlot(key);
            // 通过 jedisCluster 本地 slot -> node 映射获取 slot 对应的 node
            JedisPool jedisPool = getJedisPoolFromSlot(slot);
            if (jedisPool == null) {
                return null;
            }
            if (nodeKeyListMap.containsKey(jedisPool)) {
                nodeKeyListMap.get(jedisPool).add(key);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(key);
                nodeKeyListMap.put(jedisPool, list);
            }
        }

        /**
         * 这里可以多线程实现。max_slow(node网络时间)+n次命令时间
         * 因为是不同的 redis 节点;所以多线程实现也没事
         */
        // 从每个节点上批量获取， 这里使用mget也可以使用pipeline
        for (Map.Entry<JedisPool, List<String>> entry : nodeKeyListMap.entrySet()) {
            JedisPool jedisPool = entry.getKey();
            List<String> nodeKeyList = entry.getValue();
            // 列表变为数组
            String[] nodeKeyArray = nodeKeyList.toArray(new String[nodeKeyList.size()]);
            // 批量获取， 可以使用mget或者Pipeline
            List<String> nodeValueList = jedisPool.getResource().mget(nodeKeyArray);
            // 归档
            for (int i = 0; i < nodeKeyList.size(); i++) {
                keyValueMap.put(nodeKeyList.get(i), nodeValueList.get(i));
            }
        }
        return keyValueMap;

    }

    /**
     * 根据slot 获取合适的 jedisPool 没有实践过！！！
     */
    private JedisPool getJedisPoolFromSlot(int slot) {
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        Collection<JedisPool> values = clusterNodes.values();
        Optional<JedisPool> any = values.stream()
                .filter(x -> x.getResource().clusterSlots().contains(slot))
                .findAny();
        return any.orElse(null);
    }


}
