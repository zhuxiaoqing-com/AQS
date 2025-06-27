package com.example.datastruture.stringmatch.trie;

import java.util.HashMap;

public class Trie {
    private TrieNode root = new TrieNode('/'); // 存储无意义字符

    /**
     * 往 Trie 树中插入一个字符串
     */
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            TrieNode trieNode = p.children.get(text[i]);
            if (trieNode == null) {
                trieNode = new TrieNode(text[i]);
                p.children.put(text[i], trieNode);
            }
            p = trieNode;
        }
        // 最后设置为是一个完整字符
        p.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            char c = pattern[i];
            TrieNode trieNode = p.children.get(c);
            if (trieNode == null) {
                return false;
            }
            p = trieNode;
        }
        if(!p.isEndingChar) {
            return false;
        }
        return true;
    }


    /**
     * 树节点
     */
    static class TrieNode {
        char data;
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isEndingChar;

        public TrieNode(char c) {
            this.data = c;
        }
    }
}
