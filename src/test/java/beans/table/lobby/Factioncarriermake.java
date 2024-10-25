package beans.table.lobby;

public class Factioncarriermake {
	private Factioncarriermake() {
	}

	public static boolean add(Long key, xbean.lobby.FactionCarrierMake value) {
		return _Tables_.instance.factioncarriermake.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.FactionCarrierMake value, boolean immediately) {
		return _Tables_.instance.factioncarriermake.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.FactionCarrierMake get(Long key) {
		return _Tables_.instance.factioncarriermake.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.FactionCarrierMake select(Long key) {
		return _Tables_.instance.factioncarriermake.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.factioncarriermake.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.FactionCarrierMake> getTable() {
		return _Tables_.instance.factioncarriermake;
	}

}
