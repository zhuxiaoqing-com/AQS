package com.mdb.xmlgen;


import com.mdb.util.StringUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import com.mdb.xmlgen.xmlparse.Table;

import java.io.PrintStream;

class TableShortcut {

	static void make(MdbMetadata mdbMetadata, Table table) {
		new TableShortcut(table).make(mdbMetadata);
	}

	private Table table;

	TableShortcut(Table table) {
		this.table = table;
	}

	void make(MdbMetadata mdbMetadata) {
		String name = table.getName();
		String classname = StringUtils.upper1(name);
		try (PrintStream ps = Mdbgen.openTableFile(classname)) {
			if(ps == null) {
				return;
			}
			String K = table.getKeyType().box();
			String V = table.getValueType().isXbean() ? table.getValueType().toVarType() : table.getValueType().box();
			String KV = "<" + K + ", " + V + ">";

			ps.println("package table." + mdbMetadata.getName() + ";");
			ps.println();
			ps.println("import com.mdb.Mdb;");
			ps.println("import com.mdb.TTable;");
			ps.println();
			ps.println("public class " + classname + " {");
			ps.println("	private " + classname + "() {");
			ps.println("	}");
			ps.println();

			ps.println("	public static boolean add(Mdb mdb, " + K + " key, " + V + " value) {");
			ps.println("		TTable" + KV + " tTable = mdb.getTTable(_Tables_." + classname + ".class);");
			ps.println("		return tTable.add(key, value);");
			ps.println("	}");
			ps.println();

			ps.println("	public static boolean add(Mdb mdb, " + K + " key, " + V + " value, boolean immediately) {");
			ps.println("		TTable" + KV + " tTable = mdb.getTTable(_Tables_." + classname + ".class);");
			ps.println("		return tTable.add(key, value, immediately);");
			ps.println("	}");
			ps.println();

			ps.println("	public static " + V + " get(Mdb mdb, " + K + " key) {");
			ps.println("		TTable" + KV + " tTable = mdb.getTTable(_Tables_." + classname + ".class);");
			ps.println("		return tTable.get(key);");
			ps.println("	}");
			ps.println();

			/*ps.println("	// 只读模式查询");
			ps.println("	public static " + V + " select(" + K + " key) {");
			ps.println("		return _Tables_.instance." + name + ".select(key);");
			ps.println("	}");
			ps.println();*/

			ps.println("	public static boolean remove(Mdb mdb, " + K + " key) {");
			ps.println("		TTable" + KV + " tTable = mdb.getTTable(_Tables_." + classname + ".class);");
			ps.println("		return tTable.remove(key);");
			ps.println("	}");
			ps.println();

			ps.println("}");
		}
	}
}
