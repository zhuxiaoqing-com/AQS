package com.mdb.xmlgen.utils;

import com.mdb.xmlgen.xmlparse.MdbMetadata;
import com.mdb.xmlgen.xmlparse.Table;
import com.mdb.xmlgen.Variable;

import java.util.*;


class ForeignCircle {

	static void verify(MdbMetadata zdb) {
		for (Table t : zdb.getTables().values())
			new ForeignCircle(zdb, t).detect(t);
	}

	private MdbMetadata mdb;
	private Table origin;
	// order by add
	private Map<String, Set<String>> detected = new LinkedHashMap<String, Set<String>>();

	ForeignCircle(MdbMetadata mdb, Table origin) {
		this.mdb = mdb;
		this.origin = origin;
	}

	Set<String> foreigns(Table table) {
		Set<String> foreigns = getTableForeigns(table);
		detected.put(table.getName(), foreigns);
		if (foreigns.contains(origin.getName())) {
			for (Map.Entry<String, Set<String>> e : detected.entrySet()) {
				if (false == e.getValue().isEmpty())
					System.err.println(origin.getName() + " " + e);
			}
			throw new IllegalStateException("foreign circle found.");
		}
		return foreigns;
	}

	Set<String> getTableForeigns(Table table) {
		Set<String> foreigns = new HashSet<String>();
//		Foreign.Conf f = new Foreign.Conf(table.getForeign(), "");
//		if (f.getKey() != null)
//			foreigns.add(f.getKey());
//		if (f.getValue() != null)
//			foreigns.add(f.getValue());
//
//		Set<Type> types = new HashSet<Type>();
//		table.getKeyType().depends(types);
//		table.getValueType().depends(types);
//		for (Type type : types) {
//			collectBeanForeigns(((Xbean) type).getVariables(), foreigns);
//		}
		return foreigns;
	}

	void collectBeanForeigns(List<Variable> variables, Set<String> foreigns) {
//		for (Variable var : variables) {
//			Foreign.Conf f = new Foreign.Conf(var.getForeign(), "");
//			if (null != f.getKey())
//				foreigns.add(f.getKey());
//			if (null != f.getValue())
//				foreigns.add(f.getValue());
//		}
	}

	boolean contains(Table table) {
		return detected.containsKey(table.getName());
	}

	void detect(Table table) {
		if (null == table)
			return; // maybe null. see TypeBinary

		if (contains(table))
			return;

		Set<String> foreigns = foreigns(table);
		if (foreigns.contains(table.getName()))
			throw new IllegalStateException(
					"foreign to self is not supported!" + "NO null value in ZDB. table=" + table.getName());

//		for (String foreign : foreigns)
//			detect(mdb.getTable(foreign));
	}

}
