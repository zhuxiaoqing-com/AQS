package com.mdb.xmlgen.type;

public final class TypeInt extends Type {

	TypeInt() {
		super("int");
	}

	@Override
	public boolean isConstType() {
		return true;
	}

	@Override
	public String box() {
		return "Integer";
	}

	@Override
	public String toInitVal() {
		return "0";
	}
	
}
