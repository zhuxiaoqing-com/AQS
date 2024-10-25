package beans.table.logic;

public class Playerskin {
	private Playerskin() {
	}

	public static boolean add(Long key, xbean.logic.PlayerSkin value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSkin add key:" + key);
		return _Tables_.instance.playerskin.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerSkin value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSkin add immediately  key:" + key);
		return _Tables_.instance.playerskin.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerSkin get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSkin get key:" + key);
		return _Tables_.instance.playerskin.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerSkin select(Long key) {
		return _Tables_.instance.playerskin.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerSkin remove key:" + key);
		return _Tables_.instance.playerskin.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerSkin> getTable() {
		return _Tables_.instance.playerskin;
	}

}
