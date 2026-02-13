package com.mdb.xmlgen.type;

public final class TypeMap extends Type {
	private final Type keyType;
	private final Type valueType;
	private final TypeSet keySetType;

	public TypeMap(Type keyType, Type valueType) {
		this.keyType = keyType;
		this.valueType = valueType;
		this.keySetType = new TypeSet(keyType);
	}

	@Override
	public boolean isConstType() {
		return false;
	}

	public Type getValueType() {
		return valueType;
	}

	public Type getKeyType() {
		return keyType;
	}

	public TypeSet getKeySetType() {
		return keySetType;
	}

	@Override
	public String toVarType() {
		//return String.format("java.util.concurrent.ConcurrentHashMap<%s, %s>", keyType.box(), valueType.box());
		return String.format("com.mdb.collection.ConcurrentHashMapX<%s, %s>", keyType.box(), valueType.box());
	}

	@Override
	public String toInitVal() {
		//return String.format("new java.util.concurrent.ConcurrentHashMap<%s, %s>();", keyType.box(), valueType.box());
		return String.format("new com.mdb.collection.ConcurrentHashMapX<%s, %s>();", keyType.box(), valueType.box());
	}

	@Override
	public String toCopy(Type type) {

		String str = toInitVal() + "\n\t\t";
		if (valueType.isXbean()) {
			return str
					+ String.format("_o_.%s.forEach((_k_, _v_) -> this.%s.put(_k_, new %s(_v_)));", type.getName(), type.getName(), valueType.box());
		} else {
			return str + String.format("_o_.%s.forEach((_k_, _v_) -> this.%s.put(_k_, _v_));", type.getName(), type.getName());
		}
	}

}
