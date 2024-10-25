package beans.table.lobby;

public class Playerblacklist {
	private Playerblacklist() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerBlackList value) {
		return _Tables_.instance.playerblacklist.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerBlackList value, boolean immediately) {
		return _Tables_.instance.playerblacklist.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerBlackList get(Long key) {
		return _Tables_.instance.playerblacklist.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerBlackList select(Long key) {
		return _Tables_.instance.playerblacklist.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerblacklist.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerBlackList> getTable() {
		return _Tables_.instance.playerblacklist;
	}

}
