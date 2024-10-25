package beans.table.logic;

public class Playerwarmakesbar {
	private Playerwarmakesbar() {
	}

	public static boolean add(Long key, xbean.logic.PlayerWarMakesBar value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarMakesBar add key:" + key);
		return _Tables_.instance.playerwarmakesbar.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerWarMakesBar value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarMakesBar add immediately  key:" + key);
		return _Tables_.instance.playerwarmakesbar.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerWarMakesBar get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarMakesBar get key:" + key);
		return _Tables_.instance.playerwarmakesbar.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerWarMakesBar select(Long key) {
		return _Tables_.instance.playerwarmakesbar.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWarMakesBar remove key:" + key);
		return _Tables_.instance.playerwarmakesbar.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerWarMakesBar> getTable() {
		return _Tables_.instance.playerwarmakesbar;
	}

}
