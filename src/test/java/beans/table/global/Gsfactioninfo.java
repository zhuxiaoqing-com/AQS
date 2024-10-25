package beans.table.global;

public class Gsfactioninfo {
	private Gsfactioninfo() {
	}

	public static boolean add(Integer key, xbean.global.GsFactionInfo value) {
		return _Tables_.instance.gsfactioninfo.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.global.GsFactionInfo value, boolean immediately) {
		return _Tables_.instance.gsfactioninfo.insert(key, value, immediately) != null;
	}

	public static xbean.global.GsFactionInfo get(Integer key) {
		return _Tables_.instance.gsfactioninfo.get(key);
	}

	// 只读模式查询
	public static xbean.global.GsFactionInfo select(Integer key) {
		return _Tables_.instance.gsfactioninfo.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.gsfactioninfo.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.global.GsFactionInfo> getTable() {
		return _Tables_.instance.gsfactioninfo;
	}

}
