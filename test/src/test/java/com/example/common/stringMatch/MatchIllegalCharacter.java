package com.example.common.stringMatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 匹配非法字符
 */
public class MatchIllegalCharacter {
    private static MatchIllegalCharacter INSTANCE = new MatchIllegalCharacter();

    public static MatchIllegalCharacter getInstance() {
        return INSTANCE;
    }

    private Map<Object, Object> sensitiveWordsMap = new HashMap<>();
    public static final String END_FLAG = "end";

    /**
     * 循环将字符串传入
     * @param sensitiveWords
     */
    private void initSensitiveWordsMap(List<String> sensitiveWords) {
        if (sensitiveWords == null || sensitiveWords.isEmpty()) {
            return;
        }
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(sensitiveWords.size());
        String currentWord;
        Map<Object, Object> currentMap;
        Map<Object, Object> subMap;
        for (int x = 0; x < sensitiveWords.size(); x++) {
            currentWord = sensitiveWords.get(x);
            if (currentWord == null || currentWord.isEmpty()) {
                continue;
            }
            currentMap = sensitiveWordsMap;
            for (int i = 0; i < currentWord.length(); i++) {
                char c = currentWord.charAt(i);
                subMap = (Map<Object, Object>) currentMap.get(c);
                if (subMap == null) {
                    subMap = new HashMap<>();
                    currentMap.put(c, subMap);
                }
                currentMap = subMap;
                if(i == currentWord.length() - 1) {
                    // 如果是最后一个字符，则 put 一个结束标志，这里只需要保存 key 就行了, value 为 Null 可以节约空间
                    // 如果不是最后一个字符，不需要存这结束标志
                    currentMap.put(END_FLAG, null);
                }
            }
        }
    }

    public void init(List<String> sensitiveWords) {
        initSensitiveWordsMap(sensitiveWords);
    }

    public Map<Object, Object> getSensitiveWordsMap() {
        return sensitiveWordsMap;
    }
}
