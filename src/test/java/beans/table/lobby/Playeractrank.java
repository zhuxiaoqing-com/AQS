package beans.table.lobby;

public class Playeractrank {
	private Playeractrank() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerActRank value) {
		return _Tables_.instance.playeractrank.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerActRank value, boolean immediately) {
		return _Tables_.instance.playeractrank.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerActRank get(Long key) {
		return _Tables_.instance.playeractrank.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerActRank select(Long key) {
		return _Tables_.instance.playeractrank.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playeractrank.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerActRank> getTable() {
		return _Tables_.instance.playeractrank;
	}

}
