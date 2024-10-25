package beans.table.logic;

public class Citydepot {
	private Citydepot() {
	}

	public static boolean add(Long key, xbean.logic.CityDepot value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.CityDepot add key:" + key);
		return _Tables_.instance.citydepot.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.CityDepot value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.CityDepot add immediately  key:" + key);
		return _Tables_.instance.citydepot.insert(key, value, immediately) != null;
	}

	public static xbean.logic.CityDepot get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.CityDepot get key:" + key);
		return _Tables_.instance.citydepot.get(key);
	}

	// 只读模式查询
	public static xbean.logic.CityDepot select(Long key) {
		return _Tables_.instance.citydepot.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.CityDepot remove key:" + key);
		return _Tables_.instance.citydepot.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.CityDepot> getTable() {
		return _Tables_.instance.citydepot;
	}

}
