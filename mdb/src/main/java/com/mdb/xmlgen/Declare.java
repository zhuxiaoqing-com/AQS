package com.mdb.xmlgen;



import com.mdb.util.StringUtils;
import com.mdb.xmlgen.type.*;
import com.mdb.xmlgen.xmlparse.Enum;

import java.io.PrintStream;
import java.util.Collection;

public class Declare {

	public enum Type {
		PUBLIC, PRIVATE
	}

	public static void makeEnum(Enum e, PrintStream ps, String prefix) {
		ps.println(prefix + e.toVariable());
	}

	public static void makeVar(Variable e, PrintStream ps, String prefix) {
		ps.println(prefix + e.toVarType() + e.getComment());
	}

	public static void makeConstruct(Variable e, PrintStream ps, String prefix) {
		if (!TypeName.isBaseType(e.getType()) || e.getType().equals("string")) {
			// 字符串和其他类型都需要初始化
			ps.println(prefix + e.toInitVal());
		}
	}

	// 深拷贝
	public static void makeDeepCopy(Variable e, PrintStream ps, String prefix) {
		ps.println(prefix + e.toCopy(null));
	}

	public static void makeGetter(Variable e, PrintStream ps, String prefix) {
		ps.println(String.format("%s@JSONField(name = \"%s\")",prefix,e.getAlias()));
		ps.println(String.format("%spublic %s get%s(){", prefix, e.getVarType().toVarType(), StringUtils.upper1(e.getName())));
		ps.println(String.format("%s%sreturn this.%s;", prefix, prefix, e.getName()));
		ps.println(prefix + "}");
		ps.println();
	}

	public static void makeSetter(Variable e, PrintStream ps, String prefix) {
		// 集合不能有set方法;
		if(e.getVarType().isCollection()) {
			return;
		}
		//所有字段都必须要SET
		ps.println(String.format("%s@JSONField(name = \"%s\")",prefix,e.getAlias()));
		ps.println(String.format("%spublic void set%s(%s _v_){", prefix, StringUtils.upper1(e.getName()), e.getVarType().toVarType()));
		ps.println(String.format("%s%sthis.%s = _v_;", prefix, prefix, e.getName()));
		if(!e.getVarType().isConstType()) {
			ps.println(prefix + prefix + String.format("_v_.setParent(this);", prefix, prefix, e.getName()));
		}
		ps.println(prefix + prefix + "makeModify();");
		ps.println(prefix + "}");
		ps.println();
//		if (e.getVarType().isConstType()) {
//			ps.println(String.format("%s@JSONField(name = \"%s\")",prefix,e.getAlias()));
//			ps.println(String.format("%spublic void set%s(%s _v_){", prefix, StringUtils.upper1(e.getName()), e.getVarType().toVarType()));
//			ps.println(String.format("%s%sthis.%s = _v_;", prefix, prefix, e.getName()));
//			ps.println(prefix + "}");
//			ps.println();
//		}else if(e.getVarType().isXbean()) {
//			ps.println(String.format("%s@JSONField(name = \"%s\")",prefix,e.getAlias()));
//			ps.println(String.format("%spublic void set%s(%s _v_){", prefix, StringUtils.upper1(e.getName()), e.getVarType().toVarType()));
//			ps.println(String.format("%s%sthis.%s = _v_;", prefix, prefix, e.getName()));
//			ps.println(prefix + "}");
//			ps.println();
//		}
	}


	public static void makeFieldAnyModify(Collection<? extends Variable> variables, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public boolean fieldAnyModify() {");
		ps.println(prefix + "\tif(only_isModify()) {");
		ps.println(prefix + "\t\treturn true");
		ps.println(prefix + "\t}");


		/*if(new XBean().fieldAnyModify()){
			Mdb.logger.error("makeFieldAnyModify false fieldModify true class {} ", getClass().getName(), new RuntimeException());
			return true;
		}*/

		variables.forEach(var -> {
			if(!var.getVarType().isConstType()) {
				ps.println(prefix + "\tif(" + var.getName() + ".fieldAnyModify()) {");
				ps.println(prefix + "\t\tMdb.logger.error(\"makeFieldAnyModify false fieldModify true class {}\",getClass(), new RuntimeException());");
				ps.println(prefix + "\treturn true;");
				ps.println(prefix + "\t}");
			}

		});

		ps.println(prefix + "\treturn false;");
		ps.println(prefix + "}");
		ps.println();
	}


	public static void makeClearModify(Collection<? extends Variable> variables, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public void clearModify() {");
		ps.println(prefix + "\tsetModifyValue(isModifyTrue, false);");

		variables.forEach(var -> {
			if(var.getVarType().isXbean()) {
				ps.println(prefix + "\t" + var.getName() + ".setModifyValue(isModifyTrue, false);");
			}
		});
		ps.println(prefix + "}");
		ps.println();
	}


	public static void makeCheckFieldVersion(Collection<? extends Variable> variables, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public boolean checkFieldVersion(long checkPointCount) {");
		/*if(new XBean().fieldAnyModify()){
			Mdb.logger.error("makeFieldAnyModify false fieldModify true class {} ", getClass().getName(), new RuntimeException());
			return true;
		}*/

		variables.forEach(var -> {
			if(!var.getVarType().isConstType()) {
				ps.println(prefix + "\tif(" + var.getName() + ".checkVersion(checkPointCount)) {");
				//ps.println(prefix + "\t\tMdb.logger.error(\"checkFieldVersion checkVersion true checkPointCount {} currVersion {} class {} \", checkPointCount, getVersion(checkPointCount), getClass().getName(), new RuntimeException());");
				ps.println(prefix + "\t\tMdb.logger.error(\"checkFieldVersion checkVersion true checkPointCount {} currVersion {} class {} \",");
				ps.println(prefix + "\t\t\tcheckPointCount, getVersion(checkPointCount), getClass().getName(), new RuntimeException());");
				ps.println(prefix + "\t\treturn true;");
				ps.println(prefix + "\t}");
			}
		});

		variables.forEach(var -> {
			if(!var.getVarType().isConstType()) {
				ps.println(prefix + "\tif(" + var.getName() + ".checkFieldVersion(checkPointCount)) {");
				ps.println(prefix + "\t\treturn true;");
				ps.println(prefix + "\t}");
			}
		});

		ps.println(prefix + "\treturn false;");
		ps.println(prefix + "}");
		ps.println();
	}


	public static void makeEquals(Collection<? extends Variable> variables, String name, PrintStream ps,
			String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public boolean equals(Object _o1_) {");
		ps.println(prefix + "	if (_o1_ == this) return true;");
		ps.println(prefix + "	if (_o1_ instanceof " + name + ") {");
		if (!variables.isEmpty())
			ps.println(prefix + "		" + name + " _o_ = (" + name + ")_o1_;");
		for (Variable var : variables) {
			if(var.getVarType().isConstType() && !(var.getVarType() instanceof TypeString)) {
				ps.println("\t\t\tif (this." + var.getName() + " != _o_." + var.getName() + ") return false;");
			}else {
				ps.println("\t\t\tif (!this." + var.getName() + ".equals(_o_." + var.getName() + ")) return false;");
			}
		}
		ps.println(prefix + "		return true;");
		ps.println(prefix + "	}");
		ps.println(prefix + "	return false;");
		ps.println(prefix + "}");
		ps.println();
	}

	public static void makeHashCode(Collection<? extends Variable> variables, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public int hashCode() {");
		ps.println(prefix + "\tint _h_ = 0;");
		variables.forEach(var -> {
			String str = null;
			if(var.getVarType() instanceof TypeLong) {
				str = "(int)(" + var.getName() + " ^ (" + var.getName() + " >>> 32))";
			}else if(var.getVarType() instanceof TypeBoolean){
				str = "(this." + var.getName() + " ? 1231 : 1237)";
			}else if(var.getVarType() instanceof TypeFloat){
				str = "Float.floatToIntBits(this." + var.getName() + ")";
			}else if(var.getVarType() instanceof TypeDouble) {
				str = "Double.valueOf(" + var.getName() + ").hashCode()";
			}else if(!var.getVarType().isConstType() || var.getVarType() instanceof TypeString){
				str = "this." + var.getName() + ".hashCode()";
			}else{
				str = "(int)(this." + var.getName() + ")";
			}
			
			ps.println(prefix + "	_h_ += _h_ * 31 + " + str + ";");
		});
		ps.println(prefix + "	return _h_;");
		ps.println(prefix + "}");
		ps.println();
	}

	public static void makeToString(Collection<Variable> variables, PrintStream ps, String prefix) {
		ps.println(prefix + "@Override");
		ps.println(prefix + "public String toString() {");
		ps.println(prefix + "	StringBuilder _sb_ = new StringBuilder(super.toString());");
		ps.println(prefix + "	_sb_.append(\"=(\");");
		variables.forEach(var -> {
			if(var.getVarType() instanceof TypeString) {
				ps.println(prefix + "\t_sb_.append(\"T\").append(" + var.getName() + ".length()).append(\",\");");
			}else {
				ps.println(prefix + "\t_sb_.append(" + var.getName() + ").append(\",\");");
			}
			
		});
		
		ps.println(prefix + "	_sb_.append(\")\");");
		ps.println(prefix + "	return _sb_.toString();");
		ps.println(prefix + "}");
		ps.println();
	}

}
