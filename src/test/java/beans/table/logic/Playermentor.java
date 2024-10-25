package beans.table.logic;

public class Playermentor {
	private Playermentor() {
	}

	public static boolean add(Long key, xbean.logic.PlayerMentor value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMentor add key:" + key);
		return _Tables_.instance.playermentor.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerMentor value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMentor add immediately  key:" + key);
		return _Tables_.instance.playermentor.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerMentor get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMentor get key:" + key);
		return _Tables_.instance.playermentor.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerMentor select(Long key) {
		return _Tables_.instance.playermentor.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerMentor remove key:" + key);
		return _Tables_.instance.playermentor.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerMentor> getTable() {
		return _Tables_.instance.playermentor;
	}

}
