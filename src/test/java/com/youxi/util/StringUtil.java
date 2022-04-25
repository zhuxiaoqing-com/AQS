package com.youxi.util;


import java.util.*;
import java.util.Map.Entry;

public class StringUtil {
	private static final char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C',
			'V', 'B', 'N', 'M', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O',
			'P' };
	public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
                        || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
                        || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return isEmoji;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * 
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (isEmpty(source)) {
            return source;
        }
        StringBuffer buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuffer(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }
	/**
	 * 生成随即密码
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String genRandomString(int len) {
		final int maxNum = str.length;
		char[] pwdchar = new char[len];
		Random r = new Random();
		for (int i = 0; i < pwdchar.length; i++) {
			pwdchar[i] = str[r.nextInt(maxNum)];
		}

		return new String(pwdchar);
	}
	
	public static boolean isEmpty(String str) {
		if(str == null || str.length() == 0)
			return true;
		
		return false;
	}

	public static <T> T castToNotNull(T t,T def){
		return t==null? def:t;
	}
	
	public static String roleNameFilter(String roleName) {
        if (isEmpty(roleName)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (char c : roleName.trim().toCharArray()) {
            if (c != 32 && c != 9 && c != 10 && c != 11 && c != 13) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
	
	 /**
     *	 获取字符串的长度，汉字为2，其他字符为1
     * 
     * @param str
     * @param isChina  是否汉字算2个  true是  false不是
     * @return
     */
    public static int getStrLength(String str, boolean isChina) {
        if (isEmpty(str)) {
            return 0;
        }
        int count = 0;
        for (char c : str.trim().toCharArray()) {
            if (c >= 0 && c <= 255) {
                count += 1;
            } else {
            	if(isChina) {
            		count += 2;
            	}else {
            		count += 1;
            	}
            }
        }
        return count;
    }
    
    /**字符串集合按照升序排序**/
    public static List<String> getSortList(List<String> strList){
    	Collections.sort(strList, new Comparator<String>(){
    		@Override
    		public int compare(String arg0, String arg1) {
    			if (arg0.compareTo(arg1) > 0) {
    				return 1;
    			} else if (arg0.compareTo(arg1) < 0) {
    				return -1;
    			} else {
    				return 0;
    			}
    		}
		});
    	return strList;
    }
    
    
    public static List<String> getSortList(Map<String, Object> map){
    	List<String> strList = new ArrayList<>();
    	for(Entry<String, Object> entry:map.entrySet()) {
    		strList.add(entry.getKey());
    	}
    	return getSortList(strList);
    }
    public static String  rep(String s,int n) {
		StringBuffer sb=new StringBuffer();
		while(--n>=0) {
			sb.append(s);
		}
		return sb.toString();
	}
}
