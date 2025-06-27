package com.example.common.stringMatch.ac;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class FastScan01 {
    class WordsNode {
        HashMap<Character, WordsNode> next = new HashMap<>(); // 子节点指针
        char val; // 当前节点的字符，null 表示根节点
        WordsNode back; // 跳跃指针，也成为失败指针
        WordsNode parent; // 父节点指针
        boolean accept;// 是否形成了一个完整的词汇，中间节点也可能为true

        WordsNode() {
        }

        WordsNode(char val, WordsNode back, WordsNode parent) {
            this.val = val;
            this.back = back;
            this.parent = parent;
        }
    }
    private WordsNode root = new WordsNode();

    public void buildTree(List<String> words) {
        // 词汇去重
        words = distinctAndSort(words);
        for (String word : words) {
            // 添加单词
            addWord(root, word);
        }
        // fix backtrace pointer
        // 修理回退指针
        fallbackAll(root);
    }

    // 词汇去重
    private List<String> distinctAndSort(List<String> words) {
        // 去除空字符串
        // 去除字符串两边空格
        List<String> list = new ArrayList<>();
        for (String x : words) {
            if (x != null && !x.isEmpty()) {
                String trim = x.trim();
                list.add(trim);
            }
        }
        return list;
    }

    // 添加数据
    private void addWord(WordsNode root, String sensitive) {
        WordsNode current = root;
        for (int i = 0; i < sensitive.length(); i++) {
            char c = sensitive.charAt(i);
            HashMap<Character, WordsNode> next = current.next;
            if(next == null) {
                current.next = new HashMap<>();
            }
            WordsNode scanObj = current.next.get(c);
            if (scanObj == null) {
                // public ScanObj(char val, ScanObj back, ScanObj parent, boolean accept) {
                scanObj = new WordsNode(c, root, current);
                current.next.put(c, scanObj);
            }
            current = scanObj;
        }
        current.accept = true;
    }

    // 修理回退指针
    private void fallbackAll(WordsNode root) {
        Collection<WordsNode> values = root.next.values();
        while (values.size() > 0) {
            ArrayList<WordsNode> nextExpands = new ArrayList<>();
            // 将所有 ScanObj 加入 list
            for (WordsNode next : values) {
                // 将下一层需要修理回退指针的数据全部放入 nextExpands 中
                nextExpands.addAll(next.next.values());
                WordsNode parent = next.parent;
                WordsNode back = parent.back;
                while (back != null) {
                    // 匹配父节点的跳跃节点的子节点
                    WordsNode child = back.next.get(next.val);
                    if (child != null) {
                        next.back = child;
                        break;
                    }
                    back = back.back;
                }
            }
            values = nextExpands;
        }
    }

    public List<String> search(String content) {
        List<String> offWords = new ArrayList<>();
        WordsNode current = this.root;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            WordsNode next = current.next.get(c);
            if (next == null) {
                // 当前分支上找不到，跳到其他分支上找
                WordsNode back = current.back;
                while (back != null) {
                    next = back.next.get(c);
                    if (next != null) {
                        break;
                    }
                    back = back.back;
                }
            }
            if(next != null){
                WordsNode back = next;
                do {
                    // 收集匹配的词汇
                    if (back.accept) {
                        String s = collect(back);
                        offWords.add(s);
                    }
                    back = back.back;
                } while (back != this.root);
                current = next;
                continue;
            }
            // 重置 一个单词结束了 从 root 开始继续遍历
            current = this.root;
        }
        return offWords;
    }

    // 获取完整单词
    private String collect(WordsNode scanObj) {
        StringBuilder builder = new StringBuilder();
        while (scanObj.val != 0) {
            builder.append(scanObj.val);
            scanObj = scanObj.parent;
        }
        return builder.reverse().toString();
    }

  /*  public boolean search(String content) {
        ScanObj current = this.root;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            ScanObj next = current.next.get(c);
            if (next == null) {
                // 当前分支上找不到，跳到其他分支上找
                ScanObj back = current.back;
                while (back != null) {
                    next = back.next.get(c);
                    if (next != null) {
                        break;
                    }
                    back = back.back;
                }
            } else {
                // 如果匹配到一个完整的敏感字
                if (next.accept) {
                    return false;
                }
            }
        }
        return true;
    }*/


    @Test
    public void test01() {
        StringBuilder builder = new StringBuilder(20);
        builder.append("abcd");
        StringBuilder s = builder.insert(0, 's');
        System.out.println(builder);
        System.out.println(s.reverse());
    }

String content = "1995年中共执政当局开始寻求强化法轮功的组织构架及与政府的关系。" +
        "中国政府的国家体委、公共健康部和气功科研会，访问李洪志，要求联合成立法轮功协会，但李洪志表示拒绝。" +
        "同年，气功科研会通过一项新规定，命令所有气功分会必须建立中国共产党党支部，但李洪志再次表示拒绝。" +
        "李洪志与中国气功科研会的关系在1996年持续恶化。" +
        "1996 年3月，法轮功因拒不接受中国气功协会新负责人在“气功团体内部收取会员费创收”和“成立中国共产党党支部组织”的要求，" +
        "主动申请退出中国气功协会和中国 气功科研会, 以独立非政府形式运作。" +
        "自此，李洪志及其法轮功脱离了中国气功协会中的人脉和利益交换，同时失去了功派在中国政府体制系统的保护。" +
        "法轮功申请退出中国气功协会，是与中国政府对气功的态度产生变化相对应的;" +
        "当时随气功激进反对者在政府部门中的影响力增加，中国政府开始控制和影响各气功组织。" +
        "90年代中期，中国政府主管的媒体开始发表文章批评气功。" +
        "法轮功起初并没有受批评，但在1996年3月退出中国气功协会后，失去了政府体制的保护。";
    @Test
    public void test02() throws IOException {
        FileReader fileReader = new FileReader("D:/words.test");
        BufferedReader br = new BufferedReader(fileReader);
        ArrayList<String> words = new ArrayList<>();
        String str = "";
        while ((str = br.readLine()) != null) {
            words.add(str);
        }
        buildTree(words);
        List<String> search = search(content);
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : search) {
            Integer count = map.get(s);
            if(count == null) {
                map.put(s, 1);
            } else {
                map.put(s, count+1);
            }
        }
        System.out.println(map);
    }

    @Test
    public void fun3() {
        System.out.println(content);
    }
}

















