package beans.table.logic;

public class Playereatchicken {
	private Playereatchicken() {
	}

	public static boolean add(Long key, xbean.logic.PlayerEatChicken value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEatChicken add key:" + key);
		return _Tables_.instance.playereatchicken.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerEatChicken value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEatChicken add immediately  key:" + key);
		return _Tables_.instance.playereatchicken.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerEatChicken get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEatChicken get key:" + key);
		return _Tables_.instance.playereatchicken.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerEatChicken select(Long key) {
		return _Tables_.instance.playereatchicken.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEatChicken remove key:" + key);
		return _Tables_.instance.playereatchicken.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerEatChicken> getTable() {
		return _Tables_.instance.playereatchicken;
	}

}
