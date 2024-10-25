package beans.table.logic;

public class Playerbuffinfo {
	private Playerbuffinfo() {
	}

	public static boolean add(Long key, xbean.logic.PlayerBuffInfo value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerBuffInfo add key:" + key);
		return _Tables_.instance.playerbuffinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerBuffInfo value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerBuffInfo add immediately  key:" + key);
		return _Tables_.instance.playerbuffinfo.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerBuffInfo get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerBuffInfo get key:" + key);
		return _Tables_.instance.playerbuffinfo.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerBuffInfo select(Long key) {
		return _Tables_.instance.playerbuffinfo.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerBuffInfo remove key:" + key);
		return _Tables_.instance.playerbuffinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerBuffInfo> getTable() {
		return _Tables_.instance.playerbuffinfo;
	}

}
