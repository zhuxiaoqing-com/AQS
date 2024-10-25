package beans.table.lobby;

public class Playerfriend {
	private Playerfriend() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerFriend value) {
		return _Tables_.instance.playerfriend.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerFriend value, boolean immediately) {
		return _Tables_.instance.playerfriend.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerFriend get(Long key) {
		return _Tables_.instance.playerfriend.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerFriend select(Long key) {
		return _Tables_.instance.playerfriend.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerfriend.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerFriend> getTable() {
		return _Tables_.instance.playerfriend;
	}

}
