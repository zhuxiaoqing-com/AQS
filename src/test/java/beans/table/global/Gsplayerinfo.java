package beans.table.global;

public class Gsplayerinfo {
	private Gsplayerinfo() {
	}

	public static boolean add(Long key, xbean.global.GsPlayerInfo value) {
		return _Tables_.instance.gsplayerinfo.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.global.GsPlayerInfo value, boolean immediately) {
		return _Tables_.instance.gsplayerinfo.insert(key, value, immediately) != null;
	}

	public static xbean.global.GsPlayerInfo get(Long key) {
		return _Tables_.instance.gsplayerinfo.get(key);
	}

	// 只读模式查询
	public static xbean.global.GsPlayerInfo select(Long key) {
		return _Tables_.instance.gsplayerinfo.select(key);
	}

	public static boolean remove(Long key) {
		return _Tables_.instance.gsplayerinfo.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.global.GsPlayerInfo> getTable() {
		return _Tables_.instance.gsplayerinfo;
	}

}
