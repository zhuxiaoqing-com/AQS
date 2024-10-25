package beans.table.lobby;

public class Playerrankinfo {
	private Playerrankinfo() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerRankData value) {
		return _Tables_.instance.playerrankinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerRankData value, boolean immediately) {
		return _Tables_.instance.playerrankinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerRankData get(Long key) {
		return _Tables_.instance.playerrankinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerRankData select(Long key) {
		return _Tables_.instance.playerrankinfo.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerrankinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerRankData> getTable() {
		return _Tables_.instance.playerrankinfo;
	}

}
