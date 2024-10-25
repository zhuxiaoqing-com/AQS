package beans.table.global;

public class Gsactivitymap {
	private Gsactivitymap() {
	}

	public static boolean add(Long key, xbean.global.GSActivityMap value) {
		return _Tables_.instance.gsactivitymap.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.global.GSActivityMap value, boolean immediately) {
		return _Tables_.instance.gsactivitymap.insert(key, value, immediately) != null;
	}

	public static xbean.global.GSActivityMap get(Long key) {
		return _Tables_.instance.gsactivitymap.get(key);
	}

	// 只读模式查询
	public static xbean.global.GSActivityMap select(Long key) {
		return _Tables_.instance.gsactivitymap.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gsactivitymap.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.global.GSActivityMap> getTable() {
		return _Tables_.instance.gsactivitymap;
	}

}
