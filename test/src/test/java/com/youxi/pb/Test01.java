package com.youxi.pb;

import com.google.protobuf.Any;
import com.google.protobuf.MessageLite;
import org.junit.Test;

public class Test01 {
	@Test
	public void a() {
		// 可以不换行的输出pb数据
		// https://www.lmlphp.com/user/152273/article/item/2876790/
		// TextFormat.shortDebugString(message)
		Any.Builder builder = Any.newBuilder();
	}
}
