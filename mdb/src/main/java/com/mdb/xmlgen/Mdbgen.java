package com.mdb.xmlgen;

import com.mdb.xmlgen.utils.CachedFileOutputStream;
import com.mdb.xmlgen.utils.FileOperation;
import com.mdb.xmlgen.xmlparse.Mdb;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mdbgen {

	private static File xbeanDir;
	private static File tableDir;

	static PrintStream openXBeanFile(String classname) {
		return FileOperation.fopen(xbeanDir, classname + ".java");
	}

	static PrintStream openTableFile(String classname) {
		return FileOperation.fopen(tableDir, classname + ".java");
	}

	public static void make(Mdb mdb, File genDir) {
		tableDir = new File(new File(genDir, "table"), mdb.getName());
		xbeanDir = new File(new File(genDir, "xbean"), mdb.getName());
		CachedFileOutputStream.removeOtherFiles(genDir);
//		ForeignCircle.verify(zdb);
		mdb.getBeans().values().forEach(XbeanFormatter::make);

		mdb.getTables().values().forEach(TableShortcut::make);
		TableTables.make(mdb, mdb.getTables().values());
//		TableMeta.make(zdb);
	}
	
	public static void genSql(Mdb mdb, File sqlDir) throws Exception {
		if(!sqlDir.isDirectory())
			return;
		
		File initTableSql = new File(sqlDir, "init.sql");
		File truncateTableSql = new File(sqlDir, "truncate.sql");
		PrintWriter initWriter = new PrintWriter(new FileWriter(initTableSql, true));
		PrintWriter truncateWriter = new PrintWriter(new FileWriter(truncateTableSql, true));
		List<String> tableNames = new ArrayList<>(mdb.getTables().keySet());
		Collections.sort(tableNames);// 保证svn中变化，否则不排序rehash时候，会打乱顺序
		for(String tableName : tableNames) {
			initWriter.println("CREATE TABLE IF NOT EXISTS " + tableName
					+ "(k VARCHAR(128) NOT NULL PRIMARY KEY, v JSON NOT NULL) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_GENERAL_CI;");
			truncateWriter.println("TRUNCATE TABLE " + tableName + ";");
		}
		System.err.println(mdb.getName() + " genTable : " + mdb.getTables().size());
		initWriter.close();
		truncateWriter.close();
	}

}
