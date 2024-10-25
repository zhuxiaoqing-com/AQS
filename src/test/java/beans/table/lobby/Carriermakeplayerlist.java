package beans.table.lobby;

public class Carriermakeplayerlist {
	private Carriermakeplayerlist() {
	}

	public static boolean add(Long key, xbean.lobby.CarrierMakePlayerList value) {
		return _Tables_.instance.carriermakeplayerlist.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.CarrierMakePlayerList value, boolean immediately) {
		return _Tables_.instance.carriermakeplayerlist.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.CarrierMakePlayerList get(Long key) {
		return _Tables_.instance.carriermakeplayerlist.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.CarrierMakePlayerList select(Long key) {
		return _Tables_.instance.carriermakeplayerlist.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.carriermakeplayerlist.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.CarrierMakePlayerList> getTable() {
		return _Tables_.instance.carriermakeplayerlist;
	}

}
