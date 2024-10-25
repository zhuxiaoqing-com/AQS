package beans.table.logic;

public class Playerfaction {
	private Playerfaction() {
	}

	public static boolean add(Long key, xbean.logic.PlayerFaction value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerFaction add key:" + key);
		return _Tables_.instance.playerfaction.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerFaction value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerFaction add immediately  key:" + key);
		return _Tables_.instance.playerfaction.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerFaction get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerFaction get key:" + key);
		return _Tables_.instance.playerfaction.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerFaction select(Long key) {
		return _Tables_.instance.playerfaction.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerFaction remove key:" + key);
		return _Tables_.instance.playerfaction.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerFaction> getTable() {
		return _Tables_.instance.playerfaction;
	}

}
