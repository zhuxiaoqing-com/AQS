package com.example.common.stringMatch.kmp;

public class Kmp01 {

    public int[] getNext(String pattern) {
        // 前缀指针，后缀指针
        int prefix = 0, suffix = 1;
        int[] next = new int[pattern.length()];
        next[0] = 0;
        // 只要后缀指针没有走完就不会结束
        while (suffix < pattern.length()) {
            // 如果是 prefix 0 的话就不需要往 suffix 里面取了 说明上一次也没有匹配
            // 也不能取 如果取了就会 下标异常
            if (prefix > 0 && pattern.charAt(prefix) != pattern.charAt(suffix)) {
                // 然后从 next 里面找出上一次匹配的元素的下标
                // 因为取出来以后还要比较一次 确定不行才能写入 next 所以
                // 将 pattern.charAt(prefix) == pattern.charAt(suffix) 放下面
                prefix = next[prefix - 1];
                // 取出 suffix - 1 对应的 prefix 的下标;也就是 suffix - 1 在字符串中对应的前缀长度
                //prefix = next[next[suffix - 1]];// 和上面代码一个意思
            }

            if (pattern.charAt(prefix) == pattern.charAt(suffix)) {
                // 相同就两个指针都走
                prefix++;
                suffix++;
            }
            /**
             * 记录着当长度为 suffix 的时候 前缀和后缀的交集元素个数
             * 也记录着该下标的上一次在该 字符串里面出现的下标(prefix - 1 因为 prefix是个数 而下标是 0 开始的)
             * 1、记录着后缀与前缀对应的最大共同元素
             * 2、通过 prefix 可以计算出 上一个后缀
             */
            next[suffix] = prefix;
        }
        return next;
    }


    public boolean proceedKMP(String content, String pattern) {
        int[] next = getNext(pattern);
        int p = 0, c = 0;
        // 如果有一个匹配结束了 就说明匹配完成了
        while (p < content.length() && c < pattern.length()) {
            if (p > 0 && content.charAt(c) != pattern.charAt(p)) {
                // 不相等就取出上一个相等的前缀
                p = next[p];
            }
            if(content.charAt(c) == pattern.charAt(p)) {
                p++;
                c++;
            }
        }
        if(c == pattern.length()) {
            return true;
        } else {
            return false;
        }
    }

}
