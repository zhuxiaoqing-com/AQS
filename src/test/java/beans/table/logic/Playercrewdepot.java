package beans.table.logic;

public class Playercrewdepot {
	private Playercrewdepot() {
	}

	public static boolean add(Long key, xbean.logic.PlayerCrewDepot value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCrewDepot add key:" + key);
		return _Tables_.instance.playercrewdepot.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerCrewDepot value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCrewDepot add immediately  key:" + key);
		return _Tables_.instance.playercrewdepot.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerCrewDepot get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCrewDepot get key:" + key);
		return _Tables_.instance.playercrewdepot.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerCrewDepot select(Long key) {
		return _Tables_.instance.playercrewdepot.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCrewDepot remove key:" + key);
		return _Tables_.instance.playercrewdepot.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerCrewDepot> getTable() {
		return _Tables_.instance.playercrewdepot;
	}

}
