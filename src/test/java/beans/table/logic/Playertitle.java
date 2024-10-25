package beans.table.logic;

public class Playertitle {
	private Playertitle() {
	}

	public static boolean add(Long key, xbean.logic.PlayerTitle value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerTitle add key:" + key);
		return _Tables_.instance.playertitle.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerTitle value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerTitle add immediately  key:" + key);
		return _Tables_.instance.playertitle.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerTitle get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerTitle get key:" + key);
		return _Tables_.instance.playertitle.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerTitle select(Long key) {
		return _Tables_.instance.playertitle.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerTitle remove key:" + key);
		return _Tables_.instance.playertitle.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerTitle> getTable() {
		return _Tables_.instance.playertitle;
	}

}
