package com.example.common.stream.xmlupdate;

import org.junit.Test;

public class Rule {


    String prefix = "<message";
    String type = "type=\"toServer\"";
    char spacing = ' ';
    String ht = "ht=\"1\"";

    /**
     * <message id="1" type="toServer" class="ReqAchievementInfo" desc="向服务器请求成就信息" ht="1">
     * <p>
     * 不符合返回 -1
     *
     * @param str
     * @return
     */
    public boolean isMatch(String str) {
        // 不包含 前缀
        boolean conform = str.trim().startsWith(prefix);
        if (!conform) {
            return false;
        }
        // 不是 toServer
        conform = str.contains(type);
        if (!conform) {
            return false;
        }
        return true;
    }

    /**
     * <message id="1" type="toServer" class="ReqAchievementInfo" desc="向服务器请求成就信息" ht="1">
     * <p>
     * 不符合返回 -1
     *
     * @param str
     * @return
     */
    public boolean isHaveHt(String str) {
        String ht = "ht";
        return str.contains(ht);
    }

    /**
     * 删除 ht
     *
     * @param str
     * @return
     */
    public String deleteHt(String str) {
      /*  String ht = "ht";
        int start = str.indexOf(ht);
        int end = start;
        while (str.charAt(end) != spacing) {
            end++;
        }
        String replace = str.replace(str.substring(start, end), "");
        return replace;*/

        return str.replace(ht, "");
    }

    public String addHt(String str) {
        char xieGang = '\"';
        boolean flag = false;
        for (int i = str.length(); i >= 0; i++) {
            char c = str.charAt(i);
            if (!flag) {
                if (c != spacing) {
                    flag = true;
                } else {
                    continue;
                }
            }

            if (flag) {
                // todo  放弃了中断了
               // if(c)
            }
        }
        return null;
    }

    @Test
    public void test01() {
        String str = "01ht 456";
        String ht = "ht";
        int i = str.indexOf(ht);
        int index = i;
        while (str.charAt(index) != spacing) {
            index++;
        }
        String replace = str.replace(str.substring(i, index), "");
    }
}
