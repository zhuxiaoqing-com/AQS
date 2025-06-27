package com.example.common.stringMatch.截取两个字符串之间的值;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/6 19:51
 * @Description:
 */
public class StrSplitTest01 {
	@Test
	public void test01() {
		String str = "abc<icon>def</icon>deftfh<icon>a</icon>b<icon>c</icon>d<icon>e</icon>f<icon>g</icon>";

		Pattern p = Pattern.compile("(\\w*)<icon>(\\w*)</icon>(\\w*)");
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				System.out.println(m.group(i));
			}

		}
	}
}
