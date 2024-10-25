package beans.table.logic;

public class Playermatchcount {
	private Playermatchcount() {
	}

	public static boolean add(Long key, xbean.logic.PlayerMatchCount value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMatchCount add key:" + key);
		return _Tables_.instance.playermatchcount.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerMatchCount value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMatchCount add immediately  key:" + key);
		return _Tables_.instance.playermatchcount.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerMatchCount get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMatchCount get key:" + key);
		return _Tables_.instance.playermatchcount.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerMatchCount select(Long key) {
		return _Tables_.instance.playermatchcount.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMatchCount remove key:" + key);
		return _Tables_.instance.playermatchcount.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerMatchCount> getTable() {
		return _Tables_.instance.playermatchcount;
	}

}
