package beans.table.logic;

public class Playerhome {
	private Playerhome() {
	}

	public static boolean add(Long key, xbean.logic.PlayerHome value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerHome add key:" + key);
		return _Tables_.instance.playerhome.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerHome value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerHome add immediately  key:" + key);
		return _Tables_.instance.playerhome.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerHome get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerHome get key:" + key);
		return _Tables_.instance.playerhome.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerHome select(Long key) {
		return _Tables_.instance.playerhome.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerHome remove key:" + key);
		return _Tables_.instance.playerhome.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerHome> getTable() {
		return _Tables_.instance.playerhome;
	}

}
