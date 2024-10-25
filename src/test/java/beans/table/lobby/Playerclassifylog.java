package beans.table.lobby;

public class Playerclassifylog {
	private Playerclassifylog() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerClassifyLog value) {
		return _Tables_.instance.playerclassifylog.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerClassifyLog value, boolean immediately) {
		return _Tables_.instance.playerclassifylog.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerClassifyLog get(Long key) {
		return _Tables_.instance.playerclassifylog.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerClassifyLog select(Long key) {
		return _Tables_.instance.playerclassifylog.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerclassifylog.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerClassifyLog> getTable() {
		return _Tables_.instance.playerclassifylog;
	}

}
