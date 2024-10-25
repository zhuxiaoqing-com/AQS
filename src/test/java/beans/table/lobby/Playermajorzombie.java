package beans.table.lobby;

public class Playermajorzombie {
	private Playermajorzombie() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerMajorZombie value) {
		return _Tables_.instance.playermajorzombie.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerMajorZombie value, boolean immediately) {
		return _Tables_.instance.playermajorzombie.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerMajorZombie get(Long key) {
		return _Tables_.instance.playermajorzombie.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerMajorZombie select(Long key) {
		return _Tables_.instance.playermajorzombie.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playermajorzombie.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerMajorZombie> getTable() {
		return _Tables_.instance.playermajorzombie;
	}

}
