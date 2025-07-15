package com.mdb.xmlgen.type;

public final class TypeDouble extends Type {

	TypeDouble() {
		super("double");
	}

	@Override
	public boolean isConstType() {
		return true;
	}

	@Override
	public String toInitVal() {
		return "0.0";
	}
	
}
