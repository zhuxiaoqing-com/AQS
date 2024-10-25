package beans.table.lobby;

public class Serverinfolist {
	private Serverinfolist() {
	}

	public static boolean add(Long key, xbean.lobby.ServerInfoList value) {
		return _Tables_.instance.serverinfolist.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.ServerInfoList value, boolean immediately) {
		return _Tables_.instance.serverinfolist.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.ServerInfoList get(Long key) {
		return _Tables_.instance.serverinfolist.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.ServerInfoList select(Long key) {
		return _Tables_.instance.serverinfolist.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.serverinfolist.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.ServerInfoList> getTable() {
		return _Tables_.instance.serverinfolist;
	}

}
