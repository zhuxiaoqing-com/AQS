package beans.table.lobby;

public class Lbcarrierrentinglist {
	private Lbcarrierrentinglist() {
	}

	public static boolean add(Integer key, xbean.lobby.LbCarrierRentingList value) {
		return _Tables_.instance.lbcarrierrentinglist.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.LbCarrierRentingList value, boolean immediately) {
		return _Tables_.instance.lbcarrierrentinglist.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LbCarrierRentingList get(Integer key) {
		return _Tables_.instance.lbcarrierrentinglist.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LbCarrierRentingList select(Integer key) {
		return _Tables_.instance.lbcarrierrentinglist.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.lbcarrierrentinglist.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.LbCarrierRentingList> getTable() {
		return _Tables_.instance.lbcarrierrentinglist;
	}

}
