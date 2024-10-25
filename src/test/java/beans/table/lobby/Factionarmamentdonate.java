package beans.table.lobby;

public class Factionarmamentdonate {
	private Factionarmamentdonate() {
	}

	public static boolean add(Integer key, xbean.lobby.FactionArmamentDonate value) {
		return _Tables_.instance.factionarmamentdonate.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.FactionArmamentDonate value, boolean immediately) {
		return _Tables_.instance.factionarmamentdonate.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.FactionArmamentDonate get(Integer key) {
		return _Tables_.instance.factionarmamentdonate.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.FactionArmamentDonate select(Integer key) {
		return _Tables_.instance.factionarmamentdonate.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.factionarmamentdonate.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.FactionArmamentDonate> getTable() {
		return _Tables_.instance.factionarmamentdonate;
	}

}
