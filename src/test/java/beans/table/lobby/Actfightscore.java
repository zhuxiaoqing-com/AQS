package beans.table.lobby;

public class Actfightscore {
	private Actfightscore() {
	}

	public static boolean add(Long key, xbean.lobby.ActFightScore value) {
		return _Tables_.instance.actfightscore.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.ActFightScore value, boolean immediately) {
		return _Tables_.instance.actfightscore.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.ActFightScore get(Long key) {
		return _Tables_.instance.actfightscore.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.ActFightScore select(Long key) {
		return _Tables_.instance.actfightscore.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.actfightscore.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.ActFightScore> getTable() {
		return _Tables_.instance.actfightscore;
	}

}
