package beans.table.global;

public class Gsrankinfo {
	private Gsrankinfo() {
	}

	public static boolean add(Long key, xbean.global.RankInfo value) {
		return _Tables_.instance.gsrankinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.global.RankInfo value, boolean immediately) {
		return _Tables_.instance.gsrankinfo.insert(key, value, immediately) != null;
	}

	public static xbean.global.RankInfo get(Long key) {
		return _Tables_.instance.gsrankinfo.get(key);
	}

	// 只读模式查询
	public static xbean.global.RankInfo select(Long key) {
		return _Tables_.instance.gsrankinfo.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gsrankinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.global.RankInfo> getTable() {
		return _Tables_.instance.gsrankinfo;
	}

}
