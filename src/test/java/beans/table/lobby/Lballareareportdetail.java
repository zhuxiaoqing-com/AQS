package beans.table.lobby;

public class Lballareareportdetail {
	private Lballareareportdetail() {
	}

	public static boolean add(Long key, xbean.lobby.LBAllAreaReportDetail value) {
		return _Tables_.instance.lballareareportdetail.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.lobby.LBAllAreaReportDetail value, boolean immediately) {
		return _Tables_.instance.lballareareportdetail.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.LBAllAreaReportDetail get(Long key) {
		return _Tables_.instance.lballareareportdetail.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.LBAllAreaReportDetail select(Long key) {
		return _Tables_.instance.lballareareportdetail.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.lballareareportdetail.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.lobby.LBAllAreaReportDetail> getTable() {
		return _Tables_.instance.lballareareportdetail;
	}

}
