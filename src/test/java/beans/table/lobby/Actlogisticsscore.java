package beans.table.lobby;

public class Actlogisticsscore {
	private Actlogisticsscore() {
	}

	public static boolean add(Long key, xbean.lobby.ActLogisticsScore value) {
		return _Tables_.instance.actlogisticsscore.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.ActLogisticsScore value, boolean immediately) {
		return _Tables_.instance.actlogisticsscore.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.ActLogisticsScore get(Long key) {
		return _Tables_.instance.actlogisticsscore.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.ActLogisticsScore select(Long key) {
		return _Tables_.instance.actlogisticsscore.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.actlogisticsscore.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.ActLogisticsScore> getTable() {
		return _Tables_.instance.actlogisticsscore;
	}

}
