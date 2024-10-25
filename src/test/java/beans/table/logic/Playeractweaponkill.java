package beans.table.logic;

public class Playeractweaponkill {
	private Playeractweaponkill() {
	}

	public static boolean add(Long key, xbean.logic.PlayerActWeaponKill value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerActWeaponKill add key:" + key);
		return _Tables_.instance.playeractweaponkill.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerActWeaponKill value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerActWeaponKill add immediately  key:" + key);
		return _Tables_.instance.playeractweaponkill.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerActWeaponKill get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerActWeaponKill get key:" + key);
		return _Tables_.instance.playeractweaponkill.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerActWeaponKill select(Long key) {
		return _Tables_.instance.playeractweaponkill.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerActWeaponKill remove key:" + key);
		return _Tables_.instance.playeractweaponkill.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerActWeaponKill> getTable() {
		return _Tables_.instance.playeractweaponkill;
	}

}
