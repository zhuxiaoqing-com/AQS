package beans.table.lobby;

public class Lbplayerpraise {
	private Lbplayerpraise() {
	}

	public static boolean add(Long key, xbean.lobby.LbPlayerPraise value) {
		return _Tables_.instance.lbplayerpraise.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.LbPlayerPraise value, boolean immediately) {
		return _Tables_.instance.lbplayerpraise.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LbPlayerPraise get(Long key) {
		return _Tables_.instance.lbplayerpraise.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LbPlayerPraise select(Long key) {
		return _Tables_.instance.lbplayerpraise.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.lbplayerpraise.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.LbPlayerPraise> getTable() {
		return _Tables_.instance.lbplayerpraise;
	}

}
