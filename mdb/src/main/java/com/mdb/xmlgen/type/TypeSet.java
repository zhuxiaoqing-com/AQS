package com.mdb.xmlgen.type;

public final class TypeSet extends Type {
	private final Type valueType;

	TypeSet(Type valueType) {
		super();
		this.valueType = valueType;
	}

	@Override
	public boolean isConstType() {
		return false;
	}
	public  boolean isCollection(){
		return true;
	}
	public Type getValueType() {
		return valueType;
	}

	@Override
	public String toVarType() {
		//return String.format("java.util.concurrent.CopyOnWriteArraySet<%s>", valueType.box());
		return String.format("com.mdb.collection.ConcurrentHashSetX<%s>", valueType.box());
	}

	@Override
	public String toInitVal() {
		//return String.format("new java.util.concurrent.CopyOnWriteArraySet<%s>();", valueType.box());
		return String.format("new com.mdb.collection.ConcurrentHashSetX<%s>(this);", valueType.box());
	}

	@Override
	public String toCopy(Type type) {
		String str = toInitVal() + "\n\t\t"; 
		if(valueType.isXbean()) {
			return str + String.format("_o_.%s.forEach(_v_ -> this.%s.add(new %s(_v_)));", type.getName(), type.getName(), valueType.box());
		}else {
			return str + String.format("_o_.%s.forEach(_v_ -> this.%s.add(_v_));", type.getName(), type.getName());
		}
	}

}
