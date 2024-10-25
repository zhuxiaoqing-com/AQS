package beans.table.lobby;

public class Troop {
	private Troop() {
	}

	public static boolean add(Long key, xbean.lobby.Troop value) {
		return _Tables_.instance.troop.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.Troop value, boolean immediately) {
		return _Tables_.instance.troop.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.Troop get(Long key) {
		return _Tables_.instance.troop.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.Troop select(Long key) {
		return _Tables_.instance.troop.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.troop.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.Troop> getTable() {
		return _Tables_.instance.troop;
	}

}
