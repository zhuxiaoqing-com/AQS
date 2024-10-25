package beans.table.lobby;

public class Lguser {
	private Lguser() {
	}

	public static boolean add(String key, xbean.lobby.LgUser value) {
		return _Tables_.instance.lguser.insert(key, value) != null;
	}

	public static boolean add(String key, xbean.lobby.LgUser value, boolean immediately) {
		return _Tables_.instance.lguser.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LgUser get(String key) {
		return _Tables_.instance.lguser.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LgUser select(String key) {
		return _Tables_.instance.lguser.select(key);
	}

	public static boolean remove(String key) {
		return _Tables_.instance.lguser.delete(key);
	}

	public static warrior.mdb.TTable<String, xbean.lobby.LgUser> getTable() {
		return _Tables_.instance.lguser;
	}

}
