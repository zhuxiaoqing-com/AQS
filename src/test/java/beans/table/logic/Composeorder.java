package beans.table.logic;

public class Composeorder {
	private Composeorder() {
	}

	public static boolean add(Long key, xbean.logic.ComposeOrder value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.ComposeOrder add key:" + key);
		return _Tables_.instance.composeorder.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.ComposeOrder value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.ComposeOrder add immediately  key:" + key);
		return _Tables_.instance.composeorder.insert(key, value, immediately) != null;
	}

	public static xbean.logic.ComposeOrder get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.ComposeOrder get key:" + key);
		return _Tables_.instance.composeorder.get(key);
	}

	// 只读模式查询
	public static xbean.logic.ComposeOrder select(Long key) {
		return _Tables_.instance.composeorder.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.ComposeOrder remove key:" + key);
		return _Tables_.instance.composeorder.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.ComposeOrder> getTable() {
		return _Tables_.instance.composeorder;
	}

}
