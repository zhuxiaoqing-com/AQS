package beans.table.lobby;

public class Genscontest {
	private Genscontest() {
	}

	public static boolean add(Long key, xbean.lobby.GensContest value) {
		return _Tables_.instance.genscontest.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GensContest value, boolean immediately) {
		return _Tables_.instance.genscontest.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GensContest get(Long key) {
		return _Tables_.instance.genscontest.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GensContest select(Long key) {
		return _Tables_.instance.genscontest.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.genscontest.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GensContest> getTable() {
		return _Tables_.instance.genscontest;
	}

}
