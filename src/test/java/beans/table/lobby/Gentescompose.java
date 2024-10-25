package beans.table.lobby;

public class Gentescompose {
	private Gentescompose() {
	}

	public static boolean add(Long key, xbean.lobby.GentesCompose value) {
		return _Tables_.instance.gentescompose.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GentesCompose value, boolean immediately) {
		return _Tables_.instance.gentescompose.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GentesCompose get(Long key) {
		return _Tables_.instance.gentescompose.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GentesCompose select(Long key) {
		return _Tables_.instance.gentescompose.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gentescompose.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GentesCompose> getTable() {
		return _Tables_.instance.gentescompose;
	}

}
