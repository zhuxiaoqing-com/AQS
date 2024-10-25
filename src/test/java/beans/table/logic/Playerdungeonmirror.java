package beans.table.logic;

public class Playerdungeonmirror {
	private Playerdungeonmirror() {
	}

	public static boolean add(Long key, xbean.logic.PlayerDungeonMirror value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDungeonMirror add key:" + key);
		return _Tables_.instance.playerdungeonmirror.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerDungeonMirror value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDungeonMirror add immediately  key:" + key);
		return _Tables_.instance.playerdungeonmirror.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerDungeonMirror get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDungeonMirror get key:" + key);
		return _Tables_.instance.playerdungeonmirror.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerDungeonMirror select(Long key) {
		return _Tables_.instance.playerdungeonmirror.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDungeonMirror remove key:" + key);
		return _Tables_.instance.playerdungeonmirror.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerDungeonMirror> getTable() {
		return _Tables_.instance.playerdungeonmirror;
	}

}
