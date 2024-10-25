package beans.table.lobby;

public class Nationinfo {
	private Nationinfo() {
	}

	public static boolean add(Long key, xbean.lobby.NationInfo value) {
		return _Tables_.instance.nationinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.NationInfo value, boolean immediately) {
		return _Tables_.instance.nationinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.NationInfo get(Long key) {
		return _Tables_.instance.nationinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.NationInfo select(Long key) {
		return _Tables_.instance.nationinfo.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.nationinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.NationInfo> getTable() {
		return _Tables_.instance.nationinfo;
	}

}
