package beans.table.logic;

public class Playercarrier {
	private Playercarrier() {
	}

	public static boolean add(Long key, xbean.logic.PlayerCarrier value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCarrier add key:" + key);
		return _Tables_.instance.playercarrier.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerCarrier value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCarrier add immediately  key:" + key);
		return _Tables_.instance.playercarrier.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerCarrier get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCarrier get key:" + key);
		return _Tables_.instance.playercarrier.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerCarrier select(Long key) {
		return _Tables_.instance.playercarrier.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCarrier remove key:" + key);
		return _Tables_.instance.playercarrier.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerCarrier> getTable() {
		return _Tables_.instance.playercarrier;
	}

}
