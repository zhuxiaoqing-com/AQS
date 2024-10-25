package beans.table.logic;

public class Playercollect {
	private Playercollect() {
	}

	public static boolean add(Long key, xbean.logic.PlayerCollect value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCollect add key:" + key);
		return _Tables_.instance.playercollect.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerCollect value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCollect add immediately  key:" + key);
		return _Tables_.instance.playercollect.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerCollect get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCollect get key:" + key);
		return _Tables_.instance.playercollect.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerCollect select(Long key) {
		return _Tables_.instance.playercollect.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCollect remove key:" + key);
		return _Tables_.instance.playercollect.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerCollect> getTable() {
		return _Tables_.instance.playercollect;
	}

}
