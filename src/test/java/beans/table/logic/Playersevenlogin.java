package beans.table.logic;

public class Playersevenlogin {
	private Playersevenlogin() {
	}

	public static boolean add(Long key, xbean.logic.PlayerSevenLogin value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSevenLogin add key:" + key);
		return _Tables_.instance.playersevenlogin.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerSevenLogin value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSevenLogin add immediately  key:" + key);
		return _Tables_.instance.playersevenlogin.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerSevenLogin get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSevenLogin get key:" + key);
		return _Tables_.instance.playersevenlogin.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerSevenLogin select(Long key) {
		return _Tables_.instance.playersevenlogin.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSevenLogin remove key:" + key);
		return _Tables_.instance.playersevenlogin.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerSevenLogin> getTable() {
		return _Tables_.instance.playersevenlogin;
	}

}
