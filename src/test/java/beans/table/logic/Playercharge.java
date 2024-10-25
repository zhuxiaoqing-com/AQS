package beans.table.logic;

public class Playercharge {
	private Playercharge() {
	}

	public static boolean add(Long key, xbean.logic.PlayerCharge value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCharge add key:" + key);
		return _Tables_.instance.playercharge.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerCharge value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCharge add immediately  key:" + key);
		return _Tables_.instance.playercharge.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerCharge get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCharge get key:" + key);
		return _Tables_.instance.playercharge.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerCharge select(Long key) {
		return _Tables_.instance.playercharge.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerCharge remove key:" + key);
		return _Tables_.instance.playercharge.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerCharge> getTable() {
		return _Tables_.instance.playercharge;
	}

}
