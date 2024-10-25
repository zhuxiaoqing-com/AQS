package beans.table.lobby;

public class Playerresbase {
	private Playerresbase() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerResBase value) {
		return _Tables_.instance.playerresbase.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerResBase value, boolean immediately) {
		return _Tables_.instance.playerresbase.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerResBase get(Long key) {
		return _Tables_.instance.playerresbase.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerResBase select(Long key) {
		return _Tables_.instance.playerresbase.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerresbase.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerResBase> getTable() {
		return _Tables_.instance.playerresbase;
	}

}
