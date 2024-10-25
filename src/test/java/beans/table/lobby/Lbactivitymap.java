package beans.table.lobby;

public class Lbactivitymap {
	private Lbactivitymap() {
	}

	public static boolean add(Long key, xbean.lobby.LBActivityMap value) {
		return _Tables_.instance.lbactivitymap.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.LBActivityMap value, boolean immediately) {
		return _Tables_.instance.lbactivitymap.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LBActivityMap get(Long key) {
		return _Tables_.instance.lbactivitymap.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LBActivityMap select(Long key) {
		return _Tables_.instance.lbactivitymap.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.lbactivitymap.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.LBActivityMap> getTable() {
		return _Tables_.instance.lbactivitymap;
	}

}
