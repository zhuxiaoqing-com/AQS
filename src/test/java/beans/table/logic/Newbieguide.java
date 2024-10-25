package beans.table.logic;

public class Newbieguide {
	private Newbieguide() {
	}

	public static boolean add(Long key, xbean.logic.NewbieGuide value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.NewbieGuide add key:" + key);
		return _Tables_.instance.newbieguide.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.NewbieGuide value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.NewbieGuide add immediately  key:" + key);
		return _Tables_.instance.newbieguide.insert(key, value, immediately) != null;
	}

	public static xbean.logic.NewbieGuide get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.NewbieGuide get key:" + key);
		return _Tables_.instance.newbieguide.get(key);
	}

	// 只读模式查询
	public static xbean.logic.NewbieGuide select(Long key) {
		return _Tables_.instance.newbieguide.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.NewbieGuide remove key:" + key);
		return _Tables_.instance.newbieguide.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.NewbieGuide> getTable() {
		return _Tables_.instance.newbieguide;
	}

}
