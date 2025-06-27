package com.example.common.stringMatch.ac;

import com.example.common.stringMatch.LoadWords;
import org.junit.Test;

import java.io.IOException;
import java.util.*;


public class KeyWordsACFilter {
	/** DFA入口 */
	private final DFANode dfaEntrance = new DFANode();;
	/** 要忽略的字符 */
	private final char[] ignoreChars = new char[0];
	/** 过滤时要被替换的字符 */
	private final char subChar = '*';

	/**
	 * 定义不进行小写转化的字符,在此表定义所有字符,其小写使用原值,以避免转小写后发生冲突:
	 * <ul>
	 * <li>char code=304,İ -> i,即İ的小写是i,以下的说明类似</li>
	 * <li>char code=8490,K -> k,</li>
	 * </ul>
	 */
	private final static char[] ignowLowerCaseChars = new char[] { 304, 8490 };

    public KeyWordsACFilter() {
    }

    /**
	 *
	 * param ignore
	 *            要忽略的字符,即在检测时将被忽略的字符
	 * param substitute
	 *            过滤时要被替换的字符
	 */


	/*public KeyWordsACFilter(char[] ignore, char substitute) {
		dfaEntrance = new DFANode();
		this.ignoreChars = new char[ignore.length];
		System.arraycopy(ignore, 0, this.ignoreChars, 0,
				this.ignoreChars.length);
		this.subChar = substitute;
	}*/

	public boolean initialize(String[] keyWords) {
		clear();
		// 构造DFA
		for (int s = 0; s < keyWords.length; s++) {
			String _keyword = keyWords[s];
			if (_keyword == null || (_keyword = _keyword.trim()).length() == 0) {
				continue;
			}
			char[] patternTextArray = _keyword.toCharArray();
			DFANode currentDFANode = dfaEntrance;
			for (int i = 0; i < patternTextArray.length; i++) {
				final char _c = patternTextArray[i];
				// 逐点加入DFA
				final Character _lc = toLowerCaseWithoutConfict(_c);
				DFANode _next = currentDFANode.dfaTransition.get(_lc);
				if (_next == null) {
					_next = new DFANode();
					currentDFANode.dfaTransition.put(_lc, _next);
				}
				currentDFANode = _next;
			}
			if (currentDFANode != dfaEntrance) {
				currentDFANode.isTerminal = true;
			}
		}

		buildFailNode();
		return true;
	}

	/**
	 * 构造失效节点：
	 * 一个节点的失效节点所代表的字符串是该节点所表示它的字符串的最大 部分前缀
	 */
	private final void buildFailNode() {
		// 以下构造失效节点
		List<DFANode> queues = new ArrayList<DFANode>();
		dfaEntrance.failNode = dfaEntrance;//
		for (Iterator<DFANode> it = dfaEntrance.dfaTransition.values()
				.iterator(); it.hasNext();) {
			DFANode node = it.next();
			node.level = 1;
			queues.add(node);
			node.failNode = dfaEntrance;// 失效节点指向状态机初始状态
		}
		DFANode curNode = null;
		DFANode failNode = null;
		while (!queues.isEmpty()) {
			curNode = queues.remove(0);// 该节点的失效节点已计算
			failNode = curNode.failNode;
			for (Iterator<Map.Entry<Character, DFANode>> it = curNode.dfaTransition
					.entrySet().iterator(); it.hasNext();) {
				Map.Entry<Character, DFANode> nextTrans = it.next();
				Character nextKey = nextTrans.getKey();
				DFANode nextNode = nextTrans.getValue();
				// 如果父节点的失效节点中有条相同的出边，那么失效节点就是父节点的失效节点
				while (failNode != dfaEntrance
						&& !failNode.dfaTransition.containsKey(nextKey)) {
					failNode = failNode.failNode;
				}
				nextNode.failNode = failNode.dfaTransition.get(nextKey);
				if (nextNode.failNode == null) {
					nextNode.failNode = dfaEntrance;
				}
				nextNode.level = curNode.level + 1;
				queues.add(nextNode);// 计算下一层

			}

		}
	}

	/**
	 * 基于AC状态机查找匹配，并根据节点层数覆写应过滤掉的字符
	 *
	 */
	public String filt(String s) {
		char[] input = s.toCharArray();
		char[] result = s.toCharArray();
		boolean _filted = false;

		DFANode currentDFANode = dfaEntrance;
		DFANode _next = null;
		int replaceFrom = 0;
		for (int i = 0; i < input.length; i++) {
			final Character _lc = this.toLowerCaseWithoutConfict(input[i]);
			_next = currentDFANode.dfaTransition.get(_lc);
			while (_next == null && currentDFANode != dfaEntrance) {
				currentDFANode = currentDFANode.failNode;
				_next = currentDFANode.dfaTransition.get(_lc);
			}
			if (_next != null) {
				// 找到状态转移，可继续
				currentDFANode = _next;
			}
			// 看看当前状态可退出否
			if (currentDFANode.isTerminal) {
				// 可退出，记录，可以替换到这里
				int j = i - (currentDFANode.level - 1);
				if (j < replaceFrom) {
					j = replaceFrom;
				}
				replaceFrom = i + 1;
				for (; j <= i; j++) {
					if (!this.isIgnore(input[j])) {
						result[j] = this.subChar;
						_filted = true;
					}
				}
			}
		}
		if (_filted) {
			return String.valueOf(result);
		} else {
			return s;
		}
	}


	public boolean contain(final String inputMsg) {
		char[] input = inputMsg.toCharArray();
		DFANode currentDFANode = dfaEntrance;
		DFANode _next = null;
		for (int i = 0; i < input.length; i++) {
			final Character _lc = this.toLowerCaseWithoutConfict(input[i]);
			_next = currentDFANode.dfaTransition.get(_lc);
			while (_next == null && currentDFANode != dfaEntrance) {
				currentDFANode = currentDFANode.failNode;
				_next = currentDFANode.dfaTransition.get(_lc);
			}
			if (_next != null) {
				// 找到状态转移，可继续
				currentDFANode = _next;
			}
			// 看看当前状态可退出否
			if (currentDFANode.isTerminal) {
				// 可退出，记录，可以替换到这里
				return true;
			}
		}

		return false;
	}

	/**
	 * 初始化时先调用此函数清理
	 */
	private void clear() {
		// 清理入口
		dfaEntrance.dfaTransition.clear();
	}

	/**
	 * 将指定的字符转成小写,如果与{@link #ignowLowerCaseChars}所定义的字符相冲突,则取原值
	 *
	 * @param c
	 * @return
	 */
	private char toLowerCaseWithoutConfict(final char c) {
		return (c == ignowLowerCaseChars[0] || c == ignowLowerCaseChars[1]) ? c
				: Character.toLowerCase(c);
	}

	/**
	 * 是否属于被忽略的字符
	 *
	 * @param c
	 * @return
	 */
	private boolean isIgnore(final char c) {
		for (int i = 0; i < this.ignoreChars.length; i++) {
			if (c == this.ignoreChars[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * DFA节点
	 *
	 *
	 */
	private static class DFANode {
		//是否终结状态的节点
		public boolean isTerminal;
		/** 保存小写字母，判断时用小写 */
		private final HashMap<Character, DFANode> dfaTransition;
		//不匹配时走的节点
		DFANode failNode;
		//节点层数
		int level;

		// public boolean canExit = false;
		public DFANode() {
			this.dfaTransition = new HashMap<Character, DFANode>();
			isTerminal = false;
			failNode = null;
			level = 0;
		}

	}

	String content = "1995年中共执政当局开始寻求强化法轮功的组织构架及与政府的关系。" +
			"中国政府的国家体委、公共健康部和气功科研会，访问李洪志，要求联合成立法轮功协会，但李洪志表示拒绝。" +
			"同年，气功科研会通过一项新规定，命令所有气功分会必须建立中国共产党党支部，但李洪志再次表示拒绝。" +
			"李洪志与中国气功科研会的关系在1996年持续恶化。" +
			"1996 年3月，法轮功因拒不接受中国气功协会新负责人在“气功团体内部收取会员费创收”和“成立中国共产党党支部组织”的要求，" +
			"主动申请退出中国气功协会和中国 气功科研会, 以独立非政府形式运作。" +
			"自此，李洪志及其法轮功脱离了中国气功协会中的人脉和利益交换，同时失去了功派在中国政府体制系统的保护。" +
			"法轮功申请退出中国气功协会，是与中国政府对气功的态度产生变化相对应的；" +
			"当时随气功激进反对者在政府部门中的影响力增加，中国政府开始控制和影响各气功组织。" +
			"90年代中期，中国政府主管的媒体开始发表文章批评气功。" +
			"法轮功起初并没有受批评，但在1996年3月退出中国气功协会后，失去了政府体制的保护。";

	String content2 = "我是习d近 DF平";

	//{政府=9, 李洪志=5, 法轮功=6, 中共=1, 共产党=2}
	@Test
	public void test02() throws IOException {
        KeyWordsACFilter keyWordsACFilter = new KeyWordsACFilter();
        List<String> words = LoadWords.load();
        keyWordsACFilter.initialize(words.toArray(new String[words.size()]));
//        List<String> search = search(content);
        String filt = keyWordsACFilter.filt(content);
        System.out.println(filt);
	}

}
