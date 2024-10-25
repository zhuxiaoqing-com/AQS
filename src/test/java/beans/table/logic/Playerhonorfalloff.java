package beans.table.logic;

public class Playerhonorfalloff {
	private Playerhonorfalloff() {
	}

	public static boolean add(Long key, xbean.logic.PlayerHonorFalloff value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerHonorFalloff add key:" + key);
		return _Tables_.instance.playerhonorfalloff.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerHonorFalloff value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerHonorFalloff add immediately  key:" + key);
		return _Tables_.instance.playerhonorfalloff.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerHonorFalloff get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerHonorFalloff get key:" + key);
		return _Tables_.instance.playerhonorfalloff.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerHonorFalloff select(Long key) {
		return _Tables_.instance.playerhonorfalloff.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerHonorFalloff remove key:" + key);
		return _Tables_.instance.playerhonorfalloff.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerHonorFalloff> getTable() {
		return _Tables_.instance.playerhonorfalloff;
	}

}
