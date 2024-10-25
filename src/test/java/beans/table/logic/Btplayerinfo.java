package beans.table.logic;

public class Btplayerinfo {
	private Btplayerinfo() {
	}

	public static boolean add(Long key, xbean.logic.BTPlayerInfo value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.BTPlayerInfo add key:" + key);
		return _Tables_.instance.btplayerinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.BTPlayerInfo value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.BTPlayerInfo add immediately  key:" + key);
		return _Tables_.instance.btplayerinfo.insert(key, value, immediately) != null;
	}

	public static xbean.logic.BTPlayerInfo get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.BTPlayerInfo get key:" + key);
		return _Tables_.instance.btplayerinfo.get(key);
	}

	// 只读模式查询
	public static xbean.logic.BTPlayerInfo select(Long key) {
		return _Tables_.instance.btplayerinfo.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.BTPlayerInfo remove key:" + key);
		return _Tables_.instance.btplayerinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.BTPlayerInfo> getTable() {
		return _Tables_.instance.btplayerinfo;
	}

}
