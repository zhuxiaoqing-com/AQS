package beans.table.lobby;

public class Troopcarrier {
	private Troopcarrier() {
	}

	public static boolean add(Long key, xbean.lobby.TroopCarrier value) {
		return _Tables_.instance.troopcarrier.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.TroopCarrier value, boolean immediately) {
		return _Tables_.instance.troopcarrier.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.TroopCarrier get(Long key) {
		return _Tables_.instance.troopcarrier.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.TroopCarrier select(Long key) {
		return _Tables_.instance.troopcarrier.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.troopcarrier.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.TroopCarrier> getTable() {
		return _Tables_.instance.troopcarrier;
	}

}
