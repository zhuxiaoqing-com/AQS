package beans.table.logic;

public class Playernewhandtarget {
	private Playernewhandtarget() {
	}

	public static boolean add(Long key, xbean.logic.PlayerNewHandTarget value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerNewHandTarget add key:" + key);
		return _Tables_.instance.playernewhandtarget.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerNewHandTarget value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerNewHandTarget add immediately  key:" + key);
		return _Tables_.instance.playernewhandtarget.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerNewHandTarget get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerNewHandTarget get key:" + key);
		return _Tables_.instance.playernewhandtarget.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerNewHandTarget select(Long key) {
		return _Tables_.instance.playernewhandtarget.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerNewHandTarget remove key:" + key);
		return _Tables_.instance.playernewhandtarget.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerNewHandTarget> getTable() {
		return _Tables_.instance.playernewhandtarget;
	}

}
