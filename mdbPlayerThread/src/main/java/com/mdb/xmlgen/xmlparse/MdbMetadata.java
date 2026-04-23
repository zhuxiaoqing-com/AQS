package com.mdb.xmlgen.xmlparse;

import com.mdb.util.ElementHelper;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.Xbean;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * 存儲所有的表和bean
 * 
 * @author Eric.Ma
 *
 */
public class MdbMetadata {

	/*private static MdbMetadata instance = new MdbMetadata();

	public static MdbMetadata getInstance() {
		return instance;
	}

	private MdbMetadata() {
	}
*/
	private Map<String, Xbean> beans = new HashMap<>();

	private Map<String, Table> tables = new HashMap<>();

	// cache策略
	private String defaultCache = "com.mdb.TTableCacheLRU";

	private int checkPointPeriod = 60000;

	// snapshot 过慢时间, 单位毫秒
	private long snapshotFatalTime = 200;

	private boolean supportFlush;

	/**
	 * 序列化逻辑线程
	 */
	private String serializeLogicThreadName = "";

	private String name;

	private Datasource dc = new Datasource();

	public void initialize(Element element) throws Exception {
		ElementHelper eh = new ElementHelper(element);
		name = eh.getString("name");
		defaultCache = eh.getString("defaultTableCache", defaultCache);
		snapshotFatalTime = eh.getLong("snapshotFatalTime", snapshotFatalTime);
		checkPointPeriod = eh.getInt("checkpointPeriod", checkPointPeriod);
		supportFlush = eh.getBoolean("supportFlush", supportFlush);
		serializeLogicThreadName = eh.getString("serializeLogicThreadName", serializeLogicThreadName);
		XMLUtils.getChildElements(element).forEach(e -> {
			if (e.getNodeName().equals("xbean")) {
				Xbean xbean = new Xbean(this, e);
				Xbean preB = beans.put(xbean.getName(), xbean);
				if (preB != null) {
					throw new RuntimeException("xbean 名字重复 : " + xbean.getName());
				}
			} else if (e.getNodeName().equals("table")) {
				Table table = new Table(this, e, supportFlush);
				Table pre = tables.put(table.getName(), table);
				if (pre != null) {
					throw new RuntimeException("table 名字重复 : " + table.getName());
				}
			} else if (e.getNodeName().equals("datasource")) {
				dc.initialize(e);
			}
		});

	}

	public Map<String, Xbean> getBeans() {
		return beans;
	}

	public Map<String, Table> getTables() {
		return tables;
	}

	public Table getTable(String tableName) {
		return tables.get(tableName);
	}

	public enum DBType {
		MYSQL, MONGO
	}

	public static DBType getDBType(String dbhome) {
		if (dbhome.startsWith("jdbc:mysql"))
			return DBType.MYSQL;
		else if (dbhome.startsWith("mongo"))
			return DBType.MONGO;
		return null;
	}

	public DBType getDBType() {
		String dbhome = getDbHome();
		if (dbhome.startsWith("jdbc:mysql"))
			return DBType.MYSQL;
		else if (dbhome.startsWith("mongo"))
			return DBType.MONGO;
		return null;
	}

	public String getDbHome() {
		return dc.getDbHome();
	}
	
	public boolean isAutoCreate() {
		return dc.isAutoCreate();
	}

	public int getCheckPointPeriod() {
		return checkPointPeriod;
	}

	public String getDefaultCache() {
		return defaultCache;
	}

	public long getSnapshotFatalTime() {
		return snapshotFatalTime;
	}

	public Datasource getCache() {
		return dc;
	}

	public String getDataBase() {
		return dc.getDataBase();
	}

	public String getName() {
		return name;
	}


	public String getSerializeLogicThreadName() {
		return serializeLogicThreadName;
	}
}
