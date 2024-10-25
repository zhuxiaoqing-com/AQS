package beans.table.logic;

public class Playerupaward {
	private Playerupaward() {
	}

	public static boolean add(Long key, xbean.logic.PlayerUpAward value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerUpAward add key:" + key);
		return _Tables_.instance.playerupaward.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerUpAward value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerUpAward add immediately  key:" + key);
		return _Tables_.instance.playerupaward.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerUpAward get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerUpAward get key:" + key);
		return _Tables_.instance.playerupaward.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerUpAward select(Long key) {
		return _Tables_.instance.playerupaward.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerUpAward remove key:" + key);
		return _Tables_.instance.playerupaward.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerUpAward> getTable() {
		return _Tables_.instance.playerupaward;
	}

}
