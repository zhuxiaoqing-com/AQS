package beans.table.logic;

public class Playerwarfare {
	private Playerwarfare() {
	}

	public static boolean add(Long key, xbean.logic.PlayerWarfare value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarfare add key:" + key);
		return _Tables_.instance.playerwarfare.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerWarfare value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarfare add immediately  key:" + key);
		return _Tables_.instance.playerwarfare.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerWarfare get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarfare get key:" + key);
		return _Tables_.instance.playerwarfare.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerWarfare select(Long key) {
		return _Tables_.instance.playerwarfare.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarfare remove key:" + key);
		return _Tables_.instance.playerwarfare.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerWarfare> getTable() {
		return _Tables_.instance.playerwarfare;
	}

}
