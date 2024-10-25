package beans.table.logic;

public class Lbplayerinfo {
	private Lbplayerinfo() {
	}

	public static boolean add(Long key, xbean.logic.LBPlayerInfo value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.LBPlayerInfo add key:" + key);
		return _Tables_.instance.lbplayerinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.LBPlayerInfo value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.LBPlayerInfo add immediately  key:" + key);
		return _Tables_.instance.lbplayerinfo.insert(key, value, immediately) != null;
	}

	public static xbean.logic.LBPlayerInfo get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.LBPlayerInfo get key:" + key);
		return _Tables_.instance.lbplayerinfo.get(key);
	}

	// 只读模式查询
	public static xbean.logic.LBPlayerInfo select(Long key) {
		return _Tables_.instance.lbplayerinfo.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.LBPlayerInfo remove key:" + key);
		return _Tables_.instance.lbplayerinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.LBPlayerInfo> getTable() {
		return _Tables_.instance.lbplayerinfo;
	}

}
