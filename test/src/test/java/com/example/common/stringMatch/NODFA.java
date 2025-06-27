package com.example.common.stringMatch;

import java.util.Map;

public class NODFA {

    public boolean getSensitiveWordLength(String text) {
        if (text == null || text.trim().length() == 0) {
            return false;
        }
        char currentChar;
        Map<Object, Object> currentMap = MatchIllegalCharacter.getInstance().getSensitiveWordsMap();
        StringBuilder builder = new StringBuilder(text);
        for (int x = 0; x < builder.length(); x++) {
            for (int i = 0; i < builder.length(); i++) {
                currentChar = builder.charAt(i);
                Map<Object, Object> subMap = (Map<Object, Object>) currentMap.get(currentChar);
                if (subMap == null) {
                    break;
                }
                if (subMap.containsKey(MatchIllegalCharacter.END_FLAG)) {
                    return false;
                }
                currentMap = subMap;
            }
            // 删除第一个元素
            builder.delete(0,1);
        }
        return true;
    }

}
