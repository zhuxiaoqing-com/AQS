package com.mdb.xmlgen;

import com.mdb.xmlgen.xmlparse.Enum;
import com.mdb.xmlgen.xmlparse.MdbMetadata;

import java.io.PrintStream;
import java.util.List;

class XbeanFormatter {
	public static void make(Xbean xbean) {
		try (PrintStream ps = Mdbgen.openXBeanFile(xbean.getName())) {
			if (ps == null) {
				return;
			}
			new XbeanFormatter(xbean, ps).make();
		}
	}

	private final Xbean xbean;

	private final PrintStream ps;

	private final List<Variable> variables;

	private XbeanFormatter(Xbean xbean, PrintStream ps) {
		this.xbean = xbean;
		this.ps = ps;
		this.variables = xbean.getVariables();
	}

	private void make() {
		String cls = xbean.getName();
		MdbMetadata mdbMetadata = xbean.getMdbMetadata();
		ps.println("package xbean." + mdbMetadata.getName() + ";");
		ps.println("import com.alibaba.fastjson.annotation.JSONField;");
		ps.println();
		ps.println();
		ps.println("public final class " + cls + " extends warrior.mdb.XBean {");
		List<Enum> enums = xbean.getEnums();
		enums.forEach(e -> Declare.makeEnum(e, ps, "    "));
		if (!enums.isEmpty())
			ps.println();
		variables.forEach(var -> Declare.makeVar(var, ps, "    "));
		ps.println();
		ps.println("    " + cls + "(warrior.mdb.XBean _xp_) {");
		ps.println("        super(_xp_);");
		variables.forEach(var -> Declare.makeConstruct(var, ps, "\t\t"));
		ps.println("	}");
		ps.println();
		ps.println("	public " + cls + "() {");
		ps.println("		this((warrior.mdb.XBean)null);");
		ps.println("	}");
		ps.println();

		ps.println("	public " + cls + "(" + cls + " _o_) {");
		ps.println("		this(_o_, null);");
		ps.println("	}");
		ps.println();
		ps.println("	" + cls + "(" + cls + " _o_, warrior.mdb.XBean _xp_) {");
		ps.println("		super(_xp_);");
		variables.forEach(var -> Declare.makeDeepCopy(var, ps, "		"));
		ps.println("	}");
		ps.println();

		// deepCopy

		ps.println("	public void copyFrom(" + cls + " _o_) {");
		variables.forEach(var -> Declare.makeDeepCopy(var, ps, "        "));
		ps.println("	}");
		ps.println();

//		ps.println("	@Override");
//		ps.println("	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {");
//		ps.println("	}");
		ps.println();

		// getter setter
		variables.forEach(var -> Declare.makeGetter(var, ps, "	"));
		variables.forEach(var -> Declare.makeSetter(var, ps, "	"));
		//Declare.makeFieldAnyModify(xbean.getVariables(), ps, "	");
		//Declare.makeClearModify(xbean.getVariables(), ps, "	");
		Declare.makeCheckFieldVersion(xbean.getVariables(), ps, "	");
		// equals
		Declare.makeEquals(variables, cls, ps, "	");
		Declare.makeHashCode(xbean.getVariables(), ps, "	");
		Declare.makeToString(xbean.getVariables(), ps, "	");
//		if (xbean.isConstType())
//			CompareTo.make(xbean, ps, "	");
		ps.println("}");
	}
}
