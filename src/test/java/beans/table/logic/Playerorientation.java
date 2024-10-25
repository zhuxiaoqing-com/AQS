package beans.table.logic;

public class Playerorientation {
	private Playerorientation() {
	}

	public static boolean add(Long key, xbean.logic.PlayerOrientation value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerOrientation add key:" + key);
		return _Tables_.instance.playerorientation.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerOrientation value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerOrientation add immediately  key:" + key);
		return _Tables_.instance.playerorientation.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerOrientation get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerOrientation get key:" + key);
		return _Tables_.instance.playerorientation.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerOrientation select(Long key) {
		return _Tables_.instance.playerorientation.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerOrientation remove key:" + key);
		return _Tables_.instance.playerorientation.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerOrientation> getTable() {
		return _Tables_.instance.playerorientation;
	}

}
