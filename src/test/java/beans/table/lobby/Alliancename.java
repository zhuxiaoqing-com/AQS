package beans.table.lobby;

public class Alliancename {
	private Alliancename() {
	}

	public static boolean add(String key, Long value) {
		return _Tables_.instance.alliancename.insert(key, value) != null;
	}

	public static boolean add(String key, Long value, boolean immediately) {
		return _Tables_.instance.alliancename.insert(key, value, immediately) != null;
	}

	public static Long get(String key) {
		return _Tables_.instance.alliancename.get(key);
	}

	// 只读模式查询
	public static Long select(String key) {
		return _Tables_.instance.alliancename.select(key);
	}

	public static boolean remove(String key) {
		return _Tables_.instance.alliancename.delete(key);
	}

	public static warrior.mdb.TTable<String, Long> getTable() {
		return _Tables_.instance.alliancename;
	}

}
