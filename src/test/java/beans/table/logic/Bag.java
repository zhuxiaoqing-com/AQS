package beans.table.logic;

public class Bag {
	private Bag() {
	}

	public static boolean add(Long key, xbean.logic.AllBag value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.AllBag add key:" + key);
		return _Tables_.instance.bag.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.AllBag value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.AllBag add immediately  key:" + key);
		return _Tables_.instance.bag.insert(key, value, immediately) != null;
	}

	public static xbean.logic.AllBag get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.AllBag get key:" + key);
		return _Tables_.instance.bag.get(key);
	}

	// 只读模式查询
	public static xbean.logic.AllBag select(Long key) {
		return _Tables_.instance.bag.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.AllBag remove key:" + key);
		return _Tables_.instance.bag.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.AllBag> getTable() {
		return _Tables_.instance.bag;
	}

}
