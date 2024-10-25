package beans.table.lobby;

public class Playerreservation {
	private Playerreservation() {
	}

	public static boolean add(Long key, xbean.lobby.PlayerReservation value) {
		return _Tables_.instance.playerreservation.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.PlayerReservation value, boolean immediately) {
		return _Tables_.instance.playerreservation.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.PlayerReservation get(Long key) {
		return _Tables_.instance.playerreservation.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.PlayerReservation select(Long key) {
		return _Tables_.instance.playerreservation.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerreservation.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.PlayerReservation> getTable() {
		return _Tables_.instance.playerreservation;
	}

}
