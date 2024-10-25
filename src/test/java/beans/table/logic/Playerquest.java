package beans.table.logic;

public class Playerquest {
	private Playerquest() {
	}

	public static boolean add(Long key, xbean.logic.PlayerQuest value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerQuest add key:" + key);
		return _Tables_.instance.playerquest.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerQuest value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerQuest add immediately  key:" + key);
		return _Tables_.instance.playerquest.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerQuest get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerQuest get key:" + key);
		return _Tables_.instance.playerquest.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerQuest select(Long key) {
		return _Tables_.instance.playerquest.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerQuest remove key:" + key);
		return _Tables_.instance.playerquest.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerQuest> getTable() {
		return _Tables_.instance.playerquest;
	}

}
