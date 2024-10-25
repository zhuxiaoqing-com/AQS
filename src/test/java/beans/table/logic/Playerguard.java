package beans.table.logic;

public class Playerguard {
	private Playerguard() {
	}

	public static boolean add(Long key, xbean.logic.PlayerGuard value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerGuard add key:" + key);
		return _Tables_.instance.playerguard.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerGuard value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerGuard add immediately  key:" + key);
		return _Tables_.instance.playerguard.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerGuard get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerGuard get key:" + key);
		return _Tables_.instance.playerguard.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerGuard select(Long key) {
		return _Tables_.instance.playerguard.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerGuard remove key:" + key);
		return _Tables_.instance.playerguard.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerGuard> getTable() {
		return _Tables_.instance.playerguard;
	}

}
