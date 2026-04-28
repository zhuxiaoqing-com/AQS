package com.mdb.xmlgen;


import com.mdb.util.StringUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import com.mdb.xmlgen.xmlparse.Table;

import java.io.PrintStream;
import java.util.Collection;

class TableTables {

	static void make(MdbMetadata mdb, Collection<Table> tables) {
		String classname = "_Tables_";
		try (PrintStream ps = Mdbgen.openTableFile(classname)) {

			ps.println("package table." + mdb.getName() + ";");
			ps.println();
			if (!tables.isEmpty()) {
				ps.println("import com.mdb.util.FastJson;");
			}
			ps.println();
			ps.println("final class _Tables_ {");

			for (Table table : tables)
				define(table, ps);
			ps.println();
			ps.println("}");
		}
	}

	static void define(Table table, PrintStream ps) {
		String name = table.getName();
		String k = table.getKeyType().box();
		String v = table.getValueType().isXbean() ? table.getValueType().toVarType() : table.getValueType().box();
		String t = "com.mdb.TTable<" + k + ", " + v + ">";

		ps.println("	static class " + StringUtils.upper1(name) + " extends " + t + " {");
		ps.println("		@Override");
		ps.println("		public String getName() {");
		ps.println("			return " + StringUtils.quote(name) + ";");
		ps.println("		}");
		ps.println();

		ps.println("		@Override");
		ps.println("		protected " + v + " newValue() {");
		if(table.getValueType().isXbean()) {
			ps.println("			"+ v + " value = new " + table.getValueType().toVarType()+ "();");
		}else {
			ps.println("			"+ v + " value = " + table.getValueType().toInitVal()+ ";");
		}
		ps.println("			return value;");
		ps.println("		}");
		ps.println();

		/*
		ps.println("		" + v + " insert(" + k + " key, " + v + " value, boolean immediately) {");
		ps.println("			return add(key, value, immediately) ? value : null;");
		ps.println("		}");
		ps.println();
		
		
		ps.println("		" + v + " insert(" + k + " key, " + v + " value) {");
		ps.println("			return add(key, value) ? value : null;");
		ps.println("		}");
		ps.println();
		ps.println("		@Override");
		ps.println("		protected " + v + " deepCopy(" + v + " v){");
		ps.println("            throw new RuntimeException(\"need impl\");");
		ps.println("		}");
		ps.println();


		ps.println("		boolean delete(" + k + " key) {");
		ps.println("			return remove(key);");
		ps.println("		}");
		ps.println();
		*/

		ps.println("		@Override");
		ps.println("		protected String marshalKey(" + k + " key) {");
		ps.println("			return FastJson.toJSONString(key);");
		ps.println("		}");
		ps.println();
		
		ps.println("		@Override");
		ps.println("		protected String marshalValue(" + v + " value) {");
		ps.println("			return FastJson.toJSONString(value);");
		ps.println("		}");
		ps.println();
		
		ps.println("		@Override");
		ps.println("		protected "+ k + " unmarshalKey(String os) {");
		ps.println("			return FastJson.parseObject(os, " + k + ".class);");
		ps.println("		}");
		ps.println();

		ps.println("		@Override");
		ps.println("		protected "+ v + " unmarshalValue(String os) {");
		ps.println("			return FastJson.parseObject(os, " + v + ".class);");
		ps.println("		}");
		ps.println();
		
		ps.println("	};");
		ps.println();
		//ps.println("	" + StringUtils.upper1(name) + " " + name + " = new " + StringUtils.upper1(name) + "();");
		ps.println();
	}
}
