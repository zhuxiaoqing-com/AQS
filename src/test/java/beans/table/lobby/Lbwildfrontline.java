package beans.table.lobby;

public class Lbwildfrontline {
	private Lbwildfrontline() {
	}

	public static boolean add(Integer key, xbean.lobby.LBWildFrontLine value) {
		return _Tables_.instance.lbwildfrontline.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.LBWildFrontLine value, boolean immediately) {
		return _Tables_.instance.lbwildfrontline.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LBWildFrontLine get(Integer key) {
		return _Tables_.instance.lbwildfrontline.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LBWildFrontLine select(Integer key) {
		return _Tables_.instance.lbwildfrontline.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.lbwildfrontline.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.LBWildFrontLine> getTable() {
		return _Tables_.instance.lbwildfrontline;
	}

}
