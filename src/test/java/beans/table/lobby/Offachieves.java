package beans.table.lobby;

public class Offachieves {
	private Offachieves() {
	}

	public static boolean add(Long key, xbean.lobby.OfflinePlayerAchieve value) {
		return _Tables_.instance.offachieves.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.OfflinePlayerAchieve value, boolean immediately) {
		return _Tables_.instance.offachieves.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.OfflinePlayerAchieve get(Long key) {
		return _Tables_.instance.offachieves.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.OfflinePlayerAchieve select(Long key) {
		return _Tables_.instance.offachieves.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.offachieves.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.OfflinePlayerAchieve> getTable() {
		return _Tables_.instance.offachieves;
	}

}
