package com.youxi.db;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/4/2 14:43
 * @Description:
 */
public class SerializerUtil {
	public SerializerUtil() {
	}

	public static <T> byte[] encode(T object, Class<T> clazz) {
		Schema<T> schema = RuntimeSchema.getSchema(clazz);
		LinkedBuffer buffer = LinkedBuffer.allocate();
		return ProtostuffIOUtil.toByteArray(object, schema, buffer);
	}

	public static <T> T decode(byte[] bytes, Class<T> clazz) {
		T object;
		try {
			object = clazz.newInstance();
		} catch (Exception var4) {
			return null;
		}

		return decode(bytes, clazz, object);
	}

	public static <T> T decode(byte[] bytes, Class<T> clazz, T object) {
		Schema<T> schema = RuntimeSchema.getSchema(clazz);
		ProtostuffIOUtil.mergeFrom(bytes, object, schema);
		return object;
	}
}
