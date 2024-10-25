package beans.table.lobby;

public class Troopform {
	private Troopform() {
	}

	public static boolean add(Long key, xbean.lobby.TroopForm value) {
		return _Tables_.instance.troopform.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.TroopForm value, boolean immediately) {
		return _Tables_.instance.troopform.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.TroopForm get(Long key) {
		return _Tables_.instance.troopform.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.TroopForm select(Long key) {
		return _Tables_.instance.troopform.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.troopform.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.TroopForm> getTable() {
		return _Tables_.instance.troopform;
	}

}
