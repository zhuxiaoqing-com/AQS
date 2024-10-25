package beans.table.lobby;

public class Userlgbind {
	private Userlgbind() {
	}

	public static boolean add(String key, xbean.lobby.UserLGBind value) {
		return _Tables_.instance.userlgbind.insert(key, value) != null;
	}

	public static boolean add(String key, xbean.lobby.UserLGBind value, boolean immediately) {
		return _Tables_.instance.userlgbind.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.UserLGBind get(String key) {
		return _Tables_.instance.userlgbind.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.UserLGBind select(String key) {
		return _Tables_.instance.userlgbind.select(key);
	}

	public static boolean remove(String key) {
		return _Tables_.instance.userlgbind.delete(key);
	}

	public static warrior.mdb.TTable<String, xbean.lobby.UserLGBind> getTable() {
		return _Tables_.instance.userlgbind;
	}

}
