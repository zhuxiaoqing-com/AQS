package beans.table.logic;

public class Playerdungeonrecord {
	private Playerdungeonrecord() {
	}

	public static boolean add(Long key, xbean.logic.PlayerDungeonRecord value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDungeonRecord add key:" + key);
		return _Tables_.instance.playerdungeonrecord.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerDungeonRecord value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDungeonRecord add immediately  key:" + key);
		return _Tables_.instance.playerdungeonrecord.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerDungeonRecord get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDungeonRecord get key:" + key);
		return _Tables_.instance.playerdungeonrecord.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerDungeonRecord select(Long key) {
		return _Tables_.instance.playerdungeonrecord.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDungeonRecord remove key:" + key);
		return _Tables_.instance.playerdungeonrecord.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerDungeonRecord> getTable() {
		return _Tables_.instance.playerdungeonrecord;
	}

}
