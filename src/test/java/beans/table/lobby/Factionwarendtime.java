package beans.table.lobby;

public class Factionwarendtime {
	private Factionwarendtime() {
	}

	public static boolean add(Long key, Long value) {
		return _Tables_.instance.factionwarendtime.insert(key, value) != null;
	}

	public static boolean add(Long key, Long value, boolean immediately) {
		return _Tables_.instance.factionwarendtime.insert(key, value, immediately) != null;
	}

	public static Long get(Long key) {
		return _Tables_.instance.factionwarendtime.get(key);
	}

	// 只读模式查询
	public static Long select(Long key) {
		return _Tables_.instance.factionwarendtime.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.factionwarendtime.delete(key);
	}

	public static warrior.mdb.TTable<Long, Long> getTable() {
		return _Tables_.instance.factionwarendtime;
	}

}
