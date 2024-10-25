package beans.table.logic;

public class Playerachieve {
	private Playerachieve() {
	}

	public static boolean add(Long key, xbean.logic.PlayerAchieve value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerAchieve add key:" + key);
		return _Tables_.instance.playerachieve.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerAchieve value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerAchieve add immediately  key:" + key);
		return _Tables_.instance.playerachieve.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerAchieve get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerAchieve get key:" + key);
		return _Tables_.instance.playerachieve.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerAchieve select(Long key) {
		return _Tables_.instance.playerachieve.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerAchieve remove key:" + key);
		return _Tables_.instance.playerachieve.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerAchieve> getTable() {
		return _Tables_.instance.playerachieve;
	}

}
