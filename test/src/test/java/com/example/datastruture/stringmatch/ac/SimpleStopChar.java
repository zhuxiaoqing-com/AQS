package com.example.datastruture.stringmatch.ac;

import cn.hutool.core.collection.CollUtil;

import java.util.Set;

/**
 * 过滤字符
 */
public class SimpleStopChar {
    public static final Set<Character> STOP_WORD = CollUtil.newHashSet(' ');

    public static boolean isStopChar(char ch) {
        return Character.isWhitespace(ch) || STOP_WORD.contains(ch);
    }

    public static boolean isNotStopChar(char ch) {
        return !isStopChar(ch);
    }
}
