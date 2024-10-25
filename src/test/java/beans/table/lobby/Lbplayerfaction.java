package beans.table.lobby;

public class Lbplayerfaction {
	private Lbplayerfaction() {
	}

	public static boolean add(Long key, xbean.lobby.LbPlayerFaction value) {
		return _Tables_.instance.lbplayerfaction.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.LbPlayerFaction value, boolean immediately) {
		return _Tables_.instance.lbplayerfaction.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LbPlayerFaction get(Long key) {
		return _Tables_.instance.lbplayerfaction.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LbPlayerFaction select(Long key) {
		return _Tables_.instance.lbplayerfaction.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.lbplayerfaction.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.LbPlayerFaction> getTable() {
		return _Tables_.instance.lbplayerfaction;
	}

}
