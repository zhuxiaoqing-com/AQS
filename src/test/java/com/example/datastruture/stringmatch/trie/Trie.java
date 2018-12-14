package com.example.datastruture.stringmatch.trie;

import java.util.HashMap;

public class Trie {
private TrieNode root = new TrieNode('/'); // 存储无意义字符



    /**
     * 树节点
     */
    static class TrieNode{
        char data;
        HashMap<Character, TrieNode> children;

        public TrieNode(char c) {
            this.data = c;
        }
    }
}
