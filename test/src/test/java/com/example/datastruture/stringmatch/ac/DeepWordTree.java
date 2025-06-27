package com.example.datastruture.stringmatch.ac;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.StopChar;
import com.youxi.util.StringUtil;

import java.util.*;

/**
 * 深度关键字树;
 *
 * @author Joker
 * @date Created in 2020/10/22 10:07
 */
public class DeepWordTree extends HashMap<Character, DeepWordTree> {
    private static final long serialVersionUID = -4646423269465809276L;

    /**
     * 敏感词字符末尾标识，用于标识单词末尾字符
     */
    private final Set<Character> endCharacterSet = new HashSet<>();
    /**
     * 字符过滤规则，通过定义字符串过滤规则，过滤不需要的字符，当accept为false时，此字符不参与匹配
     */
    private static Filter<Character> charFilter = StopChar::isNotStopChar;

    /** 查找深度, 即两个关键字之间有多少个未匹配的其他有效字符 */
    private static int matchDeep = 0;

    //--------------------------------------------------------------------------------------- Constructor start

    /**
     * 默认构造
     */
    public DeepWordTree() {
    }
    //--------------------------------------------------------------------------------------- Constructor start

    /**
     * 设置字符过滤规则，通过定义字符串过滤规则，过滤不需要的字符<br>
     * 当accept为false时，此字符不参与匹配
     *
     * @param charFilter 过滤函数
     * @since 5.2.0
     */
    public static void setCharFilter(Filter<Character> charFilter) {
        DeepWordTree.charFilter = charFilter;
    }

    /**
     * 设置查找深度, 即两个关键字之间有多少个未匹配的其他有效字符
     *
     * @param matchDeep 深度
     */
    public static void setMatchDeep(int matchDeep) {
        DeepWordTree.matchDeep = matchDeep;
    }

    //------------------------------------------------------------------------------- add word

    /**
     * 增加一组单词
     *
     * @param words 单词集合
     */
    public void addWords(Collection<String> words) {
        if (!(words instanceof Set)) {
            words = new HashSet<>(words);
        }
        for (String word : words) {
            addWord(word);
        }
    }

    /**
     * 增加一组单词
     *
     * @param words 单词数组
     */
    public void addWords(String... words) {
        HashSet<String> wordsSet = CollectionUtil.newHashSet(words);
        for (String word : wordsSet) {
            addWord(word);
        }
    }

    /**
     * 添加单词，使用默认类型
     *
     * @param word 单词
     */
    public void addWord(String word) {
        final Filter<Character> charFilter = DeepWordTree.charFilter;
        DeepWordTree parent = null;
        DeepWordTree current = this;
        DeepWordTree child;
        char currentChar = 0;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            currentChar = word.charAt(i);
            if (charFilter.accept(currentChar)) {//只处理合法字符
                child = current.get(currentChar);
                if (child == null) {
                    //无子类，新建一个子节点后存放下一个字符
                    child = new DeepWordTree();
                    current.put(currentChar, child);
                }
                parent = current;
                current = child;
            }
        }
        if (null != parent) {
            parent.setEnd(currentChar);
        }
    }

    //------------------------------------------------------------------------------- match

    /**
     * 指定文本是否包含树中的词
     *
     * @param text 被检查的文本
     * @return 是否包含
     */
    public boolean isMatch(String text) {
        if (null == text) {
            return false;
        }
        return null != match(text);
    }

    /**
     * 获得第一个匹配的关键字
     *
     * @param text 被检查的文本
     * @return 匹配到的关键字
     */
    public String match(String text) {
        if (null == text) {
            return null;
        }
        List<String> matchAll = matchAll(text, 1);
        if (CollectionUtil.isNotEmpty(matchAll)) {
            return matchAll.get(0);
        }
        return null;
    }

    //------------------------------------------------------------------------------- match all

    /**
     * 找出所有匹配的关键字
     *
     * @param text 被检查的文本
     * @return 匹配的词列表
     */
    public List<String> matchAll(String text) {
        return matchAll(text, -1);
    }

    /**
     * 找出所有匹配的关键字
     *
     * @param text  被检查的文本
     * @param limit 限制匹配个数
     * @return 匹配的词列表
     */
    public List<String> matchAll(String text, int limit) {
        return matchAll(text, limit, false, false);
    }

    /**
     * 找出所有匹配的关键字<br>
     * 密集匹配原则：假如关键词有 ab,b，文本是abab，将匹配 [ab,b,ab]<br>
     * 贪婪匹配（最长匹配）原则：假如关键字a,ab，最长匹配将匹配[a, ab]
     *
     * @param text           被检查的文本
     * @param limit          限制匹配个数
     * @param isDensityMatch 是否使用密集匹配原则
     * @param isGreedMatch   是否使用贪婪匹配（最长匹配）原则
     * @return 匹配的词列表
     */
    public List<String> matchAll(String text, int limit, boolean isDensityMatch, boolean isGreedMatch) {
        if (null == text) {
            return null;
        }

        List<String> foundWords = new ArrayList<>();
        DeepWordTree current = this;
        int length = text.length();
        final Filter<Character> charFilter = DeepWordTree.charFilter;
        //存放查找到的字符缓存。完整出现一个词时加到findedWords中，否则清空
        final StrBuilder wordBuffer = StrUtil.strBuilder();
        char currentChar;
        for (int i = 0; i < length; i++) {
            wordBuffer.reset();
            int notMatchNum = 0;
            for (int j = i; j < length; j++) {
                currentChar = text.charAt(j);
                if (!charFilter.accept(currentChar)) {
                    if (wordBuffer.length() > 0) {
                        //做为关键词中间的停顿词被当作关键词的一部分被返回
                        wordBuffer.append(currentChar);
                        notMatchNum = 0;
                    } else {
                        //停顿词做为关键词的第一个字符时需要跳过
                        i++;
                    }
                    continue;
                } else if (!current.containsKey(currentChar)) {
                    //非关键字符被整体略过，重新以下个字符开始检查
                    notMatchNum++;
                    if (notMatchNum > matchDeep || wordBuffer.length() <= 0) {
                        break;
                    } else {
                        wordBuffer.append(currentChar);
                        continue;
                    }
                }
                wordBuffer.append(currentChar);
                notMatchNum = 0;
                if (current.isEnd(currentChar)) {
                    //到达单词末尾，关键词成立，从此词的下一个位置开始查找
                    foundWords.add(wordBuffer.toString());
                    if (limit > 0 && foundWords.size() >= limit) {
                        //超过匹配限制个数，直接返回
                        return foundWords;
                    }
                    if (!isDensityMatch) {
                        //如果非密度匹配，跳过匹配到的词
                        i = j;
                    }
                    if (!isGreedMatch) {
                        //如果懒惰匹配（非贪婪匹配）。当遇到第一个结尾标记就结束本轮匹配
                        break;
                    }
                }
                current = current.get(currentChar);
                if (null == current) {
                    break;
                }
            }
            current = this;
        }
        return foundWords;
    }


    //--------------------------------------------------------------------------------------- Private method start

    /**
     * 是否末尾
     *
     * @param c 检查的字符
     * @return 是否末尾
     */
    private boolean isEnd(Character c) {
        return this.endCharacterSet.contains(c);
    }

    /**
     * 设置是否到达末尾
     *
     * @param c 设置结尾的字符
     */
    private void setEnd(Character c) {
        if (null != c) {
            this.endCharacterSet.add(c);
        }
    }

	/**
	 * 过滤聊天内容;<br/>
	 *
	 * @param inputContent 欲过滤的内容
	 * @param replaceChar  将检测到的敏感字替换成的字符
	 * @return 屏蔽之后的字符串
	 */
	public String filterChatMsg(String inputContent, String replaceChar) {
		String result = inputContent;
		List<String> strings = matchAll(inputContent, -1, false, true);
		int size = strings.size();
		for (int i = size - 1; i >= 0 ; i--) {
			String eachMatch = strings.get(i);
			result = result.replace(eachMatch, StringUtil.rep(replaceChar, eachMatch.length()));
		}
		return result;
	}
    //--------------------------------------------------------------------------------------- Private method end
}
