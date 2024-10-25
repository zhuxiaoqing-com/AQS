package beans.table.logic;

public class Playermapinfo {
	private Playermapinfo() {
	}

	public static boolean add(Long key, xbean.logic.PlayerMapInfo value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMapInfo add key:" + key);
		return _Tables_.instance.playermapinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerMapInfo value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMapInfo add immediately  key:" + key);
		return _Tables_.instance.playermapinfo.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerMapInfo get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMapInfo get key:" + key);
		return _Tables_.instance.playermapinfo.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerMapInfo select(Long key) {
		return _Tables_.instance.playermapinfo.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMapInfo remove key:" + key);
		return _Tables_.instance.playermapinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerMapInfo> getTable() {
		return _Tables_.instance.playermapinfo;
	}

}
