package beans.table.logic;

public class Playergensrecruit {
	private Playergensrecruit() {
	}

	public static boolean add(Long key, xbean.logic.GensRecruitInfo value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.GensRecruitInfo add key:" + key);
		return _Tables_.instance.playergensrecruit.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.GensRecruitInfo value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.GensRecruitInfo add immediately  key:" + key);
		return _Tables_.instance.playergensrecruit.insert(key, value, immediately) != null;
	}

	public static xbean.logic.GensRecruitInfo get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.GensRecruitInfo get key:" + key);
		return _Tables_.instance.playergensrecruit.get(key);
	}

	// 只读模式查询
	public static xbean.logic.GensRecruitInfo select(Long key) {
		return _Tables_.instance.playergensrecruit.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.GensRecruitInfo remove key:" + key);
		return _Tables_.instance.playergensrecruit.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.GensRecruitInfo> getTable() {
		return _Tables_.instance.playergensrecruit;
	}

}
