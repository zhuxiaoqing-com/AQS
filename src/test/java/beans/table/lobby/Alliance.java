package beans.table.lobby;

public class Alliance {
	private Alliance() {
	}

	public static boolean add(Long key, xbean.lobby.Alliance value) {
		return _Tables_.instance.alliance.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.Alliance value, boolean immediately) {
		return _Tables_.instance.alliance.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.Alliance get(Long key) {
		return _Tables_.instance.alliance.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.Alliance select(Long key) {
		return _Tables_.instance.alliance.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.alliance.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.Alliance> getTable() {
		return _Tables_.instance.alliance;
	}

}
