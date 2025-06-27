package com.example.common.stringMatch.ac;

import cn.hutool.dfa.WordTree;
import com.example.common.stringMatch.LoadWords;
import com.example.datastruture.stringmatch.ac.AC1;
import com.example.datastruture.stringmatch.ac.DeepWordTree;
import com.example.datastruture.stringmatch.ac.SimpleStopChar;
import com.example.demo4.TestUtil;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/9/17 11:57
 * @Description:
 */
public class ACTest01 {
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
	String content3 = "我是习共近产党平";


	@Test
	public void test01() {
		List<String> words = LoadWords.load();

		KeyWordsACFilter a1 = new KeyWordsACFilter();
		a1.initialize(words.toArray(new String[0]));

		DeepWordTree a2 = new DeepWordTree();
		DeepWordTree.setMatchDeep(2);
		DeepWordTree.setCharFilter(SimpleStopChar::isNotStopChar);
		a2.addWords(words);

		AC1 a3 = new AC1();
		a3.buildTree(words);

		String content = this.content;
		content = content2;
		content = content3;

		System.out.println(a1.contain(content));
		System.out.println(a2.isMatch(content));
		System.out.println(a3.match(content));


		System.out.println(a1.filt(content));
		System.out.println(a2.filterChatMsg(content, "*"));
		System.out.println(a3.replace(content, '*'));

		//System.out.println(a1.filt(content).equals(a3.replace(content, '*')));


		String finalContent = content;
		TestUtil.testTime(()-> a1.contain(finalContent), 100000, "a1");
		TestUtil.testTime(()-> a2.isMatch(finalContent), 100000, "a2");
		TestUtil.testTime(()-> a3.match(finalContent), 100000, "a3");


		TestUtil.testTime(()-> a1.filt(finalContent), 100000, "a1");
		TestUtil.testTime(()-> a2.filterChatMsg(finalContent,"*"), 100000, "a2");
		TestUtil.testTime(()-> a3.replace(finalContent,'*'), 100000, "a3");

	}
}
