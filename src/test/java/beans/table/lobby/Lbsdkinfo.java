package beans.table.lobby;

public class Lbsdkinfo {
	private Lbsdkinfo() {
	}

	public static boolean add(String key, xbean.lobby.ServerPlayerInfo value) {
		return _Tables_.instance.lbsdkinfo.insert(key, value) != null;
	}

	public static boolean add(String key, xbean.lobby.ServerPlayerInfo value, boolean immediately) {
		return _Tables_.instance.lbsdkinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.ServerPlayerInfo get(String key) {
		return _Tables_.instance.lbsdkinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.ServerPlayerInfo select(String key) {
		return _Tables_.instance.lbsdkinfo.select(key);
	}

	public static boolean remove(String key) {
		return _Tables_.instance.lbsdkinfo.delete(key);
	}

	public static warrior.mdb.TTable<String, xbean.lobby.ServerPlayerInfo> getTable() {
		return _Tables_.instance.lbsdkinfo;
	}

}
