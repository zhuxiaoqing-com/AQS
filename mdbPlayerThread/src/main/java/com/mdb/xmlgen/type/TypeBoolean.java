package com.mdb.xmlgen.type;

public class TypeBoolean extends Type {

	TypeBoolean() {
		super("boolean");
	}

	@Override
	public boolean isConstType() {
		return true;
	}

	@Override
	public String toInitVal() {
		return "false";
	}
	
	

}
