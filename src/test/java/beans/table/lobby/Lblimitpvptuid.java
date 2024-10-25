package beans.table.lobby;

public class Lblimitpvptuid {
	private Lblimitpvptuid() {
	}

	public static boolean add(Integer key, xbean.lobby.LBLimitPvPUid value) {
		return _Tables_.instance.lblimitpvptuid.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.LBLimitPvPUid value, boolean immediately) {
		return _Tables_.instance.lblimitpvptuid.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LBLimitPvPUid get(Integer key) {
		return _Tables_.instance.lblimitpvptuid.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LBLimitPvPUid select(Integer key) {
		return _Tables_.instance.lblimitpvptuid.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.lblimitpvptuid.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.LBLimitPvPUid> getTable() {
		return _Tables_.instance.lblimitpvptuid;
	}

}
