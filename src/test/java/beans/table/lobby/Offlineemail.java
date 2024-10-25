package beans.table.lobby;

public class Offlineemail {
	private Offlineemail() {
	}

	public static boolean add(Long key, xbean.lobby.OfflinePlayerEmails value) {
		return _Tables_.instance.offlineemail.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.OfflinePlayerEmails value, boolean immediately) {
		return _Tables_.instance.offlineemail.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.OfflinePlayerEmails get(Long key) {
		return _Tables_.instance.offlineemail.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.OfflinePlayerEmails select(Long key) {
		return _Tables_.instance.offlineemail.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.offlineemail.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.OfflinePlayerEmails> getTable() {
		return _Tables_.instance.offlineemail;
	}

}
