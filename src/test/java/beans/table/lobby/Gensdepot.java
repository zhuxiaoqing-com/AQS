package beans.table.lobby;

public class Gensdepot {
	private Gensdepot() {
	}

	public static boolean add(Long key, xbean.lobby.GensDepot value) {
		return _Tables_.instance.gensdepot.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GensDepot value, boolean immediately) {
		return _Tables_.instance.gensdepot.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GensDepot get(Long key) {
		return _Tables_.instance.gensdepot.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GensDepot select(Long key) {
		return _Tables_.instance.gensdepot.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gensdepot.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GensDepot> getTable() {
		return _Tables_.instance.gensdepot;
	}

}
