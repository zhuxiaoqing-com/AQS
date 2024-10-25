package beans.table.logic;

public class Playershop {
	private Playershop() {
	}

	public static boolean add(Long key, xbean.logic.PlayerShop value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerShop add key:" + key);
		return _Tables_.instance.playershop.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerShop value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerShop add immediately  key:" + key);
		return _Tables_.instance.playershop.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerShop get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerShop get key:" + key);
		return _Tables_.instance.playershop.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerShop select(Long key) {
		return _Tables_.instance.playershop.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerShop remove key:" + key);
		return _Tables_.instance.playershop.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerShop> getTable() {
		return _Tables_.instance.playershop;
	}

}
