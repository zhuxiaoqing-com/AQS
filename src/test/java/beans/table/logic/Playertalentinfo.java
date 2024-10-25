package beans.table.logic;

public class Playertalentinfo {
	private Playertalentinfo() {
	}

	public static boolean add(Long key, xbean.logic.PlayerTalentInfo value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerTalentInfo add key:" + key);
		return _Tables_.instance.playertalentinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerTalentInfo value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerTalentInfo add immediately  key:" + key);
		return _Tables_.instance.playertalentinfo.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerTalentInfo get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerTalentInfo get key:" + key);
		return _Tables_.instance.playertalentinfo.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerTalentInfo select(Long key) {
		return _Tables_.instance.playertalentinfo.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerTalentInfo remove key:" + key);
		return _Tables_.instance.playertalentinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerTalentInfo> getTable() {
		return _Tables_.instance.playertalentinfo;
	}

}
