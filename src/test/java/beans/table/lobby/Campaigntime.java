package beans.table.lobby;

public class Campaigntime {
	private Campaigntime() {
	}

	public static boolean add(Integer key, xbean.lobby.CampaignTime value) {
		return _Tables_.instance.campaigntime.insert(key, value) != null;
	}

	public static boolean add(Integer key, xbean.lobby.CampaignTime value, boolean immediately) {
		return _Tables_.instance.campaigntime.insert(key, value, immediately) != null;
	}

	public static xbean.lobby.CampaignTime get(Integer key) {
		return _Tables_.instance.campaigntime.get(key);
	}

	// 只读模式查询
	public static xbean.lobby.CampaignTime select(Integer key) {
		return _Tables_.instance.campaigntime.select(key);
	}

	public static boolean remove(Integer key) {
		return _Tables_.instance.campaigntime.delete(key);
	}

	public static warrior.mdb.TTable<Integer, xbean.lobby.CampaignTime> getTable() {
		return _Tables_.instance.campaigntime;
	}

}
