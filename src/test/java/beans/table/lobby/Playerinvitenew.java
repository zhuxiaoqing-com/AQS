package beans.table.lobby;

public class Playerinvitenew {
	private Playerinvitenew() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerInviteNew value) {
		return _Tables_.instance.playerinvitenew.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerInviteNew value, boolean immediately) {
		return _Tables_.instance.playerinvitenew.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerInviteNew get(Long key) {
		return _Tables_.instance.playerinvitenew.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerInviteNew select(Long key) {
		return _Tables_.instance.playerinvitenew.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerinvitenew.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerInviteNew> getTable() {
		return _Tables_.instance.playerinvitenew;
	}

}
