package beans.table.logic;

public class Playerfightproperty {
	private Playerfightproperty() {
	}

	public static boolean add(Long key, xbean.logic.PlayerFightProperty value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerFightProperty add key:" + key);
		return _Tables_.instance.playerfightproperty.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerFightProperty value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerFightProperty add immediately  key:" + key);
		return _Tables_.instance.playerfightproperty.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerFightProperty get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerFightProperty get key:" + key);
		return _Tables_.instance.playerfightproperty.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerFightProperty select(Long key) {
		return _Tables_.instance.playerfightproperty.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerFightProperty remove key:" + key);
		return _Tables_.instance.playerfightproperty.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerFightProperty> getTable() {
		return _Tables_.instance.playerfightproperty;
	}

}
