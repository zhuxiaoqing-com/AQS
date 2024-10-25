package beans.table.logic;

public class Playermilitarysup {
	private Playermilitarysup() {
	}

	public static boolean add(Long key, xbean.logic.PlayerMilitarySup value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMilitarySup add key:" + key);
		return _Tables_.instance.playermilitarysup.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerMilitarySup value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMilitarySup add immediately  key:" + key);
		return _Tables_.instance.playermilitarysup.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerMilitarySup get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMilitarySup get key:" + key);
		return _Tables_.instance.playermilitarysup.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerMilitarySup select(Long key) {
		return _Tables_.instance.playermilitarysup.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMilitarySup remove key:" + key);
		return _Tables_.instance.playermilitarysup.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerMilitarySup> getTable() {
		return _Tables_.instance.playermilitarysup;
	}

}
