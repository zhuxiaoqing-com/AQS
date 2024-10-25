package beans.table.lobby;

public class Playerfactionresbase {
	private Playerfactionresbase() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerFactionResBase value) {
		return _Tables_.instance.playerfactionresbase.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerFactionResBase value, boolean immediately) {
		return _Tables_.instance.playerfactionresbase.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerFactionResBase get(Long key) {
		return _Tables_.instance.playerfactionresbase.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerFactionResBase select(Long key) {
		return _Tables_.instance.playerfactionresbase.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerfactionresbase.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerFactionResBase> getTable() {
		return _Tables_.instance.playerfactionresbase;
	}

}
