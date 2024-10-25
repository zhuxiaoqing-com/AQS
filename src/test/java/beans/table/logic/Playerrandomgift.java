package beans.table.logic;

public class Playerrandomgift {
	private Playerrandomgift() {
	}

	public static boolean add(Long key, xbean.logic.PlayerRandomGift value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerRandomGift add key:" + key);
		return _Tables_.instance.playerrandomgift.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerRandomGift value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerRandomGift add immediately  key:" + key);
		return _Tables_.instance.playerrandomgift.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerRandomGift get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerRandomGift get key:" + key);
		return _Tables_.instance.playerrandomgift.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerRandomGift select(Long key) {
		return _Tables_.instance.playerrandomgift.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerRandomGift remove key:" + key);
		return _Tables_.instance.playerrandomgift.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerRandomGift> getTable() {
		return _Tables_.instance.playerrandomgift;
	}

}
