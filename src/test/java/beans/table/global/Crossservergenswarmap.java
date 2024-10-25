package beans.table.global;

public class Crossservergenswarmap {
	private Crossservergenswarmap() {
	}

	public static boolean add(Integer key, xbean.global.CrossServerGensWarMap value) {
		return _Tables_.instance.crossservergenswarmap.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.global.CrossServerGensWarMap value, boolean immediately) {
		return _Tables_.instance.crossservergenswarmap.insert(key, value, immediately) != null;
	}

	public static xbean.global.CrossServerGensWarMap get(Integer key) {
		return _Tables_.instance.crossservergenswarmap.get(key);
	}

	// 只读模式查询
	public static xbean.global.CrossServerGensWarMap select(Integer key) {
		return _Tables_.instance.crossservergenswarmap.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.crossservergenswarmap.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.global.CrossServerGensWarMap> getTable() {
		return _Tables_.instance.crossservergenswarmap;
	}

}
