package beans.table.logic;

public class Playerskilled {
	private Playerskilled() {
	}

	public static boolean add(Long key, xbean.logic.PlayerSkilledExpInfo value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSkilledExpInfo add key:" + key);
		return _Tables_.instance.playerskilled.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerSkilledExpInfo value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSkilledExpInfo add immediately  key:" + key);
		return _Tables_.instance.playerskilled.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerSkilledExpInfo get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSkilledExpInfo get key:" + key);
		return _Tables_.instance.playerskilled.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerSkilledExpInfo select(Long key) {
		return _Tables_.instance.playerskilled.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSkilledExpInfo remove key:" + key);
		return _Tables_.instance.playerskilled.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerSkilledExpInfo> getTable() {
		return _Tables_.instance.playerskilled;
	}

}
