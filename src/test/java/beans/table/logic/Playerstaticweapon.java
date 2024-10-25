package beans.table.logic;

public class Playerstaticweapon {
	private Playerstaticweapon() {
	}

	public static boolean add(Long key, xbean.logic.PlayerStaticWeapon value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerStaticWeapon add key:" + key);
		return _Tables_.instance.playerstaticweapon.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerStaticWeapon value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerStaticWeapon add immediately  key:" + key);
		return _Tables_.instance.playerstaticweapon.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerStaticWeapon get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerStaticWeapon get key:" + key);
		return _Tables_.instance.playerstaticweapon.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerStaticWeapon select(Long key) {
		return _Tables_.instance.playerstaticweapon.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerStaticWeapon remove key:" + key);
		return _Tables_.instance.playerstaticweapon.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerStaticWeapon> getTable() {
		return _Tables_.instance.playerstaticweapon;
	}

}
