package com.mdb.xmlgen.xmlparse;

import com.mdb.util.ElementHelper;
import com.mdb.xmlgen.Naming;
import com.mdb.xmlgen.Variable;
import com.mdb.xmlgen.Xbean;
import com.mdb.xmlgen.type.Type;
import com.mdb.xmlgen.type.TypeName;
import org.w3c.dom.Element;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class Enum extends Naming {

	private long value;
	private String comment;
	private String strtype;
	private Type valueType = null;
	private static final Set<String> typeset = new HashSet<>(Arrays.asList("byte", "short", "int", "long"));

	private Xbean xbean;

	public Enum(Xbean xbean, Element self) {
		super(self);
		this.xbean = xbean;
		initialize(self);
	}

	private void initialize(Element self) {
		ElementHelper eh = new ElementHelper(self);
		value = eh.getLong("value");
		strtype = eh.getString("type", "int");
		if (!typeset.contains(strtype)) {
			throw new RuntimeException("invalid enum type in bean : " + xbean.getName());
		}
		comment = Variable.extractComment(self);
		valueType = TypeName.parseType(xbean.getMdbMetadata(), strtype, "", "");
	}

	public String toVariable() {
		return String.format("public static final %s %s = %s;  %s", strtype, getName(), getValue(), getComment());
	}

	public Type getType() {
		return valueType;
	}

	public long getValue() {
		return value;
	}

	public String getComment() {
		return comment;
	}

}
