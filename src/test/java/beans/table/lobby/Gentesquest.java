package beans.table.lobby;

public class Gentesquest {
	private Gentesquest() {
	}

	public static boolean add(Long key, xbean.lobby.GentesQuestInfo value) {
		return _Tables_.instance.gentesquest.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.GentesQuestInfo value, boolean immediately) {
		return _Tables_.instance.gentesquest.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.GentesQuestInfo get(Long key) {
		return _Tables_.instance.gentesquest.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.GentesQuestInfo select(Long key) {
		return _Tables_.instance.gentesquest.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gentesquest.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.GentesQuestInfo> getTable() {
		return _Tables_.instance.gentesquest;
	}

}
