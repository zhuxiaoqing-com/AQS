package beans.table.lobby;

public class Mentorship {
	private Mentorship() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerMentorship value) {
		return _Tables_.instance.mentorship.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerMentorship value, boolean immediately) {
		return _Tables_.instance.mentorship.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerMentorship get(Long key) {
		return _Tables_.instance.mentorship.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerMentorship select(Long key) {
		return _Tables_.instance.mentorship.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.mentorship.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerMentorship> getTable() {
		return _Tables_.instance.mentorship;
	}

}
