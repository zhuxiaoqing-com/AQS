package beans.table.global;

public class Playerreportinfo {
	private Playerreportinfo() {
	}

	public static boolean add(Long key, xbean.global.PlayerReportInfo value) {
		return _Tables_.instance.playerreportinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.global.PlayerReportInfo value, boolean immediately) {
		return _Tables_.instance.playerreportinfo.insert(key, value, immediately) != null;
	}

	public static xbean.global.PlayerReportInfo get(Long key) {
		return _Tables_.instance.playerreportinfo.get(key);
	}

	// 只读模式查询
	public static xbean.global.PlayerReportInfo select(Long key) {
		return _Tables_.instance.playerreportinfo.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.playerreportinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.global.PlayerReportInfo> getTable() {
		return _Tables_.instance.playerreportinfo;
	}

}
