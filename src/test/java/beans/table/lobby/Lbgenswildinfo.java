package beans.table.lobby;

public class Lbgenswildinfo {
	private Lbgenswildinfo() {
	}

	public static boolean add(Integer key, xbean.lobby.LBGensWildInfo value) {
		return _Tables_.instance.lbgenswildinfo.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.LBGensWildInfo value, boolean immediately) {
		return _Tables_.instance.lbgenswildinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LBGensWildInfo get(Integer key) {
		return _Tables_.instance.lbgenswildinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LBGensWildInfo select(Integer key) {
		return _Tables_.instance.lbgenswildinfo.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.lbgenswildinfo.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.LBGensWildInfo> getTable() {
		return _Tables_.instance.lbgenswildinfo;
	}

}
