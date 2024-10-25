package beans.table.lobby;

public class Playernewbiehelp {
	private Playernewbiehelp() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerNewbieHelp value) {
		return _Tables_.instance.playernewbiehelp.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerNewbieHelp value, boolean immediately) {
		return _Tables_.instance.playernewbiehelp.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerNewbieHelp get(Long key) {
		return _Tables_.instance.playernewbiehelp.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerNewbieHelp select(Long key) {
		return _Tables_.instance.playernewbiehelp.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playernewbiehelp.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerNewbieHelp> getTable() {
		return _Tables_.instance.playernewbiehelp;
	}

}
