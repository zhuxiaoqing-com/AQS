package beans.table.lobby;

public class Gensrecord {
	private Gensrecord() {
	}

	public static boolean add(Long key, xbean.lobby.GensRecord value) {
		return _Tables_.instance.gensrecord.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GensRecord value, boolean immediately) {
		return _Tables_.instance.gensrecord.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GensRecord get(Long key) {
		return _Tables_.instance.gensrecord.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GensRecord select(Long key) {
		return _Tables_.instance.gensrecord.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gensrecord.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GensRecord> getTable() {
		return _Tables_.instance.gensrecord;
	}

}
