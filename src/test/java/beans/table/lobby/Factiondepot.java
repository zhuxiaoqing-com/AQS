package beans.table.lobby;

public class Factiondepot {
	private Factiondepot() {
	}

	public static boolean add(Integer key, xbean.lobby.FactionDepot value) {
		return _Tables_.instance.factiondepot.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.FactionDepot value, boolean immediately) {
		return _Tables_.instance.factiondepot.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.FactionDepot get(Integer key) {
		return _Tables_.instance.factiondepot.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.FactionDepot select(Integer key) {
		return _Tables_.instance.factiondepot.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.factiondepot.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.FactionDepot> getTable() {
		return _Tables_.instance.factiondepot;
	}

}
