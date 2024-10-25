package beans.table.lobby;

public class Allianceids {
	private Allianceids() {
	}

	public static boolean add(Integer key, xbean.lobby.AllianceIds value) {
		return _Tables_.instance.allianceids.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.AllianceIds value, boolean immediately) {
		return _Tables_.instance.allianceids.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.AllianceIds get(Integer key) {
		return _Tables_.instance.allianceids.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.AllianceIds select(Integer key) {
		return _Tables_.instance.allianceids.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.allianceids.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.AllianceIds> getTable() {
		return _Tables_.instance.allianceids;
	}

}
