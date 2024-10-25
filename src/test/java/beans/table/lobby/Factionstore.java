package beans.table.lobby;

public class Factionstore {
	private Factionstore() {
	}

	public static boolean add(Integer key, xbean.lobby.FactionStore value) {
		return _Tables_.instance.factionstore.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.FactionStore value, boolean immediately) {
		return _Tables_.instance.factionstore.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.FactionStore get(Integer key) {
		return _Tables_.instance.factionstore.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.FactionStore select(Integer key) {
		return _Tables_.instance.factionstore.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.factionstore.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.FactionStore> getTable() {
		return _Tables_.instance.factionstore;
	}

}
