package beans.table.lobby;

public class Genswarmap {
	private Genswarmap() {
	}

	public static boolean add(Integer key, xbean.lobby.GensWarMapInfo value) {
		return _Tables_.instance.genswarmap.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.GensWarMapInfo value, boolean immediately) {
		return _Tables_.instance.genswarmap.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GensWarMapInfo get(Integer key) {
		return _Tables_.instance.genswarmap.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GensWarMapInfo select(Integer key) {
		return _Tables_.instance.genswarmap.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.genswarmap.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.GensWarMapInfo> getTable() {
		return _Tables_.instance.genswarmap;
	}

}
