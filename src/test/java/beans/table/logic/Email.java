package beans.table.logic;

public class Email {
	private Email() {
	}

	public static boolean add(Long key, xbean.logic.PlayerEmails value) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEmails add key:" + key);
		return _Tables_.instance.email.insert(key, value) != null;
	}

	public static boolean add(Long key, xbean.logic.PlayerEmails value, boolean immediately) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key) && immediately == false)
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEmails add immediately  key:" + key);
		return _Tables_.instance.email.insert(key, value, immediately) != null;
	}

	public static xbean.logic.PlayerEmails get(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEmails get key:" + key);
		return _Tables_.instance.email.get(key);
	}

	// 只读模式查询
	public static xbean.logic.PlayerEmails select(Long key) {
		return _Tables_.instance.email.select(key);
	}

	public static boolean remove(Long key) {
		if (!warrior.mdb.Mdb.getInstance().canOperate(key))
			throw new RuntimeException("Invalid Operation xbean.logic.PlayerEmails remove key:" + key);
		return _Tables_.instance.email.delete(key);
	}

	public static warrior.mdb.TTable<Long, xbean.logic.PlayerEmails> getTable() {
		return _Tables_.instance.email;
	}

}
