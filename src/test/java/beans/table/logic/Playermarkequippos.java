package beans.table.logic;

public class Playermarkequippos {
	private Playermarkequippos() {
	}

	public static boolean add(Long key, xbean.logic.PlayerMarkEquipPos value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMarkEquipPos add key:" + key);
		return _Tables_.instance.playermarkequippos.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerMarkEquipPos value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMarkEquipPos add immediately  key:" + key);
		return _Tables_.instance.playermarkequippos.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerMarkEquipPos get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMarkEquipPos get key:" + key);
		return _Tables_.instance.playermarkequippos.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerMarkEquipPos select(Long key) {
		return _Tables_.instance.playermarkequippos.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMarkEquipPos remove key:" + key);
		return _Tables_.instance.playermarkequippos.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerMarkEquipPos> getTable() {
		return _Tables_.instance.playermarkequippos;
	}

}
