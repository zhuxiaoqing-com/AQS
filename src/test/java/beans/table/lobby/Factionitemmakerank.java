package beans.table.lobby;

public class Factionitemmakerank {
	private Factionitemmakerank() {
	}

	public static boolean add(Long key, xbean.lobby.FactionItemMakeRank value) {
		return _Tables_.instance.factionitemmakerank.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.FactionItemMakeRank value, boolean immediately) {
		return _Tables_.instance.factionitemmakerank.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.FactionItemMakeRank get(Long key) {
		return _Tables_.instance.factionitemmakerank.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.FactionItemMakeRank select(Long key) {
		return _Tables_.instance.factionitemmakerank.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.factionitemmakerank.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.FactionItemMakeRank> getTable() {
		return _Tables_.instance.factionitemmakerank;
	}

}
