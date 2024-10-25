package beans.table.lobby;

public class Lblogicalserverinfo {
	private Lblogicalserverinfo() {
	}

	public static boolean add(Integer key, xbean.lobby.LbLogicalServerInfo value) {
		return _Tables_.instance.lblogicalserverinfo.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.LbLogicalServerInfo value, boolean immediately) {
		return _Tables_.instance.lblogicalserverinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LbLogicalServerInfo get(Integer key) {
		return _Tables_.instance.lblogicalserverinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LbLogicalServerInfo select(Integer key) {
		return _Tables_.instance.lblogicalserverinfo.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.lblogicalserverinfo.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.LbLogicalServerInfo> getTable() {
		return _Tables_.instance.lblogicalserverinfo;
	}

}
