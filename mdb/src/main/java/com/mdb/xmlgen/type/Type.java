package com.mdb.xmlgen.type;

import com.mdb.utils.StringUtils;
import com.mdb.xmlgen.Naming;
import com.mdb.xmlgen.Xbean;
import org.w3c.dom.Element;

public abstract class Type extends Naming {

	Type(Element self, String key) {

	}

	public Type() {

	}

	public Type(Element self) {
		super(self);
	}

	Type(String name) {
		super(name);
	}
	
	
	public boolean isXbean() {
		return this instanceof Xbean;
	}

	public abstract boolean isConstType();

	// 自动封箱，int需要的单独处理
	public String box() {
		return StringUtils.upper1(getName());
	}

	public String toVarType() {
		return getName();
	}
	
	public String toDefault(String v) {
		return v;
	}

	// 初始化
	public String toInitVal() {
		return null;
	}
	
	// 深拷贝
	public String toCopy(Type type) {
		return String.format("_o_.%s;", type.getName());
	}

}
