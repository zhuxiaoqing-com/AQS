package beans.table.lobby;

public class Gentesname {
	private Gentesname() {
	}

	public static boolean add(String key, Long value) {
		return _Tables_.instance.gentesname.insert(key, value) != null;
	}

	public static boolean add(String key, Long value, boolean immediately) {
		return _Tables_.instance.gentesname.insert(key, value, immediately) != null;
	}

	public static Long get(String key) {
		return _Tables_.instance.gentesname.get(key);
	}

	// 只读模式查询
	public static Long select(String key) {
		return _Tables_.instance.gentesname.select(key);
	}

	public static boolean remove(String key) {
		return _Tables_.instance.gentesname.delete(key);
	}

	public static warrior.mdb.TTable<String, Long> getTable() {
		return _Tables_.instance.gentesname;
	}

}
