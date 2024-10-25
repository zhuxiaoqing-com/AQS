package beans.table.lobby;

public class Factiondepotmakeinfo {
	private Factiondepotmakeinfo() {
	}

	public static boolean add(Integer key, xbean.lobby.FactionDepotMakeInfo value) {
		return _Tables_.instance.factiondepotmakeinfo.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.FactionDepotMakeInfo value, boolean immediately) {
		return _Tables_.instance.factiondepotmakeinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.FactionDepotMakeInfo get(Integer key) {
		return _Tables_.instance.factiondepotmakeinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.FactionDepotMakeInfo select(Integer key) {
		return _Tables_.instance.factiondepotmakeinfo.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.factiondepotmakeinfo.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.FactionDepotMakeInfo> getTable() {
		return _Tables_.instance.factiondepotmakeinfo;
	}

}
