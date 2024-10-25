package beans.table.lobby;

public class Lbbattleinfo {
	private Lbbattleinfo() {
	}

	public static boolean add(Long key, xbean.lobby.LBBattleInfo value) {
		return _Tables_.instance.lbbattleinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.LBBattleInfo value, boolean immediately) {
		return _Tables_.instance.lbbattleinfo.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LBBattleInfo get(Long key) {
		return _Tables_.instance.lbbattleinfo.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LBBattleInfo select(Long key) {
		return _Tables_.instance.lbbattleinfo.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.lbbattleinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.LBBattleInfo> getTable() {
		return _Tables_.instance.lbbattleinfo;
	}

}
