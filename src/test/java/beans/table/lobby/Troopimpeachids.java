package beans.table.lobby;

public class Troopimpeachids {
	private Troopimpeachids() {
	}

	public static boolean add(Integer key, xbean.lobby.TroopImpeachIds value) {
		return _Tables_.instance.troopimpeachids.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.TroopImpeachIds value, boolean immediately) {
		return _Tables_.instance.troopimpeachids.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.TroopImpeachIds get(Integer key) {
		return _Tables_.instance.troopimpeachids.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.TroopImpeachIds select(Integer key) {
		return _Tables_.instance.troopimpeachids.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.troopimpeachids.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.TroopImpeachIds> getTable() {
		return _Tables_.instance.troopimpeachids;
	}

}
