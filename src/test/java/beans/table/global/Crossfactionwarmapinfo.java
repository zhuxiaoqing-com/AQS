package beans.table.global;

public class Crossfactionwarmapinfo {
	private Crossfactionwarmapinfo() {
	}

	public static boolean add(Integer key, xbean.global.CrossFactionWarMapInfo value) {
		return _Tables_.instance.crossfactionwarmapinfo.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.global.CrossFactionWarMapInfo value, boolean immediately) {
		return _Tables_.instance.crossfactionwarmapinfo.insert(key, value, immediately) != null;
	}

	public static xbean.global.CrossFactionWarMapInfo get(Integer key) {
		return _Tables_.instance.crossfactionwarmapinfo.get(key);
	}

	// 只读模式查询
	public static xbean.global.CrossFactionWarMapInfo select(Integer key) {
		return _Tables_.instance.crossfactionwarmapinfo.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.crossfactionwarmapinfo.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.global.CrossFactionWarMapInfo> getTable() {
		return _Tables_.instance.crossfactionwarmapinfo;
	}

}
