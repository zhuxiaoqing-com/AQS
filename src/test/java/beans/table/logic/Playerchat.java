package beans.table.logic;

public class Playerchat {
	private Playerchat() {
	}

	public static boolean add(Long key, xbean.logic.PlayerChat value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerChat add key:" + key);
		return _Tables_.instance.playerchat.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerChat value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerChat add immediately  key:" + key);
		return _Tables_.instance.playerchat.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerChat get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerChat get key:" + key);
		return _Tables_.instance.playerchat.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerChat select(Long key) {
		return _Tables_.instance.playerchat.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerChat remove key:" + key);
		return _Tables_.instance.playerchat.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerChat> getTable() {
		return _Tables_.instance.playerchat;
	}

}
