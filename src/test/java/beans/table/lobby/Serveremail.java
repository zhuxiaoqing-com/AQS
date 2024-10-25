package beans.table.lobby;

public class Serveremail {
	private Serveremail() {
	}

	public static boolean add(Integer key, xbean.lobby.ServerEmail value) {
		return _Tables_.instance.serveremail.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.ServerEmail value, boolean immediately) {
		return _Tables_.instance.serveremail.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.ServerEmail get(Integer key) {
		return _Tables_.instance.serveremail.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.ServerEmail select(Integer key) {
		return _Tables_.instance.serveremail.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.serveremail.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.ServerEmail> getTable() {
		return _Tables_.instance.serveremail;
	}

}
