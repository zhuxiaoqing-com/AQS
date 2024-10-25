package beans.table.lobby;

public class Serverreservation {
	private Serverreservation() {
	}

	public static boolean add(Integer key, xbean.lobby.ServerReservation value) {
		return _Tables_.instance.serverreservation.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.ServerReservation value, boolean immediately) {
		return _Tables_.instance.serverreservation.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.ServerReservation get(Integer key) {
		return _Tables_.instance.serverreservation.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.ServerReservation select(Integer key) {
		return _Tables_.instance.serverreservation.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.serverreservation.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.ServerReservation> getTable() {
		return _Tables_.instance.serverreservation;
	}

}
