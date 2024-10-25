package beans.table.lobby;

public class Lgplayerinfo {
	private Lgplayerinfo() {
	}

	public static boolean add(Long key, xbean.lobby.LgPlayerInfo value) {
		return _Tables_.instance.lgplayerinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.LgPlayerInfo value, boolean immediately) {
		return _Tables_.instance.lgplayerinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LgPlayerInfo get(Long key) {
		return _Tables_.instance.lgplayerinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LgPlayerInfo select(Long key) {
		return _Tables_.instance.lgplayerinfo.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.lgplayerinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.LgPlayerInfo> getTable() {
		return _Tables_.instance.lgplayerinfo;
	}

}
