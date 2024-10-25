package beans.table.lobby;

public class Singleachieve {
	private Singleachieve() {
	}

	public static boolean add(Integer key, xbean.lobby.SingleAchieve value) {
		return _Tables_.instance.singleachieve.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.SingleAchieve value, boolean immediately) {
		return _Tables_.instance.singleachieve.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.SingleAchieve get(Integer key) {
		return _Tables_.instance.singleachieve.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.SingleAchieve select(Integer key) {
		return _Tables_.instance.singleachieve.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.singleachieve.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.SingleAchieve> getTable() {
		return _Tables_.instance.singleachieve;
	}

}
