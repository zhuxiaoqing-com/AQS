package beans.table.lobby;

public class Playerorder {
	private Playerorder() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerOrder value) {
		return _Tables_.instance.playerorder.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerOrder value, boolean immediately) {
		return _Tables_.instance.playerorder.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerOrder get(Long key) {
		return _Tables_.instance.playerorder.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerOrder select(Long key) {
		return _Tables_.instance.playerorder.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerorder.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerOrder> getTable() {
		return _Tables_.instance.playerorder;
	}

}
