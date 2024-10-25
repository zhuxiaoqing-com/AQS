package beans.table.logic;

public class Playerwildboss {
	private Playerwildboss() {
	}

	public static boolean add(Long key, xbean.logic.PlayerWildBoss value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWildBoss add key:" + key);
		return _Tables_.instance.playerwildboss.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerWildBoss value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWildBoss add immediately  key:" + key);
		return _Tables_.instance.playerwildboss.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerWildBoss get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWildBoss get key:" + key);
		return _Tables_.instance.playerwildboss.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerWildBoss select(Long key) {
		return _Tables_.instance.playerwildboss.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWildBoss remove key:" + key);
		return _Tables_.instance.playerwildboss.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerWildBoss> getTable() {
		return _Tables_.instance.playerwildboss;
	}

}
