package com.mdb.xmlgen.type;

public final class TypeFloat extends Type {

	TypeFloat() {
		super("float");
	}

	@Override
	public boolean isConstType() {
		return true;
	}

	@Override
	public String toInitVal() {
		return "0.f";
	}

	@Override
	public String toDefault(String v) {
		return v + "f";
	}
	
	
	
}
