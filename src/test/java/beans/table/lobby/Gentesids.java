package beans.table.lobby;

public class Gentesids {
	private Gentesids() {
	}

	public static boolean add(Integer key, xbean.lobby.GentesIds value) {
		return _Tables_.instance.gentesids.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.GentesIds value, boolean immediately) {
		return _Tables_.instance.gentesids.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GentesIds get(Integer key) {
		return _Tables_.instance.gentesids.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GentesIds select(Integer key) {
		return _Tables_.instance.gentesids.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.gentesids.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.GentesIds> getTable() {
		return _Tables_.instance.gentesids;
	}

}
