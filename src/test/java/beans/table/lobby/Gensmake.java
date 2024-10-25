package beans.table.lobby;

public class Gensmake {
	private Gensmake() {
	}

	public static boolean add(Long key, xbean.lobby.GensMake value) {
		return _Tables_.instance.gensmake.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GensMake value, boolean immediately) {
		return _Tables_.instance.gensmake.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GensMake get(Long key) {
		return _Tables_.instance.gensmake.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GensMake select(Long key) {
		return _Tables_.instance.gensmake.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gensmake.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GensMake> getTable() {
		return _Tables_.instance.gensmake;
	}

}
