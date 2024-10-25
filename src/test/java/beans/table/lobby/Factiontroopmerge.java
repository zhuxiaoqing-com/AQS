package beans.table.lobby;

public class Factiontroopmerge {
	private Factiontroopmerge() {
	}

	public static boolean add(Integer key, xbean.lobby.FactionTroopMerge value) {
		return _Tables_.instance.factiontroopmerge.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.FactionTroopMerge value, boolean immediately) {
		return _Tables_.instance.factiontroopmerge.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.FactionTroopMerge get(Integer key) {
		return _Tables_.instance.factiontroopmerge.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.FactionTroopMerge select(Integer key) {
		return _Tables_.instance.factiontroopmerge.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.factiontroopmerge.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.FactionTroopMerge> getTable() {
		return _Tables_.instance.factiontroopmerge;
	}

}
