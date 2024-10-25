package beans.table.lobby;

public class Playerstatisticslist {
	private Playerstatisticslist() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerStatisticsList value) {
		return _Tables_.instance.playerstatisticslist.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerStatisticsList value, boolean immediately) {
		return _Tables_.instance.playerstatisticslist.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerStatisticsList get(Long key) {
		return _Tables_.instance.playerstatisticslist.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerStatisticsList select(Long key) {
		return _Tables_.instance.playerstatisticslist.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerstatisticslist.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerStatisticsList> getTable() {
		return _Tables_.instance.playerstatisticslist;
	}

}
