package com.mdb.xmlgen.type;

public final class TypeByte extends Type {

	TypeByte() {
		super("byte");
	}

	@Override
	public boolean isConstType() {
		return true;
	}

	@Override
	public String toInitVal() {
		return "0";
	}

}
