package beans.table.lobby;

public class Gentes {
	private Gentes() {
	}

	public static boolean add(Long key, xbean.lobby.Gens value) {
		return _Tables_.instance.gentes.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.Gens value, boolean immediately) {
		return _Tables_.instance.gentes.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.Gens get(Long key) {
		return _Tables_.instance.gentes.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.Gens select(Long key) {
		return _Tables_.instance.gentes.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gentes.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.Gens> getTable() {
		return _Tables_.instance.gentes;
	}

}
