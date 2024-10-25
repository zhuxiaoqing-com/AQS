package beans.table.logic;

public class Playerpvpbattle {
	private Playerpvpbattle() {
	}

	public static boolean add(Long key, xbean.logic.PlayerPvpBattle value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerPvpBattle add key:" + key);
		return _Tables_.instance.playerpvpbattle.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerPvpBattle value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerPvpBattle add immediately  key:" + key);
		return _Tables_.instance.playerpvpbattle.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerPvpBattle get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerPvpBattle get key:" + key);
		return _Tables_.instance.playerpvpbattle.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerPvpBattle select(Long key) {
		return _Tables_.instance.playerpvpbattle.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerPvpBattle remove key:" + key);
		return _Tables_.instance.playerpvpbattle.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerPvpBattle> getTable() {
		return _Tables_.instance.playerpvpbattle;
	}

}
