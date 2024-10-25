package beans.table.lobby;

public class Troopimpeach {
	private Troopimpeach() {
	}

	public static boolean add(Long key, xbean.lobby.TroopImpeach value) {
		return _Tables_.instance.troopimpeach.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.TroopImpeach value, boolean immediately) {
		return _Tables_.instance.troopimpeach.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.TroopImpeach get(Long key) {
		return _Tables_.instance.troopimpeach.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.TroopImpeach select(Long key) {
		return _Tables_.instance.troopimpeach.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.troopimpeach.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.TroopImpeach> getTable() {
		return _Tables_.instance.troopimpeach;
	}

}
