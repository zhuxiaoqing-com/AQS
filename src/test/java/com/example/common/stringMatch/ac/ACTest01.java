package com.example.common.stringMatch.ac;

import com.example.common.stringMatch.LoadWords;
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


	@Test
	public void test01() {
		KeyWordsACFilter keyWordsACFilter = new KeyWordsACFilter();
		List<String> words = LoadWords.load();
		keyWordsACFilter.initialize(words.toArray(new String[words.size()]));
		//        List<String> search = search(content);

		SensitiveFilterImprove sensitiveFilterImprove = new SensitiveFilterImprove();
		sensitiveFilterImprove.buildTree(words);


		TestUtil.testTime(()-> keyWordsACFilter.contain(content), 100000, "a");
		TestUtil.testTime(()-> sensitiveFilterImprove.search(content), 100000, "b");


	}
}
