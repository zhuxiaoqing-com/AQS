package com.example.common.stringMatch.ac;


import com.example.common.stringMatch.LoadWords;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


public class SensitiveFilterImprove {
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

    /**
     * 绑定敏感字将其变为树状
     *
     * @param words
     */
    public void buildTree(List<String> words) {
        // 词汇去重
        words = distinctAndSort(words);
        for (String word : words) {
            // 添加单词
            addWord(root, word);
        }
        // 修理回退指针
        fallbackAll(root);
    }

    // 词汇去重
    private List<String> distinctAndSort(List<String> words) {
        // 去除空字符串，去除字符串两边空格
        List<String> list = new ArrayList<>();
        for (String x : words) {
            if (x != null && !x.isEmpty()) {
                String trim = x.trim();
                list.add(trim);
            }
        }
        return list;
    }

    /**
     * 添加敏感字
     *
     * @param root      根节点
     * @param sensitive 敏感字
     */
    private void addWord(WordsNode root, String sensitive) {
        WordsNode current = root;
        for (int i = 0; i < sensitive.length(); i++) {
            char c = sensitive.charAt(i);
            WordsNode scanObj = current.next.get(c);
            if (scanObj == null) {
                // public ScanObj(char val, ScanObj back, ScanObj parent, boolean accept) {
                // 失败指针先全部指向 root 以便后面修理失败指针的时候遍历;
                scanObj = new WordsNode(c, root, current);
                current.next.put(c, scanObj);
            }
            current = scanObj;
        }
        current.accept = true;
    }

    /**
     * 为什么失败节点要从父节点开始 就和 kmp 一样 要和 root 错开;
     * i = 0; j = 1; 这样才能匹配 前缀(没有最后一个字母)和后缀(没有第一个字母)
     */
    /**
     * 修理回退指针。失败后直接调到下一个失败的节点
     * 一个节点的失效节点所代表的字符串是该节点所表示它的字符串的最大 部分前缀
     *
     * @param root 根节点
     */
    private void fallbackAll(WordsNode root) {
        Collection<WordsNode> values = root.next.values();
        while (values.size() > 0) {
            List<WordsNode> nextPoints = new ArrayList<>();
            for (WordsNode scanObj : values) {
                // 将所有子节点保存起来; 一层一层修理
                nextPoints.addAll(scanObj.next.values());
                // 获取父亲节点的 Back 默认都是 root 也就是从 root 往下遍历
                WordsNode back = scanObj.parent.back;
                while (back != null) {
                    WordsNode child = back.next.get(scanObj.val);
                    if (child != null) {
                        scanObj.back = child;
                        break;
                    }
                    back = back.back;
                }
            }
            values = nextPoints;
        }
    }

    /**
     * 查询并找出不符合的规则的敏感字
     *
     * @param content
     * @return
     */
    public List<String> searchAndFindSensitive(String content) {
        WordsNode current = root;
        List<String> sensitives = new ArrayList<>();
        for (int x = 0; x < content.length(); x++) {
            char c = content.charAt(x);
            WordsNode scanObj = current.next.get(c);
            /*
            升级版的 AC  获取 back 如果获取到了 root 层次就不获取了
            直接继续下一个字节
             */
            while (scanObj == null && current != root) {
                current = current.back;
                scanObj = current.next.get(c);
            }

            if (scanObj != null) {
                // 如果结束了
                if (scanObj.accept) {
                    sensitives.add(collect(scanObj));
                }
                current = scanObj;
            }
        }
        return sensitives;
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

    /**
     * 查找是否有敏感字
     *
     * @param content
     * @return
     */
    public boolean search(String content) {
        WordsNode current = root;
        for (int x = 0; x < content.length(); x++) {
            char c = content.charAt(x);
            WordsNode scanObj = current.next.get(c);
            while (scanObj == null && current != root) {
                current = current.back;
                scanObj = current.next.get(c);
            }

            if (scanObj != null) {
                // 如果结束了
                if (scanObj.accept) {
                    return false;
                }
                current = scanObj;
            }
        }
        return true;
    }

    /**
     * 将特殊符号空格等过滤掉
     */
    public String filterSpecialContent(String content) {
        String regex = "[^a-zA-Z_0-9\\u4e00-\\u9fa5]";
        Pattern NAME_PATTERN = Pattern.compile(regex);
        return NAME_PATTERN.matcher(content).replaceAll("");
    }

    /**
     * 过滤所有不是中文的字符
     *
     * @param content
     * @return
     */
    public String filterNonChineseContent(String content) {
        String regex = "[^\\u4e00-\\u9fa5]";
        Pattern NAME_PATTERN = Pattern.compile(regex);
        return NAME_PATTERN.matcher(content).replaceAll("");
    }

    @Test
    public void test01() {
        String v1 = "  sf!232  4!^**(&f单号if你懂个我奥国际破案件告破机构评级 hSFAjdso  ".replaceAll("[^a-zA-Z_0-9\\u4e00-\\u9fa5]", "");
        System.out.println(v1);
    }

    String content = "1995年中共执政当局开始寻求强化法轮功的组织构架及与政府的关系。" +
            "中国政府的国家体委、公共健康部和气功科研会，访问李洪志，要求联合成立法轮功协会，但李洪志表示拒绝。" +
            "同年，气功科研会通过一项新规定，命令所有气功分会必须建立中国共产党党支部，但李洪志再次表示拒绝。" +
            "李洪志与中国气功科研会的关系在1996年持续恶化。" +
            "1996 年3月，法轮功因拒不接受中国气功协会新负责人在“气功团体内部收取会员费创收”和“成立中国共产党党支部组织”的要求，" +
            "主动申请退出中国气功协会和中国 气功科研会, 以独立非政府形式运作。" +
            "自此，李洪志及其法轮功脱离了中国气功协会中的人脉和利益交换，同时失去了功派在中国政府体制系统的保护。" +
            "法轮功申请退出中国气功协会，是与中国政府对气功的态度产生变化相对应的；" +
            "当时随气功激进反对者在政府部门中的影响力增加，中国政府开始控制和影响各气功组织。" +
            "90年代中期，中国政府主管的媒体开始发表文章批评气功。" +
            "法轮功起初并没有受批评，但在1996年3月退出中国气功协会后，失去了政府体制的保护。";

    String content2 = "我是习d近 DF平";

    //{政府=9, 李洪志=5, 法轮功=6, 中共=1, 共产党=2}
    @Test
    public void test02() throws IOException {
		List<String> words = LoadWords.load();
        buildTree(words);
//        List<String> search = search(content);
        List<String> search = searchAndFindSensitive(filterNonChineseContent(content));
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : search) {
            Integer count = map.get(s);
            if (count == null) {
                map.put(s, 1);
            } else {
                map.put(s, count + 1);
            }
        }
        System.out.println(map);
    }

}

















