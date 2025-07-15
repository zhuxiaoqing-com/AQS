package com.mdb.xmlgen.type;

public final class TypeShort extends Type {

	TypeShort() {
		super("short");
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
