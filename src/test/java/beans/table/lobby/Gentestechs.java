package beans.table.lobby;

public class Gentestechs {
	private Gentestechs() {
	}

	public static boolean add(Long key, xbean.lobby.GentesTech value) {
		return _Tables_.instance.gentestechs.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GentesTech value, boolean immediately) {
		return _Tables_.instance.gentestechs.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GentesTech get(Long key) {
		return _Tables_.instance.gentestechs.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GentesTech select(Long key) {
		return _Tables_.instance.gentestechs.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gentestechs.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GentesTech> getTable() {
		return _Tables_.instance.gentestechs;
	}

}
