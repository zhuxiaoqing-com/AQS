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
			ps.println("public class " + classname + " {");
			ps.println("	private " + classname + "() {");
			ps.println("	}");
			ps.println();

			ps.println("	public static boolean add(" + K + " key, " + V + " value) {");
			makeFind(mdbMetadata.getName(),ps, V,"add",false);
			ps.println("		return _Tables_.instance." + name + ".insert(key, value) != null;");
			ps.println("	}");
			ps.println();

			ps.println("	public static boolean add(" + K + " key, " + V + " value, boolean immediately) {");
			makeFind(mdbMetadata.getName(),ps, V,"add immediately ",true);
			ps.println("		return _Tables_.instance." + name + ".insert(key, value, immediately) != null;");
			ps.println("	}");
			ps.println();

			ps.println("	public static " + V + " get(" + K + " key) {");
			makeFind(mdbMetadata.getName(),ps, V,"get",false);
			ps.println("		return _Tables_.instance." + name + ".get(key);");
			ps.println("	}");
			ps.println();

			/*ps.println("	// 只读模式查询");
			ps.println("	public static " + V + " select(" + K + " key) {");
			ps.println("		return _Tables_.instance." + name + ".select(key);");
			ps.println("	}");
			ps.println();*/

			ps.println("	public static boolean remove(" + K + " key) {");
			makeFind(mdbMetadata.getName(),ps, V,"remove",false);
			ps.println("		return _Tables_.instance." + name + ".delete(key);");
			ps.println("	}");
			ps.println();

			ps.println("	public static com.mdb.TTable" + KV + " getTable() {");
			ps.println("		return _Tables_.instance." + name + ";");
			ps.println("	}");
			ps.println();

			ps.println("}");
		}
	}
	
	void makeFind(String mdbName,PrintStream ps,String V, String function,boolean immediately){
		if(mdbName.indexOf("logic") != -1){
			if(immediately == true){
				ps.println("		if (!com.mdb.Mdb.getInstance().canOperate(key) && immediately == false)");
			}else{
				ps.println("		if (!com.mdb.Mdb.getInstance().canOperate(key))");
			}
			ps.println("			throw new RuntimeException(\"Invalid Operation " + V + " "+function+" key:\" + key);");
		}
	}
}
