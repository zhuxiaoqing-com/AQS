package beans.table.logic;

public class Playermilitary {
	private Playermilitary() {
	}

	public static boolean add(Long key, xbean.logic.PlayerMilitary value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMilitary add key:" + key);
		return _Tables_.instance.playermilitary.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerMilitary value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMilitary add immediately  key:" + key);
		return _Tables_.instance.playermilitary.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerMilitary get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMilitary get key:" + key);
		return _Tables_.instance.playermilitary.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerMilitary select(Long key) {
		return _Tables_.instance.playermilitary.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMilitary remove key:" + key);
		return _Tables_.instance.playermilitary.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerMilitary> getTable() {
		return _Tables_.instance.playermilitary;
	}

}
