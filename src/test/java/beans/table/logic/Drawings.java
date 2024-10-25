package beans.table.logic;

public class Drawings {
	private Drawings() {
	}

	public static boolean add(Long key, xbean.logic.PlayerDrawings value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDrawings add key:" + key);
		return _Tables_.instance.drawings.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerDrawings value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDrawings add immediately  key:" + key);
		return _Tables_.instance.drawings.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerDrawings get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDrawings get key:" + key);
		return _Tables_.instance.drawings.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerDrawings select(Long key) {
		return _Tables_.instance.drawings.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerDrawings remove key:" + key);
		return _Tables_.instance.drawings.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerDrawings> getTable() {
		return _Tables_.instance.drawings;
	}

}
