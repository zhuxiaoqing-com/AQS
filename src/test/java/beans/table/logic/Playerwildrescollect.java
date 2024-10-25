package beans.table.logic;

public class Playerwildrescollect {
	private Playerwildrescollect() {
	}

	public static boolean add(Long key, xbean.logic.PlayerWildResCollect value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWildResCollect add key:" + key);
		return _Tables_.instance.playerwildrescollect.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerWildResCollect value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWildResCollect add immediately  key:" + key);
		return _Tables_.instance.playerwildrescollect.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerWildResCollect get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWildResCollect get key:" + key);
		return _Tables_.instance.playerwildrescollect.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerWildResCollect select(Long key) {
		return _Tables_.instance.playerwildrescollect.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerWildResCollect remove key:" + key);
		return _Tables_.instance.playerwildrescollect.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerWildResCollect> getTable() {
		return _Tables_.instance.playerwildrescollect;
	}

}
