package beans.table.lobby;

public class Playerstatisticslog {
	private Playerstatisticslog() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerStatisticsLog value) {
		return _Tables_.instance.playerstatisticslog.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerStatisticsLog value, boolean immediately) {
		return _Tables_.instance.playerstatisticslog.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerStatisticsLog get(Long key) {
		return _Tables_.instance.playerstatisticslog.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerStatisticsLog select(Long key) {
		return _Tables_.instance.playerstatisticslog.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerstatisticslog.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerStatisticsLog> getTable() {
		return _Tables_.instance.playerstatisticslog;
	}

}
