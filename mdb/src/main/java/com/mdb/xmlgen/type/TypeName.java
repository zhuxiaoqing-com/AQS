package com.mdb.xmlgen.type;

import com.mdb.xmlgen.xmlparse.MdbMetadata;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeName {

	public static List<String> BASE_TYPE = Arrays.asList("int", "long", "short", "byte", "boolean", "float", "double",
			"string");

	public static List<String> COMPLEX_TYPE = Arrays.asList("map", "list", "set", "vector");

	private static Map<String, Class<? extends Type>> typeMap = new HashMap<>();

	static {
		typeMap.put("int", TypeInt.class);
		typeMap.put("long", TypeLong.class);
		typeMap.put("short", TypeShort.class);
		typeMap.put("byte", TypeByte.class);
		typeMap.put("boolean", TypeBoolean.class);
		typeMap.put("float", TypeFloat.class);
		typeMap.put("double", TypeDouble.class);
		typeMap.put("string", TypeString.class);
		typeMap.put("map", TypeMap.class);
		typeMap.put("list", TypeList.class);
		typeMap.put("set", TypeSet.class);
		typeMap.put("vector", TypeVector.class);
	}

	// 是否基础类型
	public static boolean isBaseType(String type) {
		return BASE_TYPE.contains(type);
	}

	// 是否合法类型
	public static boolean isValidType(String type) {
		return BASE_TYPE.contains(type) || COMPLEX_TYPE.contains(type);
	}

	// 是否是复合类型
	public static boolean isComplexType(String type) {
		return COMPLEX_TYPE.contains(type);
	}

	// 是否是xbean
	public static boolean isXbean(MdbMetadata mdbMetadata, String type) {
		return mdbMetadata.getBeans().containsKey(type);
	}

	public static Type parseType(MdbMetadata mdbMetadata, String type, String key, String value) {
		try {
			if (isBaseType(type)) {
				Class<? extends Type> baseClass = typeMap.get(type);
				return baseClass.newInstance();
			}
			if (isComplexType(type)) {
				Class<? extends Type> complexClass = typeMap.get(type);
				if (type.equals("map")) {
					Type keyType = parseType(mdbMetadata, key, "", "");
					Type valueType = parseType(mdbMetadata, value, "", "");
					Constructor<? extends Type> constructor = complexClass.getDeclaredConstructor(Type.class,
							Type.class);
					return constructor.newInstance(keyType, valueType);
				} else {
					Type valueType = parseType(mdbMetadata, value, "", "");
					Constructor<? extends Type> constructor = complexClass.getDeclaredConstructor(Type.class);
					return constructor.newInstance(valueType);
				}
			}
			if (isXbean(mdbMetadata, type)) {
				return mdbMetadata.getBeans().get(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("parseType error ...", e);
		}
		try {
			throw new RuntimeException("tp : " + type + " k : " + key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("mdb.xml 配置错误，请检查xbena 和 table相关引用配置!!!");
		return null;
	}

}
