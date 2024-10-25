package beans.table.global;

public class Cfwtotallog {
	private Cfwtotallog() {
	}

	public static boolean add(Integer key, xbean.global.CFWTotalLog value) {
		return _Tables_.instance.cfwtotallog.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.global.CFWTotalLog value, boolean immediately) {
		return _Tables_.instance.cfwtotallog.insert(key, value, immediately) != null;
	}

	public static xbean.global.CFWTotalLog get(Integer key) {
		return _Tables_.instance.cfwtotallog.get(key);
	}

	// 只读模式查询
	public static xbean.global.CFWTotalLog select(Integer key) {
		return _Tables_.instance.cfwtotallog.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.cfwtotallog.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.global.CFWTotalLog> getTable() {
		return _Tables_.instance.cfwtotallog;
	}

}
