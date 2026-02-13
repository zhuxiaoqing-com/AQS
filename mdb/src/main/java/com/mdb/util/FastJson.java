package com.mdb.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FastJson {

	public static <T> T parseObject(Object text, Class<T> clazz) {
		if (text == null) {
			return null;
		}
		if (text.toString().length() == 0) {
			return null;
		}
		return JSON.parseObject(text.toString(), clazz);
	}

	@SuppressWarnings("unchecked")
	public static String toJSONString(Object object) {
		PropertyFilter profilter = new PropertyFilter() {
			@Override
			public boolean apply(Object object, String name, Object param) {
				if(object instanceof Map){
					//如果map类型，并且value不是对象类型，不过滤
					return true;
				}
				// 过滤数值类型，值如果为0，不参加转json
				// 过滤map,set,list类型，如果没有值，不参与转json
				if (param instanceof Integer) {
					int value = ((Integer) param).intValue();
					if (value == 0) {
						return false;
					}
				} else if (param instanceof Double) {
					double d = ((Double) param).doubleValue();
					if (d == 0) {
						return false;
					}
				} else if (param instanceof Float) {
					float f = ((Float) param).floatValue();
					if (f == 0) {
						return false;
					}
				} else if (param instanceof Long) {
					long l = ((Long) param).longValue();
					if (l == 0) {
						return false;
					}
				}else if (param instanceof List) {
					List<Object> list = (List<Object>)param;
					if(list.size() == 0){
						return false;
					}
				}else if (param instanceof Set) {
					Set<Object> set = (Set<Object>)param;
					if(set.size() == 0){
						return false;
					}
				}else if (param instanceof Map) {
					Map<Object,Object> map = (Map<Object, Object>) param; 
					if(map.size() == 0){
						return false;
					}
				}else if (param instanceof Boolean) {
					boolean b = ((Boolean) param).booleanValue();
					return b;
				}
				return true;
			}
		};
		return JSON.toJSONString(object,profilter, SerializerFeature.WriteNonStringKeyAsString);
	}
	
	static class Ta{
		public int id;
		public String name;
	}
	
	public static void main(String[] args) {
		Ta t = new Ta();
		t.id = 1;
		t.name = "nnmm";
		Map<Integer, Ta> map = new HashMap<>();	
		map.put(1, t);
		System.err.println(toJSONString(map));
		
	}

}
