package beans.table.lobby;

public class Playertroop {
	private Playertroop() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerTroop value) {
		return _Tables_.instance.playertroop.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerTroop value, boolean immediately) {
		return _Tables_.instance.playertroop.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerTroop get(Long key) {
		return _Tables_.instance.playertroop.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerTroop select(Long key) {
		return _Tables_.instance.playertroop.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playertroop.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerTroop> getTable() {
		return _Tables_.instance.playertroop;
	}

}
