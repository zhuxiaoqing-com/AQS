package beans.table.logic;

public class Playerequipwarehouse {
	private Playerequipwarehouse() {
	}

	public static boolean add(Long key, xbean.logic.PlayerEquipWarehouse value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEquipWarehouse add key:" + key);
		return _Tables_.instance.playerequipwarehouse.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerEquipWarehouse value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEquipWarehouse add immediately  key:" + key);
		return _Tables_.instance.playerequipwarehouse.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerEquipWarehouse get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEquipWarehouse get key:" + key);
		return _Tables_.instance.playerequipwarehouse.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerEquipWarehouse select(Long key) {
		return _Tables_.instance.playerequipwarehouse.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEquipWarehouse remove key:" + key);
		return _Tables_.instance.playerequipwarehouse.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerEquipWarehouse> getTable() {
		return _Tables_.instance.playerequipwarehouse;
	}

}
