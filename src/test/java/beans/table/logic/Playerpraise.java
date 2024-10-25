package beans.table.logic;

public class Playerpraise {
	private Playerpraise() {
	}

	public static boolean add(Long key, xbean.logic.PlayerPraise value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerPraise add key:" + key);
		return _Tables_.instance.playerpraise.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerPraise value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerPraise add immediately  key:" + key);
		return _Tables_.instance.playerpraise.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerPraise get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerPraise get key:" + key);
		return _Tables_.instance.playerpraise.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerPraise select(Long key) {
		return _Tables_.instance.playerpraise.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerPraise remove key:" + key);
		return _Tables_.instance.playerpraise.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerPraise> getTable() {
		return _Tables_.instance.playerpraise;
	}

}
