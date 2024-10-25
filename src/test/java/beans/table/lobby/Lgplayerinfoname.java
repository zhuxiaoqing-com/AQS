package beans.table.lobby;

public class Lgplayerinfoname {
	private Lgplayerinfoname() {
	}

	public static boolean add(String key, xbean.lobby.PlayerUserInfo value) {
		return _Tables_.instance.lgplayerinfoname.insert(key, value) != null;
	}

	public static boolean add(String key, xbean.lobby.PlayerUserInfo value, boolean immediately) {
		return _Tables_.instance.lgplayerinfoname.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerUserInfo get(String key) {
		return _Tables_.instance.lgplayerinfoname.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerUserInfo select(String key) {
		return _Tables_.instance.lgplayerinfoname.select(key);
	}

	public static boolean remove(String key) {
		return _Tables_.instance.lgplayerinfoname.delete(key);
	}

	public static warrior.mdb.TTable<String, xbean.lobby.PlayerUserInfo> getTable() {
		return _Tables_.instance.lgplayerinfoname;
	}

}
