package beans.table.logic;

public class Playerwarmakes {
	private Playerwarmakes() {
	}

	public static boolean add(Long key, xbean.logic.PlayerWarMakes value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarMakes add key:" + key);
		return _Tables_.instance.playerwarmakes.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerWarMakes value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarMakes add immediately  key:" + key);
		return _Tables_.instance.playerwarmakes.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerWarMakes get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarMakes get key:" + key);
		return _Tables_.instance.playerwarmakes.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerWarMakes select(Long key) {
		return _Tables_.instance.playerwarmakes.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarMakes remove key:" + key);
		return _Tables_.instance.playerwarmakes.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerWarMakes> getTable() {
		return _Tables_.instance.playerwarmakes;
	}

}
