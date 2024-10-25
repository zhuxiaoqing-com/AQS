package beans.table.lobby;

public class Gensmember {
	private Gensmember() {
	}

	public static boolean add(Long key, xbean.lobby.GensMember value) {
		return _Tables_.instance.gensmember.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GensMember value, boolean immediately) {
		return _Tables_.instance.gensmember.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GensMember get(Long key) {
		return _Tables_.instance.gensmember.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GensMember select(Long key) {
		return _Tables_.instance.gensmember.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gensmember.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GensMember> getTable() {
		return _Tables_.instance.gensmember;
	}

}
