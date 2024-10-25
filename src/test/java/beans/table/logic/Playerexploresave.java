package beans.table.logic;

public class Playerexploresave {
	private Playerexploresave() {
	}

	public static boolean add(Long key, xbean.logic.PlayerExploreSave value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerExploreSave add key:" + key);
		return _Tables_.instance.playerexploresave.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerExploreSave value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerExploreSave add immediately  key:" + key);
		return _Tables_.instance.playerexploresave.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerExploreSave get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerExploreSave get key:" + key);
		return _Tables_.instance.playerexploresave.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerExploreSave select(Long key) {
		return _Tables_.instance.playerexploresave.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerExploreSave remove key:" + key);
		return _Tables_.instance.playerexploresave.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerExploreSave> getTable() {
		return _Tables_.instance.playerexploresave;
	}

}
