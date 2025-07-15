package com.mdb.xmlgen.type;

public final class TypeLong extends Type {

	TypeLong() {
		super("long");
	}

	@Override
	public boolean isConstType() {
		return true;
	}

	@Override
	public String toInitVal() {
		return "0L";
	}

}
