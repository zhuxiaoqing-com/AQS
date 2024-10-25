package beans.table.lobby;

public class Gensrent {
	private Gensrent() {
	}

	public static boolean add(Long key, xbean.lobby.GensRentInfo value) {
		return _Tables_.instance.gensrent.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GensRentInfo value, boolean immediately) {
		return _Tables_.instance.gensrent.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GensRentInfo get(Long key) {
		return _Tables_.instance.gensrent.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GensRentInfo select(Long key) {
		return _Tables_.instance.gensrent.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gensrent.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GensRentInfo> getTable() {
		return _Tables_.instance.gensrent;
	}

}
