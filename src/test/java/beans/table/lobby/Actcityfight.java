package beans.table.lobby;

public class Actcityfight {
	private Actcityfight() {
	}

	public static boolean add(Long key, xbean.lobby.ActCityFight value) {
		return _Tables_.instance.actcityfight.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.ActCityFight value, boolean immediately) {
		return _Tables_.instance.actcityfight.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.ActCityFight get(Long key) {
		return _Tables_.instance.actcityfight.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.ActCityFight select(Long key) {
		return _Tables_.instance.actcityfight.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.actcityfight.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.ActCityFight> getTable() {
		return _Tables_.instance.actcityfight;
	}

}
