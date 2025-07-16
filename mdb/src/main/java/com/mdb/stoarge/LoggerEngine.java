package com.mdb.stoarge;

public interface LoggerEngine {
	
	void dropTables(String[] tableNames) throws Exception;

	void close();
}
