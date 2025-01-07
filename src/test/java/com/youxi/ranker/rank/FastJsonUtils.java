package com.youxi.ranker.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonUtils {

	public static <T> T parseObject(Object text, Class<T> clazz) {
		if (text == null) {
			return null;
		}
		if (text.toString().length() == 0) {
			return null;
		}
		return JSON.parseObject(text.toString(), clazz);
	}

	public static String toJSONString(Object object) {
		return JSON.toJSONString(object, SerializerFeature.WriteNonStringKeyAsString);
	}
}
