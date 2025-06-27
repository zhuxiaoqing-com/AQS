package com.example.datastruture.stringmatch.ac;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AC1 {
	AcNode root = new AcNode('/');


	static class AcNode {
		public Character data;
		public HashMap<Character, AcNode> children = new HashMap();
		public boolean isEndingChar = false;
		public int length = -1; // 当 isEndingChar = true 时,记录模式串长度
		public AcNode fail; // 失败指针

		public AcNode(char data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "AcNode{" +
					"data=" + data +
					", children=" + children +
					", isEndingChar=" + isEndingChar +
					", length=" + length +
					'}';
		}
	}


	public void buildTree(List<String> words) {
		words = distinctAndSort(words);
		for (String word : words) {
			addWord(word);
		}

		fallbackAll();
	}


	// 词汇去重
	private List<String> distinctAndSort(List<String> words) {
		return words.stream()
				.map(String::trim)
				.filter(a -> !a.isEmpty())
				.collect(Collectors.toList());
	}

	private void addWord(String sensitive) {
		AcNode current = root;
		for (int i = 0; i < sensitive.length(); i++) {
			char c = sensitive.charAt(i);
			AcNode acNode = current.children.get(c);
			if (acNode != null) {
				current = acNode;
				continue;
			}

			AcNode node = new AcNode(c);
			current.children.put(c, node);
			current = node;
		}
		current.isEndingChar = true;
		current.length = sensitive.length();
	}

	/**
	 * 修理回退指针
	 * <p>
	 * 如果没有失败节点就默认为 root;
	 * <p>
	 * 默认用父类的失败节点;
	 * 查看父类的失败节点的下一个节点是否有等于自己的;有的话就将其标位自己的失败节点;
	 * 没有的话就继续查看父类的失败节点的失败节点,以此类推;
	 */
	private void fallbackAll() {
		ArrayDeque<AcNode> queue = new ArrayDeque<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			AcNode poll = queue.poll();
			for (AcNode value : poll.children.values()) {
				AcNode fail = poll.fail;
				while (fail != null) {
					AcNode acNode = fail.children.get(value.data);
					if (acNode != null) {
						value.fail = acNode;
						break;
					}
					fail = fail.fail;
				}

				if (value.fail == null) {
					value.fail = root;
				}
				queue.add(value);
			}
		}
	}


	public boolean match(String text) {
		AcNode node = root;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			while (!node.equals(root) && !node.children.containsKey(c)) {
				node = node.fail;
			}
			node = node.children.get(c);

			if (node == null) {
				node = root;
			}

			if (node.isEndingChar) {
				return true;
			}
		}
		return false;
	}


	public String replace(String text, char replaceChar) {
		char[] chars = text.toCharArray();
		AcNode node = root;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			while (!node.equals(root) && !node.children.containsKey(c)) {
				node = node.fail;
			}
			node = node.children.get(c);

			if (node == null) {
				node = root;
			}

			if (node.isEndingChar) {
				int j = i - node.length + 1;
				for (int charIndex = j; charIndex <= i; charIndex++) {
					chars[charIndex] = replaceChar;
				}
			}
		}
		return new String(chars);
	}
}















