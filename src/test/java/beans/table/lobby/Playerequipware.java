package beans.table.lobby;

public class Playerequipware {
	private Playerequipware() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerEquipWare value) {
		return _Tables_.instance.playerequipware.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerEquipWare value, boolean immediately) {
		return _Tables_.instance.playerequipware.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerEquipWare get(Long key) {
		return _Tables_.instance.playerequipware.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerEquipWare select(Long key) {
		return _Tables_.instance.playerequipware.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerequipware.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerEquipWare> getTable() {
		return _Tables_.instance.playerequipware;
	}

}
