package beans.table.logic;

public class Playerguide {
	private Playerguide() {
	}

	public static boolean add(Long key, xbean.logic.PlayerGuide value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerGuide add key:" + key);
		return _Tables_.instance.playerguide.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerGuide value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerGuide add immediately  key:" + key);
		return _Tables_.instance.playerguide.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerGuide get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerGuide get key:" + key);
		return _Tables_.instance.playerguide.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerGuide select(Long key) {
		return _Tables_.instance.playerguide.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerGuide remove key:" + key);
		return _Tables_.instance.playerguide.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerGuide> getTable() {
		return _Tables_.instance.playerguide;
	}

}
