package beans.table.lobby;

public class Factiondepotrecordcollect {
	private Factiondepotrecordcollect() {
	}

	public static boolean add(Integer key, xbean.lobby.FactionDepotRecordCollect value) {
		return _Tables_.instance.factiondepotrecordcollect.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.FactionDepotRecordCollect value, boolean immediately) {
		return _Tables_.instance.factiondepotrecordcollect.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.FactionDepotRecordCollect get(Integer key) {
		return _Tables_.instance.factiondepotrecordcollect.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.FactionDepotRecordCollect select(Integer key) {
		return _Tables_.instance.factiondepotrecordcollect.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.factiondepotrecordcollect.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.FactionDepotRecordCollect> getTable() {
		return _Tables_.instance.factiondepotrecordcollect;
	}

}
