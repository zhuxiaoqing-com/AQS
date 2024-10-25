package beans.table.lobby;

public class Offlinecarrier {
	private Offlinecarrier() {
	}

	public static boolean add(Long key, xbean.lobby.OfflineCarrier value) {
		return _Tables_.instance.offlinecarrier.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.OfflineCarrier value, boolean immediately) {
		return _Tables_.instance.offlinecarrier.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.OfflineCarrier get(Long key) {
		return _Tables_.instance.offlinecarrier.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.OfflineCarrier select(Long key) {
		return _Tables_.instance.offlinecarrier.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.offlinecarrier.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.OfflineCarrier> getTable() {
		return _Tables_.instance.offlinecarrier;
	}

}
