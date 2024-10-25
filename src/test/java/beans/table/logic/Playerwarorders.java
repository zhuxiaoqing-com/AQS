package beans.table.logic;

public class Playerwarorders {
	private Playerwarorders() {
	}

	public static boolean add(Long key, xbean.logic.PlayerWarOrders value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarOrders add key:" + key);
		return _Tables_.instance.playerwarorders.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerWarOrders value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarOrders add immediately  key:" + key);
		return _Tables_.instance.playerwarorders.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerWarOrders get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarOrders get key:" + key);
		return _Tables_.instance.playerwarorders.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerWarOrders select(Long key) {
		return _Tables_.instance.playerwarorders.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarOrders remove key:" + key);
		return _Tables_.instance.playerwarorders.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerWarOrders> getTable() {
		return _Tables_.instance.playerwarorders;
	}

}
