package beans.table.logic;

public class Players {
	private Players() {
	}

	public static boolean add(Long key, xbean.logic.Player value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.Player add key:" + key);
		return _Tables_.instance.players.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.Player value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.Player add immediately  key:" + key);
		return _Tables_.instance.players.insert(key, value, immediately) != null;
	}

	public static xbean.logic.Player get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.Player get key:" + key);
		return _Tables_.instance.players.get(key);
	}

	// 只读模式查询
	public static xbean.logic.Player select(Long key) {
		return _Tables_.instance.players.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.Player remove key:" + key);
		return _Tables_.instance.players.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.Player> getTable() {
		return _Tables_.instance.players;
	}

}
