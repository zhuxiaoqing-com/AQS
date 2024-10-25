package beans.table.lobby;

public class Troopformids {
	private Troopformids() {
	}

	public static boolean add(Integer key, xbean.lobby.TroopFormIds value) {
		return _Tables_.instance.troopformids.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.TroopFormIds value, boolean immediately) {
		return _Tables_.instance.troopformids.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.TroopFormIds get(Integer key) {
		return _Tables_.instance.troopformids.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.TroopFormIds select(Integer key) {
		return _Tables_.instance.troopformids.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.troopformids.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.TroopFormIds> getTable() {
		return _Tables_.instance.troopformids;
	}

}
