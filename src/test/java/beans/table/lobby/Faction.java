package beans.table.lobby;

public class Faction {
	private Faction() {
	}

	public static boolean add(Integer key, xbean.lobby.Faction value) {
		return _Tables_.instance.faction.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.Faction value, boolean immediately) {
		return _Tables_.instance.faction.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.Faction get(Integer key) {
		return _Tables_.instance.faction.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.Faction select(Integer key) {
		return _Tables_.instance.faction.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.faction.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.Faction> getTable() {
		return _Tables_.instance.faction;
	}

}
