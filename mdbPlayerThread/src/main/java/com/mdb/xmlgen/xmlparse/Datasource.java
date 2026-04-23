package com.mdb.xmlgen.xmlparse;

import com.mdb.util.ElementHelper;
import org.w3c.dom.Element;

/**
 * 缓存配置信息
 * 
 * @author Eric.Ma
 *
 */
public class Datasource {

	private String dbHome;

	private String dataBase;

	private int minDbPoolSize;

	private int maxDbPoolSize;
	
	private boolean autoCreate;

	public void initialize(Element element) {
		ElementHelper eh = new ElementHelper(element);
		dbHome = eh.getString("dbhome");
		dataBase = eh.getString("database");
		minDbPoolSize = eh.getInt("dbminpool", 10);
		maxDbPoolSize = eh.getInt("dbmaxpool", 20);
		autoCreate = eh.getBoolean("autocreate", false);
	}
	
	public boolean isAutoCreate() {
		return autoCreate;
	}

	public String getDbHome() {
		return dbHome;
	}

	public String getDataBase() {
		return dataBase;
	}

	public int getMinDbPoolSize() {
		return minDbPoolSize;
	}

	public int getMaxDbPoolSize() {
		return maxDbPoolSize;
	}

}
