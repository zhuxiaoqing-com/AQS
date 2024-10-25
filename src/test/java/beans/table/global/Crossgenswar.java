package beans.table.global;

public class Crossgenswar {
	private Crossgenswar() {
	}

	public static boolean add(Long key, xbean.global.CrossGensWar value) {
		return _Tables_.instance.crossgenswar.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.global.CrossGensWar value, boolean immediately) {
		return _Tables_.instance.crossgenswar.insert(key, value, immediately) != null;
	}

	public static xbean.global.CrossGensWar get(Long key) {
		return _Tables_.instance.crossgenswar.get(key);
	}

	// 只读模式查询
	public static xbean.global.CrossGensWar select(Long key) {
		return _Tables_.instance.crossgenswar.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.crossgenswar.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.global.CrossGensWar> getTable() {
		return _Tables_.instance.crossgenswar;
	}

}
