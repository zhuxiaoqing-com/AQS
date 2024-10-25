package beans.table.lobby;

public class Offlinequest {
	private Offlinequest() {
	}

	public static boolean add(Long key, xbean.lobby.OfflineQuest value) {
		return _Tables_.instance.offlinequest.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.OfflineQuest value, boolean immediately) {
		return _Tables_.instance.offlinequest.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.OfflineQuest get(Long key) {
		return _Tables_.instance.offlinequest.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.OfflineQuest select(Long key) {
		return _Tables_.instance.offlinequest.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.offlinequest.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.OfflineQuest> getTable() {
		return _Tables_.instance.offlinequest;
	}

}
