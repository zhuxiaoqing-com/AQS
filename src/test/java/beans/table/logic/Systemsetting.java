package beans.table.logic;

public class Systemsetting {
	private Systemsetting() {
	}

	public static boolean add(Long key, xbean.logic.SystemSetting value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.SystemSetting add key:" + key);
		return _Tables_.instance.systemsetting.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.SystemSetting value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.SystemSetting add immediately  key:" + key);
		return _Tables_.instance.systemsetting.insert(key, value, immediately) != null;
	}

	public static xbean.logic.SystemSetting get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.SystemSetting get key:" + key);
		return _Tables_.instance.systemsetting.get(key);
	}

	// 只读模式查询
	public static xbean.logic.SystemSetting select(Long key) {
		return _Tables_.instance.systemsetting.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.SystemSetting remove key:" + key);
		return _Tables_.instance.systemsetting.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.SystemSetting> getTable() {
		return _Tables_.instance.systemsetting;
	}

}
