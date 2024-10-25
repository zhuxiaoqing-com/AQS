package beans.table.lobby;

public class Chargeorderhistory {
	private Chargeorderhistory() {
	}

	public static boolean add(Long key, xbean.lobby.OrderInfo value) {
		return _Tables_.instance.chargeorderhistory.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.OrderInfo value, boolean immediately) {
		return _Tables_.instance.chargeorderhistory.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.OrderInfo get(Long key) {
		return _Tables_.instance.chargeorderhistory.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.OrderInfo select(Long key) {
		return _Tables_.instance.chargeorderhistory.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.chargeorderhistory.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.OrderInfo> getTable() {
		return _Tables_.instance.chargeorderhistory;
	}

}
