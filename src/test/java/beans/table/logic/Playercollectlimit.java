package beans.table.logic;

public class Playercollectlimit {
	private Playercollectlimit() {
	}

	public static boolean add(Long key, xbean.logic.PlayerCollectLimit value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCollectLimit add key:" + key);
		return _Tables_.instance.playercollectlimit.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerCollectLimit value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCollectLimit add immediately  key:" + key);
		return _Tables_.instance.playercollectlimit.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerCollectLimit get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCollectLimit get key:" + key);
		return _Tables_.instance.playercollectlimit.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerCollectLimit select(Long key) {
		return _Tables_.instance.playercollectlimit.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCollectLimit remove key:" + key);
		return _Tables_.instance.playercollectlimit.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerCollectLimit> getTable() {
		return _Tables_.instance.playercollectlimit;
	}

}
