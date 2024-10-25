package beans.table.lobby;

public class Actcityfightenroll {
	private Actcityfightenroll() {
	}

	public static boolean add(Long key, xbean.lobby.ActCityFightEnroll value) {
		return _Tables_.instance.actcityfightenroll.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.ActCityFightEnroll value, boolean immediately) {
		return _Tables_.instance.actcityfightenroll.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.ActCityFightEnroll get(Long key) {
		return _Tables_.instance.actcityfightenroll.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.ActCityFightEnroll select(Long key) {
		return _Tables_.instance.actcityfightenroll.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.actcityfightenroll.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.ActCityFightEnroll> getTable() {
		return _Tables_.instance.actcityfightenroll;
	}

}
