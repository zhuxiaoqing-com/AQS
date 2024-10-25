package beans.table.lobby;

public class Lblimitchatuid {
	private Lblimitchatuid() {
	}

	public static boolean add(Integer key, xbean.lobby.LBLimitChatUid value) {
		return _Tables_.instance.lblimitchatuid.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.LBLimitChatUid value, boolean immediately) {
		return _Tables_.instance.lblimitchatuid.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LBLimitChatUid get(Integer key) {
		return _Tables_.instance.lblimitchatuid.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LBLimitChatUid select(Integer key) {
		return _Tables_.instance.lblimitchatuid.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.lblimitchatuid.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.LBLimitChatUid> getTable() {
		return _Tables_.instance.lblimitchatuid;
	}

}
