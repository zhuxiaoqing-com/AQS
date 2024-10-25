package beans.table.global;

public class Crossservergenswildinfo {
	private Crossservergenswildinfo() {
	}

	public static boolean add(Integer key, xbean.global.CrossServerGensWildInfo value) {
		return _Tables_.instance.crossservergenswildinfo.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.global.CrossServerGensWildInfo value, boolean immediately) {
		return _Tables_.instance.crossservergenswildinfo.insert(key, value, immediately) != null;
	}

	public static xbean.global.CrossServerGensWildInfo get(Integer key) {
		return _Tables_.instance.crossservergenswildinfo.get(key);
	}

	// 只读模式查询
	public static xbean.global.CrossServerGensWildInfo select(Integer key) {
		return _Tables_.instance.crossservergenswildinfo.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.crossservergenswildinfo.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.global.CrossServerGensWildInfo> getTable() {
		return _Tables_.instance.crossservergenswildinfo;
	}

}
