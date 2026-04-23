package com.mdb.xmlgen.type;

public final class TypeString extends Type {

	TypeString() {
		super("string");
	}

	@Override
	public boolean isConstType() {
		return true;
	}

	@Override
	public String toVarType() {
		return "String";
	}

	@Override
	public String toInitVal() {
		return "\"\";";
	}

}
