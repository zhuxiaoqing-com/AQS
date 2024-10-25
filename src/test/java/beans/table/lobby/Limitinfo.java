package beans.table.lobby;

public class Limitinfo {
	private Limitinfo() {
	}

	public static boolean add(Integer key, xbean.lobby.LimitUidIp value) {
		return _Tables_.instance.limitinfo.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.LimitUidIp value, boolean immediately) {
		return _Tables_.instance.limitinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LimitUidIp get(Integer key) {
		return _Tables_.instance.limitinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LimitUidIp select(Integer key) {
		return _Tables_.instance.limitinfo.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.limitinfo.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.LimitUidIp> getTable() {
		return _Tables_.instance.limitinfo;
	}

}
