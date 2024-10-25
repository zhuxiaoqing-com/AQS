package beans.table.logic;

public class Playernewbie {
	private Playernewbie() {
	}

	public static boolean add(Long key, xbean.logic.PlayerNewbie value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerNewbie add key:" + key);
		return _Tables_.instance.playernewbie.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerNewbie value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerNewbie add immediately  key:" + key);
		return _Tables_.instance.playernewbie.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerNewbie get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerNewbie get key:" + key);
		return _Tables_.instance.playernewbie.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerNewbie select(Long key) {
		return _Tables_.instance.playernewbie.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerNewbie remove key:" + key);
		return _Tables_.instance.playernewbie.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerNewbie> getTable() {
		return _Tables_.instance.playernewbie;
	}

}
