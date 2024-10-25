package beans.table.lobby;

public class Playercontestinfo {
	private Playercontestinfo() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerContestInfo value) {
		return _Tables_.instance.playercontestinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerContestInfo value, boolean immediately) {
		return _Tables_.instance.playercontestinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerContestInfo get(Long key) {
		return _Tables_.instance.playercontestinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerContestInfo select(Long key) {
		return _Tables_.instance.playercontestinfo.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playercontestinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerContestInfo> getTable() {
		return _Tables_.instance.playercontestinfo;
	}

}
