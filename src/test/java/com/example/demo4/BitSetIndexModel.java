package com.example.demo4;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BitSetIndexModel {
    private String type;//索引类型：address，age，gender
    private ConcurrentHashMap<String, Integer> map;//索引类型和bitSet在bsList中下标的映射关系
    private List<String> list;//索引类型的值集合，例如gender：girl，boy
    private List<BitSet> bsList;//bitset集合

    public BitSetIndexModel() {

    }

    public BitSetIndexModel(String type) {
        this.type = type;
        map = new ConcurrentHashMap<String, Integer>();
        list = new ArrayList<String>();
        bsList = new ArrayList<BitSet>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(ConcurrentHashMap<String, Integer> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<BitSet> getBsList() {
        return bsList;
    }

    public void setBsList(List<BitSet> bsList) {
        this.bsList = bsList;
    }

    /**
     *
     * @param str
     * @param i
     */
    public void createIndex(String str, int i) {
        BitSet bitset = null;
        //获取‘str'对应的bitset在bsList中的下标
        Integer index = this.getMap().get(str);
        if (index != null) {
            bitset = this.getBsList().get(index);
            if (bitset == null) {
                bitset = new BitSet();
                this.getBsList().add(index, bitset);
            }
            bitset.set(i, true);// 将str对应的位置为true,true可省略
        } else {
            bitset = new BitSet();
            List<String> list = this.getList();
            list.add(str);
            index = list.size() - 1;
            bitset.set(i, true);
            this.getBsList().add(bitset);
            this.getMap().put(str, index);
        }
    }

    /**
     * 从entity里拿出符合条件的bitset
     *
     * @param str
     * @return
     */
    public BitSet get(String str) {
        BitSet bitset = null;
        str = str.toLowerCase();
        Integer index = this.getMap().get(str);

        if (index != null) {
            bitset = this.getBsList().get(index);
        } else {
            bitset = new BitSet();
        }
        return bitset;
    }

    /**
     * bitset的与运算
     *
     * @param str
     * @param bitset
     * @return
     */
    public BitSet and(String str, BitSet bitset) {
        if (str != null) {
            str = str.toLowerCase();
            if (bitset != null) {
                bitset.and(get(str));
            } else {
                bitset = new BitSet();
                bitset.or(get(str));
            }
        }
        return bitset;
    }

    /**
     * bitset的或运算
     *
     * @param str
     * @param bitset
     * @return
     */
    public BitSet or(String str, BitSet bitset) {
        if (str != null) {
            str = str.toLowerCase();
            if (bitset != null) {
                bitset.or(get(str));
            } else {
                bitset = new BitSet();
                bitset.or(get(str));
            }
        }
        return bitset;
    }

    /**
     * 获取bitset值为true的 即 把 bitset翻译为list的索引
     *
     * @param bitset
     * @return
     */
    public static List<Integer> getRealIndexs(BitSet bitset) {
        List<Integer> indexs = new ArrayList<Integer>();
        if (bitset != null) {
            int i = bitset.nextSetBit(0);
            if (i != -1) {
                indexs.add(i);
                for (i = bitset.nextSetBit(i + 1); i >= 0; i = bitset.nextSetBit(i + 1)) {
                    int endOfRun = bitset.nextClearBit(i);
                    do {
                        indexs.add(i);
                    } while (++i < endOfRun);
                }
            }
        }

        return indexs;
    }

}
