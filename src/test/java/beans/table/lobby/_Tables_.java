package beans.table.lobby;

import warrior.mdb.util.FastJson;

final class _Tables_ {
	volatile static _Tables_ instance;

	private _Tables_ () {
		instance = this;
	}

	class Genswarmap extends warrior.mdb.TTable<Integer, xbean.lobby.GensWarMapInfo> {
		@Override
		public String getName() {
			return "genswarmap";
		}

		@Override
		protected xbean.lobby.GensWarMapInfo newValue() {
			xbean.lobby.GensWarMapInfo value = new xbean.lobby.GensWarMapInfo();
			return value;
		}

		xbean.lobby.GensWarMapInfo insert(Integer key, xbean.lobby.GensWarMapInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GensWarMapInfo insert(Integer key, xbean.lobby.GensWarMapInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GensWarMapInfo deepCopy(xbean.lobby.GensWarMapInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GensWarMapInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.GensWarMapInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GensWarMapInfo.class);
		}

	};

	Genswarmap genswarmap = new Genswarmap();

	class Gensmember extends warrior.mdb.TTable<Long, xbean.lobby.GensMember> {
		@Override
		public String getName() {
			return "gensmember";
		}

		@Override
		protected xbean.lobby.GensMember newValue() {
			xbean.lobby.GensMember value = new xbean.lobby.GensMember();
			return value;
		}

		xbean.lobby.GensMember insert(Long key, xbean.lobby.GensMember value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GensMember insert(Long key, xbean.lobby.GensMember value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GensMember deepCopy(xbean.lobby.GensMember v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GensMember value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GensMember unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GensMember.class);
		}

	};

	Gensmember gensmember = new Gensmember();

	class Factionstore extends warrior.mdb.TTable<Integer, xbean.lobby.FactionStore> {
		@Override
		public String getName() {
			return "factionstore";
		}

		@Override
		protected xbean.lobby.FactionStore newValue() {
			xbean.lobby.FactionStore value = new xbean.lobby.FactionStore();
			return value;
		}

		xbean.lobby.FactionStore insert(Integer key, xbean.lobby.FactionStore value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.FactionStore insert(Integer key, xbean.lobby.FactionStore value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.FactionStore deepCopy(xbean.lobby.FactionStore v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.FactionStore value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.FactionStore unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.FactionStore.class);
		}

	};

	Factionstore factionstore = new Factionstore();

	class Alliance extends warrior.mdb.TTable<Long, xbean.lobby.Alliance> {
		@Override
		public String getName() {
			return "alliance";
		}

		@Override
		protected xbean.lobby.Alliance newValue() {
			xbean.lobby.Alliance value = new xbean.lobby.Alliance();
			return value;
		}

		xbean.lobby.Alliance insert(Long key, xbean.lobby.Alliance value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.Alliance insert(Long key, xbean.lobby.Alliance value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.Alliance deepCopy(xbean.lobby.Alliance v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.Alliance value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.Alliance unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.Alliance.class);
		}

	};

	Alliance alliance = new Alliance();

	class Campaigntime extends warrior.mdb.TTable<Integer, xbean.lobby.CampaignTime> {
		@Override
		public String getName() {
			return "campaigntime";
		}

		@Override
		protected xbean.lobby.CampaignTime newValue() {
			xbean.lobby.CampaignTime value = new xbean.lobby.CampaignTime();
			return value;
		}

		xbean.lobby.CampaignTime insert(Integer key, xbean.lobby.CampaignTime value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.CampaignTime insert(Integer key, xbean.lobby.CampaignTime value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.CampaignTime deepCopy(xbean.lobby.CampaignTime v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.CampaignTime value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.CampaignTime unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.CampaignTime.class);
		}

	};

	Campaigntime campaigntime = new Campaigntime();

	class Offlinequest extends warrior.mdb.TTable<Long, xbean.lobby.OfflineQuest> {
		@Override
		public String getName() {
			return "offlinequest";
		}

		@Override
		protected xbean.lobby.OfflineQuest newValue() {
			xbean.lobby.OfflineQuest value = new xbean.lobby.OfflineQuest();
			return value;
		}

		xbean.lobby.OfflineQuest insert(Long key, xbean.lobby.OfflineQuest value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.OfflineQuest insert(Long key, xbean.lobby.OfflineQuest value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.OfflineQuest deepCopy(xbean.lobby.OfflineQuest v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.OfflineQuest value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.OfflineQuest unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.OfflineQuest.class);
		}

	};

	Offlinequest offlinequest = new Offlinequest();

	class Lblogicalserverinfo extends warrior.mdb.TTable<Integer, xbean.lobby.LbLogicalServerInfo> {
		@Override
		public String getName() {
			return "lblogicalserverinfo";
		}

		@Override
		protected xbean.lobby.LbLogicalServerInfo newValue() {
			xbean.lobby.LbLogicalServerInfo value = new xbean.lobby.LbLogicalServerInfo();
			return value;
		}

		xbean.lobby.LbLogicalServerInfo insert(Integer key, xbean.lobby.LbLogicalServerInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LbLogicalServerInfo insert(Integer key, xbean.lobby.LbLogicalServerInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LbLogicalServerInfo deepCopy(xbean.lobby.LbLogicalServerInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LbLogicalServerInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.LbLogicalServerInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LbLogicalServerInfo.class);
		}

	};

	Lblogicalserverinfo lblogicalserverinfo = new Lblogicalserverinfo();

	class Alliancename extends warrior.mdb.TTable<String, Long> {
		@Override
		public String getName() {
			return "alliancename";
		}

		@Override
		protected Long newValue() {
			Long value = 0L;
			return value;
		}

		Long insert(String key, Long value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		Long insert(String key, Long value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected Long deepCopy(Long v){
            throw new RuntimeException("need impl");
		}

		boolean delete(String key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(String key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(Long value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected String unmarshalKey(String os) {
			return FastJson.parseObject(os, String.class);
		}

		@Override
		protected Long unmarshalValue(String os) {
			return FastJson.parseObject(os, Long.class);
		}

	};

	Alliancename alliancename = new Alliancename();

	class Lbsdkinfo extends warrior.mdb.TTable<String, xbean.lobby.ServerPlayerInfo> {
		@Override
		public String getName() {
			return "lbsdkinfo";
		}

		@Override
		protected xbean.lobby.ServerPlayerInfo newValue() {
			xbean.lobby.ServerPlayerInfo value = new xbean.lobby.ServerPlayerInfo();
			return value;
		}

		xbean.lobby.ServerPlayerInfo insert(String key, xbean.lobby.ServerPlayerInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.ServerPlayerInfo insert(String key, xbean.lobby.ServerPlayerInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.ServerPlayerInfo deepCopy(xbean.lobby.ServerPlayerInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(String key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(String key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.ServerPlayerInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected String unmarshalKey(String os) {
			return FastJson.parseObject(os, String.class);
		}

		@Override
		protected xbean.lobby.ServerPlayerInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.ServerPlayerInfo.class);
		}

	};

	Lbsdkinfo lbsdkinfo = new Lbsdkinfo();

	class Actlogisticsscore extends warrior.mdb.TTable<Long, xbean.lobby.ActLogisticsScore> {
		@Override
		public String getName() {
			return "actlogisticsscore";
		}

		@Override
		protected xbean.lobby.ActLogisticsScore newValue() {
			xbean.lobby.ActLogisticsScore value = new xbean.lobby.ActLogisticsScore();
			return value;
		}

		xbean.lobby.ActLogisticsScore insert(Long key, xbean.lobby.ActLogisticsScore value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.ActLogisticsScore insert(Long key, xbean.lobby.ActLogisticsScore value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.ActLogisticsScore deepCopy(xbean.lobby.ActLogisticsScore v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.ActLogisticsScore value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.ActLogisticsScore unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.ActLogisticsScore.class);
		}

	};

	Actlogisticsscore actlogisticsscore = new Actlogisticsscore();

	class Troopcarrier extends warrior.mdb.TTable<Long, xbean.lobby.TroopCarrier> {
		@Override
		public String getName() {
			return "troopcarrier";
		}

		@Override
		protected xbean.lobby.TroopCarrier newValue() {
			xbean.lobby.TroopCarrier value = new xbean.lobby.TroopCarrier();
			return value;
		}

		xbean.lobby.TroopCarrier insert(Long key, xbean.lobby.TroopCarrier value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.TroopCarrier insert(Long key, xbean.lobby.TroopCarrier value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.TroopCarrier deepCopy(xbean.lobby.TroopCarrier v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.TroopCarrier value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.TroopCarrier unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.TroopCarrier.class);
		}

	};

	Troopcarrier troopcarrier = new Troopcarrier();

	class Playeractrank extends warrior.mdb.TTable<Long, xbean.lobby.PlayerActRank> {
		@Override
		public String getName() {
			return "playeractrank";
		}

		@Override
		protected xbean.lobby.PlayerActRank newValue() {
			xbean.lobby.PlayerActRank value = new xbean.lobby.PlayerActRank();
			return value;
		}

		xbean.lobby.PlayerActRank insert(Long key, xbean.lobby.PlayerActRank value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerActRank insert(Long key, xbean.lobby.PlayerActRank value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerActRank deepCopy(xbean.lobby.PlayerActRank v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerActRank value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerActRank unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerActRank.class);
		}

	};

	Playeractrank playeractrank = new Playeractrank();

	class Userlgbind extends warrior.mdb.TTable<String, xbean.lobby.UserLGBind> {
		@Override
		public String getName() {
			return "userlgbind";
		}

		@Override
		protected xbean.lobby.UserLGBind newValue() {
			xbean.lobby.UserLGBind value = new xbean.lobby.UserLGBind();
			return value;
		}

		xbean.lobby.UserLGBind insert(String key, xbean.lobby.UserLGBind value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.UserLGBind insert(String key, xbean.lobby.UserLGBind value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.UserLGBind deepCopy(xbean.lobby.UserLGBind v){
            throw new RuntimeException("need impl");
		}

		boolean delete(String key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(String key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.UserLGBind value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected String unmarshalKey(String os) {
			return FastJson.parseObject(os, String.class);
		}

		@Override
		protected xbean.lobby.UserLGBind unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.UserLGBind.class);
		}

	};

	Userlgbind userlgbind = new Userlgbind();

	class Rankinfo extends warrior.mdb.TTable<Long, xbean.lobby.RankInfo> {
		@Override
		public String getName() {
			return "rankinfo";
		}

		@Override
		protected xbean.lobby.RankInfo newValue() {
			xbean.lobby.RankInfo value = new xbean.lobby.RankInfo();
			return value;
		}

		xbean.lobby.RankInfo insert(Long key, xbean.lobby.RankInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.RankInfo insert(Long key, xbean.lobby.RankInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.RankInfo deepCopy(xbean.lobby.RankInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.RankInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.RankInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.RankInfo.class);
		}

	};

	Rankinfo rankinfo = new Rankinfo();

	class Playerorder extends warrior.mdb.TTable<Long, xbean.lobby.PlayerOrder> {
		@Override
		public String getName() {
			return "playerorder";
		}

		@Override
		protected xbean.lobby.PlayerOrder newValue() {
			xbean.lobby.PlayerOrder value = new xbean.lobby.PlayerOrder();
			return value;
		}

		xbean.lobby.PlayerOrder insert(Long key, xbean.lobby.PlayerOrder value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerOrder insert(Long key, xbean.lobby.PlayerOrder value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerOrder deepCopy(xbean.lobby.PlayerOrder v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerOrder value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerOrder unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerOrder.class);
		}

	};

	Playerorder playerorder = new Playerorder();

	class Gentestechs extends warrior.mdb.TTable<Long, xbean.lobby.GentesTech> {
		@Override
		public String getName() {
			return "gentestechs";
		}

		@Override
		protected xbean.lobby.GentesTech newValue() {
			xbean.lobby.GentesTech value = new xbean.lobby.GentesTech();
			return value;
		}

		xbean.lobby.GentesTech insert(Long key, xbean.lobby.GentesTech value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GentesTech insert(Long key, xbean.lobby.GentesTech value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GentesTech deepCopy(xbean.lobby.GentesTech v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GentesTech value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GentesTech unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GentesTech.class);
		}

	};

	Gentestechs gentestechs = new Gentestechs();

	class Factiontroopmerge extends warrior.mdb.TTable<Integer, xbean.lobby.FactionTroopMerge> {
		@Override
		public String getName() {
			return "factiontroopmerge";
		}

		@Override
		protected xbean.lobby.FactionTroopMerge newValue() {
			xbean.lobby.FactionTroopMerge value = new xbean.lobby.FactionTroopMerge();
			return value;
		}

		xbean.lobby.FactionTroopMerge insert(Integer key, xbean.lobby.FactionTroopMerge value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.FactionTroopMerge insert(Integer key, xbean.lobby.FactionTroopMerge value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.FactionTroopMerge deepCopy(xbean.lobby.FactionTroopMerge v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.FactionTroopMerge value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.FactionTroopMerge unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.FactionTroopMerge.class);
		}

	};

	Factiontroopmerge factiontroopmerge = new Factiontroopmerge();

	class Lbplayerpraise extends warrior.mdb.TTable<Long, xbean.lobby.LbPlayerPraise> {
		@Override
		public String getName() {
			return "lbplayerpraise";
		}

		@Override
		protected xbean.lobby.LbPlayerPraise newValue() {
			xbean.lobby.LbPlayerPraise value = new xbean.lobby.LbPlayerPraise();
			return value;
		}

		xbean.lobby.LbPlayerPraise insert(Long key, xbean.lobby.LbPlayerPraise value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LbPlayerPraise insert(Long key, xbean.lobby.LbPlayerPraise value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LbPlayerPraise deepCopy(xbean.lobby.LbPlayerPraise v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LbPlayerPraise value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.LbPlayerPraise unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LbPlayerPraise.class);
		}

	};

	Lbplayerpraise lbplayerpraise = new Lbplayerpraise();

	class Troopimpeach extends warrior.mdb.TTable<Long, xbean.lobby.TroopImpeach> {
		@Override
		public String getName() {
			return "troopimpeach";
		}

		@Override
		protected xbean.lobby.TroopImpeach newValue() {
			xbean.lobby.TroopImpeach value = new xbean.lobby.TroopImpeach();
			return value;
		}

		xbean.lobby.TroopImpeach insert(Long key, xbean.lobby.TroopImpeach value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.TroopImpeach insert(Long key, xbean.lobby.TroopImpeach value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.TroopImpeach deepCopy(xbean.lobby.TroopImpeach v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.TroopImpeach value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.TroopImpeach unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.TroopImpeach.class);
		}

	};

	Troopimpeach troopimpeach = new Troopimpeach();

	class Troopformids extends warrior.mdb.TTable<Integer, xbean.lobby.TroopFormIds> {
		@Override
		public String getName() {
			return "troopformids";
		}

		@Override
		protected xbean.lobby.TroopFormIds newValue() {
			xbean.lobby.TroopFormIds value = new xbean.lobby.TroopFormIds();
			return value;
		}

		xbean.lobby.TroopFormIds insert(Integer key, xbean.lobby.TroopFormIds value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.TroopFormIds insert(Integer key, xbean.lobby.TroopFormIds value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.TroopFormIds deepCopy(xbean.lobby.TroopFormIds v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.TroopFormIds value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.TroopFormIds unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.TroopFormIds.class);
		}

	};

	Troopformids troopformids = new Troopformids();

	class Lgplayerinfo extends warrior.mdb.TTable<Long, xbean.lobby.LgPlayerInfo> {
		@Override
		public String getName() {
			return "lgplayerinfo";
		}

		@Override
		protected xbean.lobby.LgPlayerInfo newValue() {
			xbean.lobby.LgPlayerInfo value = new xbean.lobby.LgPlayerInfo();
			return value;
		}

		xbean.lobby.LgPlayerInfo insert(Long key, xbean.lobby.LgPlayerInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LgPlayerInfo insert(Long key, xbean.lobby.LgPlayerInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LgPlayerInfo deepCopy(xbean.lobby.LgPlayerInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LgPlayerInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.LgPlayerInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LgPlayerInfo.class);
		}

	};

	Lgplayerinfo lgplayerinfo = new Lgplayerinfo();

	class Gentesids extends warrior.mdb.TTable<Integer, xbean.lobby.GentesIds> {
		@Override
		public String getName() {
			return "gentesids";
		}

		@Override
		protected xbean.lobby.GentesIds newValue() {
			xbean.lobby.GentesIds value = new xbean.lobby.GentesIds();
			return value;
		}

		xbean.lobby.GentesIds insert(Integer key, xbean.lobby.GentesIds value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GentesIds insert(Integer key, xbean.lobby.GentesIds value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GentesIds deepCopy(xbean.lobby.GentesIds v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GentesIds value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.GentesIds unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GentesIds.class);
		}

	};

	Gentesids gentesids = new Gentesids();

	class Serveremail extends warrior.mdb.TTable<Integer, xbean.lobby.ServerEmail> {
		@Override
		public String getName() {
			return "serveremail";
		}

		@Override
		protected xbean.lobby.ServerEmail newValue() {
			xbean.lobby.ServerEmail value = new xbean.lobby.ServerEmail();
			return value;
		}

		xbean.lobby.ServerEmail insert(Integer key, xbean.lobby.ServerEmail value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.ServerEmail insert(Integer key, xbean.lobby.ServerEmail value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.ServerEmail deepCopy(xbean.lobby.ServerEmail v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.ServerEmail value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.ServerEmail unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.ServerEmail.class);
		}

	};

	Serveremail serveremail = new Serveremail();

	class Carriermakeplayerlist extends warrior.mdb.TTable<Long, xbean.lobby.CarrierMakePlayerList> {
		@Override
		public String getName() {
			return "carriermakeplayerlist";
		}

		@Override
		protected xbean.lobby.CarrierMakePlayerList newValue() {
			xbean.lobby.CarrierMakePlayerList value = new xbean.lobby.CarrierMakePlayerList();
			return value;
		}

		xbean.lobby.CarrierMakePlayerList insert(Long key, xbean.lobby.CarrierMakePlayerList value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.CarrierMakePlayerList insert(Long key, xbean.lobby.CarrierMakePlayerList value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.CarrierMakePlayerList deepCopy(xbean.lobby.CarrierMakePlayerList v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.CarrierMakePlayerList value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.CarrierMakePlayerList unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.CarrierMakePlayerList.class);
		}

	};

	Carriermakeplayerlist carriermakeplayerlist = new Carriermakeplayerlist();

	class Gentescompose extends warrior.mdb.TTable<Long, xbean.lobby.GentesCompose> {
		@Override
		public String getName() {
			return "gentescompose";
		}

		@Override
		protected xbean.lobby.GentesCompose newValue() {
			xbean.lobby.GentesCompose value = new xbean.lobby.GentesCompose();
			return value;
		}

		xbean.lobby.GentesCompose insert(Long key, xbean.lobby.GentesCompose value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GentesCompose insert(Long key, xbean.lobby.GentesCompose value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GentesCompose deepCopy(xbean.lobby.GentesCompose v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GentesCompose value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GentesCompose unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GentesCompose.class);
		}

	};

	Gentescompose gentescompose = new Gentescompose();

	class Playerequipware extends warrior.mdb.TTable<Long, xbean.lobby.PlayerEquipWare> {
		@Override
		public String getName() {
			return "playerequipware";
		}

		@Override
		protected xbean.lobby.PlayerEquipWare newValue() {
			xbean.lobby.PlayerEquipWare value = new xbean.lobby.PlayerEquipWare();
			return value;
		}

		xbean.lobby.PlayerEquipWare insert(Long key, xbean.lobby.PlayerEquipWare value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerEquipWare insert(Long key, xbean.lobby.PlayerEquipWare value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerEquipWare deepCopy(xbean.lobby.PlayerEquipWare v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerEquipWare value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerEquipWare unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerEquipWare.class);
		}

	};

	Playerequipware playerequipware = new Playerequipware();

	class Lbbattleinfo extends warrior.mdb.TTable<Long, xbean.lobby.LBBattleInfo> {
		@Override
		public String getName() {
			return "lbbattleinfo";
		}

		@Override
		protected xbean.lobby.LBBattleInfo newValue() {
			xbean.lobby.LBBattleInfo value = new xbean.lobby.LBBattleInfo();
			return value;
		}

		xbean.lobby.LBBattleInfo insert(Long key, xbean.lobby.LBBattleInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LBBattleInfo insert(Long key, xbean.lobby.LBBattleInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LBBattleInfo deepCopy(xbean.lobby.LBBattleInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LBBattleInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.LBBattleInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LBBattleInfo.class);
		}

	};

	Lbbattleinfo lbbattleinfo = new Lbbattleinfo();

	class Lbactivitymap extends warrior.mdb.TTable<Long, xbean.lobby.LBActivityMap> {
		@Override
		public String getName() {
			return "lbactivitymap";
		}

		@Override
		protected xbean.lobby.LBActivityMap newValue() {
			xbean.lobby.LBActivityMap value = new xbean.lobby.LBActivityMap();
			return value;
		}

		xbean.lobby.LBActivityMap insert(Long key, xbean.lobby.LBActivityMap value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LBActivityMap insert(Long key, xbean.lobby.LBActivityMap value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LBActivityMap deepCopy(xbean.lobby.LBActivityMap v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LBActivityMap value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.LBActivityMap unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LBActivityMap.class);
		}

	};

	Lbactivitymap lbactivitymap = new Lbactivitymap();

	class Playerclassifylog extends warrior.mdb.TTable<Long, xbean.lobby.PlayerClassifyLog> {
		@Override
		public String getName() {
			return "playerclassifylog";
		}

		@Override
		protected xbean.lobby.PlayerClassifyLog newValue() {
			xbean.lobby.PlayerClassifyLog value = new xbean.lobby.PlayerClassifyLog();
			return value;
		}

		xbean.lobby.PlayerClassifyLog insert(Long key, xbean.lobby.PlayerClassifyLog value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerClassifyLog insert(Long key, xbean.lobby.PlayerClassifyLog value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerClassifyLog deepCopy(xbean.lobby.PlayerClassifyLog v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerClassifyLog value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerClassifyLog unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerClassifyLog.class);
		}

	};

	Playerclassifylog playerclassifylog = new Playerclassifylog();

	class Lbplayerfaction extends warrior.mdb.TTable<Long, xbean.lobby.LbPlayerFaction> {
		@Override
		public String getName() {
			return "lbplayerfaction";
		}

		@Override
		protected xbean.lobby.LbPlayerFaction newValue() {
			xbean.lobby.LbPlayerFaction value = new xbean.lobby.LbPlayerFaction();
			return value;
		}

		xbean.lobby.LbPlayerFaction insert(Long key, xbean.lobby.LbPlayerFaction value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LbPlayerFaction insert(Long key, xbean.lobby.LbPlayerFaction value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LbPlayerFaction deepCopy(xbean.lobby.LbPlayerFaction v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LbPlayerFaction value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.LbPlayerFaction unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LbPlayerFaction.class);
		}

	};

	Lbplayerfaction lbplayerfaction = new Lbplayerfaction();

	class Lblimitpvptuid extends warrior.mdb.TTable<Integer, xbean.lobby.LBLimitPvPUid> {
		@Override
		public String getName() {
			return "lblimitpvptuid";
		}

		@Override
		protected xbean.lobby.LBLimitPvPUid newValue() {
			xbean.lobby.LBLimitPvPUid value = new xbean.lobby.LBLimitPvPUid();
			return value;
		}

		xbean.lobby.LBLimitPvPUid insert(Integer key, xbean.lobby.LBLimitPvPUid value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LBLimitPvPUid insert(Integer key, xbean.lobby.LBLimitPvPUid value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LBLimitPvPUid deepCopy(xbean.lobby.LBLimitPvPUid v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LBLimitPvPUid value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.LBLimitPvPUid unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LBLimitPvPUid.class);
		}

	};

	Lblimitpvptuid lblimitpvptuid = new Lblimitpvptuid();

	class Factionitemmakerank extends warrior.mdb.TTable<Long, xbean.lobby.FactionItemMakeRank> {
		@Override
		public String getName() {
			return "factionitemmakerank";
		}

		@Override
		protected xbean.lobby.FactionItemMakeRank newValue() {
			xbean.lobby.FactionItemMakeRank value = new xbean.lobby.FactionItemMakeRank();
			return value;
		}

		xbean.lobby.FactionItemMakeRank insert(Long key, xbean.lobby.FactionItemMakeRank value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.FactionItemMakeRank insert(Long key, xbean.lobby.FactionItemMakeRank value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.FactionItemMakeRank deepCopy(xbean.lobby.FactionItemMakeRank v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.FactionItemMakeRank value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.FactionItemMakeRank unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.FactionItemMakeRank.class);
		}

	};

	Factionitemmakerank factionitemmakerank = new Factionitemmakerank();

	class Factionwarendtime extends warrior.mdb.TTable<Long, Long> {
		@Override
		public String getName() {
			return "factionwarendtime";
		}

		@Override
		protected Long newValue() {
			Long value = 0L;
			return value;
		}

		Long insert(Long key, Long value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		Long insert(Long key, Long value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected Long deepCopy(Long v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(Long value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected Long unmarshalValue(String os) {
			return FastJson.parseObject(os, Long.class);
		}

	};

	Factionwarendtime factionwarendtime = new Factionwarendtime();

	class Gensrecord extends warrior.mdb.TTable<Long, xbean.lobby.GensRecord> {
		@Override
		public String getName() {
			return "gensrecord";
		}

		@Override
		protected xbean.lobby.GensRecord newValue() {
			xbean.lobby.GensRecord value = new xbean.lobby.GensRecord();
			return value;
		}

		xbean.lobby.GensRecord insert(Long key, xbean.lobby.GensRecord value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GensRecord insert(Long key, xbean.lobby.GensRecord value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GensRecord deepCopy(xbean.lobby.GensRecord v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GensRecord value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GensRecord unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GensRecord.class);
		}

	};

	Gensrecord gensrecord = new Gensrecord();

	class Offlineemail extends warrior.mdb.TTable<Long, xbean.lobby.OfflinePlayerEmails> {
		@Override
		public String getName() {
			return "offlineemail";
		}

		@Override
		protected xbean.lobby.OfflinePlayerEmails newValue() {
			xbean.lobby.OfflinePlayerEmails value = new xbean.lobby.OfflinePlayerEmails();
			return value;
		}

		xbean.lobby.OfflinePlayerEmails insert(Long key, xbean.lobby.OfflinePlayerEmails value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.OfflinePlayerEmails insert(Long key, xbean.lobby.OfflinePlayerEmails value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.OfflinePlayerEmails deepCopy(xbean.lobby.OfflinePlayerEmails v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.OfflinePlayerEmails value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.OfflinePlayerEmails unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.OfflinePlayerEmails.class);
		}

	};

	Offlineemail offlineemail = new Offlineemail();

	class Lblimitchatuid extends warrior.mdb.TTable<Integer, xbean.lobby.LBLimitChatUid> {
		@Override
		public String getName() {
			return "lblimitchatuid";
		}

		@Override
		protected xbean.lobby.LBLimitChatUid newValue() {
			xbean.lobby.LBLimitChatUid value = new xbean.lobby.LBLimitChatUid();
			return value;
		}

		xbean.lobby.LBLimitChatUid insert(Integer key, xbean.lobby.LBLimitChatUid value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LBLimitChatUid insert(Integer key, xbean.lobby.LBLimitChatUid value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LBLimitChatUid deepCopy(xbean.lobby.LBLimitChatUid v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LBLimitChatUid value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.LBLimitChatUid unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LBLimitChatUid.class);
		}

	};

	Lblimitchatuid lblimitchatuid = new Lblimitchatuid();

	class Gensmake extends warrior.mdb.TTable<Long, xbean.lobby.GensMake> {
		@Override
		public String getName() {
			return "gensmake";
		}

		@Override
		protected xbean.lobby.GensMake newValue() {
			xbean.lobby.GensMake value = new xbean.lobby.GensMake();
			return value;
		}

		xbean.lobby.GensMake insert(Long key, xbean.lobby.GensMake value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GensMake insert(Long key, xbean.lobby.GensMake value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GensMake deepCopy(xbean.lobby.GensMake v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GensMake value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GensMake unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GensMake.class);
		}

	};

	Gensmake gensmake = new Gensmake();

	class Playerresbase extends warrior.mdb.TTable<Long, xbean.lobby.PlayerResBase> {
		@Override
		public String getName() {
			return "playerresbase";
		}

		@Override
		protected xbean.lobby.PlayerResBase newValue() {
			xbean.lobby.PlayerResBase value = new xbean.lobby.PlayerResBase();
			return value;
		}

		xbean.lobby.PlayerResBase insert(Long key, xbean.lobby.PlayerResBase value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerResBase insert(Long key, xbean.lobby.PlayerResBase value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerResBase deepCopy(xbean.lobby.PlayerResBase v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerResBase value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerResBase unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerResBase.class);
		}

	};

	Playerresbase playerresbase = new Playerresbase();

	class Playercontestinfo extends warrior.mdb.TTable<Long, xbean.lobby.PlayerContestInfo> {
		@Override
		public String getName() {
			return "playercontestinfo";
		}

		@Override
		protected xbean.lobby.PlayerContestInfo newValue() {
			xbean.lobby.PlayerContestInfo value = new xbean.lobby.PlayerContestInfo();
			return value;
		}

		xbean.lobby.PlayerContestInfo insert(Long key, xbean.lobby.PlayerContestInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerContestInfo insert(Long key, xbean.lobby.PlayerContestInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerContestInfo deepCopy(xbean.lobby.PlayerContestInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerContestInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerContestInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerContestInfo.class);
		}

	};

	Playercontestinfo playercontestinfo = new Playercontestinfo();

	class Playerfactionresbase extends warrior.mdb.TTable<Long, xbean.lobby.PlayerFactionResBase> {
		@Override
		public String getName() {
			return "playerfactionresbase";
		}

		@Override
		protected xbean.lobby.PlayerFactionResBase newValue() {
			xbean.lobby.PlayerFactionResBase value = new xbean.lobby.PlayerFactionResBase();
			return value;
		}

		xbean.lobby.PlayerFactionResBase insert(Long key, xbean.lobby.PlayerFactionResBase value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerFactionResBase insert(Long key, xbean.lobby.PlayerFactionResBase value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerFactionResBase deepCopy(xbean.lobby.PlayerFactionResBase v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerFactionResBase value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerFactionResBase unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerFactionResBase.class);
		}

	};

	Playerfactionresbase playerfactionresbase = new Playerfactionresbase();

	class Gentesquest extends warrior.mdb.TTable<Long, xbean.lobby.GentesQuestInfo> {
		@Override
		public String getName() {
			return "gentesquest";
		}

		@Override
		protected xbean.lobby.GentesQuestInfo newValue() {
			xbean.lobby.GentesQuestInfo value = new xbean.lobby.GentesQuestInfo();
			return value;
		}

		xbean.lobby.GentesQuestInfo insert(Long key, xbean.lobby.GentesQuestInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GentesQuestInfo insert(Long key, xbean.lobby.GentesQuestInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GentesQuestInfo deepCopy(xbean.lobby.GentesQuestInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GentesQuestInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GentesQuestInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GentesQuestInfo.class);
		}

	};

	Gentesquest gentesquest = new Gentesquest();

	class Playerreservation extends warrior.mdb.TTable<Long, xbean.lobby.PlayerReservation> {
		@Override
		public String getName() {
			return "playerreservation";
		}

		@Override
		protected xbean.lobby.PlayerReservation newValue() {
			xbean.lobby.PlayerReservation value = new xbean.lobby.PlayerReservation();
			return value;
		}

		xbean.lobby.PlayerReservation insert(Long key, xbean.lobby.PlayerReservation value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerReservation insert(Long key, xbean.lobby.PlayerReservation value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerReservation deepCopy(xbean.lobby.PlayerReservation v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerReservation value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerReservation unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerReservation.class);
		}

	};

	Playerreservation playerreservation = new Playerreservation();

	class Offlinecarrier extends warrior.mdb.TTable<Long, xbean.lobby.OfflineCarrier> {
		@Override
		public String getName() {
			return "offlinecarrier";
		}

		@Override
		protected xbean.lobby.OfflineCarrier newValue() {
			xbean.lobby.OfflineCarrier value = new xbean.lobby.OfflineCarrier();
			return value;
		}

		xbean.lobby.OfflineCarrier insert(Long key, xbean.lobby.OfflineCarrier value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.OfflineCarrier insert(Long key, xbean.lobby.OfflineCarrier value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.OfflineCarrier deepCopy(xbean.lobby.OfflineCarrier v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.OfflineCarrier value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.OfflineCarrier unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.OfflineCarrier.class);
		}

	};

	Offlinecarrier offlinecarrier = new Offlinecarrier();

	class Mentorship extends warrior.mdb.TTable<Long, xbean.lobby.PlayerMentorship> {
		@Override
		public String getName() {
			return "mentorship";
		}

		@Override
		protected xbean.lobby.PlayerMentorship newValue() {
			xbean.lobby.PlayerMentorship value = new xbean.lobby.PlayerMentorship();
			return value;
		}

		xbean.lobby.PlayerMentorship insert(Long key, xbean.lobby.PlayerMentorship value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerMentorship insert(Long key, xbean.lobby.PlayerMentorship value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerMentorship deepCopy(xbean.lobby.PlayerMentorship v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerMentorship value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerMentorship unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerMentorship.class);
		}

	};

	Mentorship mentorship = new Mentorship();

	class Lbgenswildinfo extends warrior.mdb.TTable<Integer, xbean.lobby.LBGensWildInfo> {
		@Override
		public String getName() {
			return "lbgenswildinfo";
		}

		@Override
		protected xbean.lobby.LBGensWildInfo newValue() {
			xbean.lobby.LBGensWildInfo value = new xbean.lobby.LBGensWildInfo();
			return value;
		}

		xbean.lobby.LBGensWildInfo insert(Integer key, xbean.lobby.LBGensWildInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LBGensWildInfo insert(Integer key, xbean.lobby.LBGensWildInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LBGensWildInfo deepCopy(xbean.lobby.LBGensWildInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LBGensWildInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.LBGensWildInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LBGensWildInfo.class);
		}

	};

	Lbgenswildinfo lbgenswildinfo = new Lbgenswildinfo();

	class Serverreservation extends warrior.mdb.TTable<Integer, xbean.lobby.ServerReservation> {
		@Override
		public String getName() {
			return "serverreservation";
		}

		@Override
		protected xbean.lobby.ServerReservation newValue() {
			xbean.lobby.ServerReservation value = new xbean.lobby.ServerReservation();
			return value;
		}

		xbean.lobby.ServerReservation insert(Integer key, xbean.lobby.ServerReservation value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.ServerReservation insert(Integer key, xbean.lobby.ServerReservation value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.ServerReservation deepCopy(xbean.lobby.ServerReservation v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.ServerReservation value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.ServerReservation unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.ServerReservation.class);
		}

	};

	Serverreservation serverreservation = new Serverreservation();

	class Actcityfight extends warrior.mdb.TTable<Long, xbean.lobby.ActCityFight> {
		@Override
		public String getName() {
			return "actcityfight";
		}

		@Override
		protected xbean.lobby.ActCityFight newValue() {
			xbean.lobby.ActCityFight value = new xbean.lobby.ActCityFight();
			return value;
		}

		xbean.lobby.ActCityFight insert(Long key, xbean.lobby.ActCityFight value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.ActCityFight insert(Long key, xbean.lobby.ActCityFight value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.ActCityFight deepCopy(xbean.lobby.ActCityFight v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.ActCityFight value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.ActCityFight unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.ActCityFight.class);
		}

	};

	Actcityfight actcityfight = new Actcityfight();

	class Troopimpeachids extends warrior.mdb.TTable<Integer, xbean.lobby.TroopImpeachIds> {
		@Override
		public String getName() {
			return "troopimpeachids";
		}

		@Override
		protected xbean.lobby.TroopImpeachIds newValue() {
			xbean.lobby.TroopImpeachIds value = new xbean.lobby.TroopImpeachIds();
			return value;
		}

		xbean.lobby.TroopImpeachIds insert(Integer key, xbean.lobby.TroopImpeachIds value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.TroopImpeachIds insert(Integer key, xbean.lobby.TroopImpeachIds value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.TroopImpeachIds deepCopy(xbean.lobby.TroopImpeachIds v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.TroopImpeachIds value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.TroopImpeachIds unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.TroopImpeachIds.class);
		}

	};

	Troopimpeachids troopimpeachids = new Troopimpeachids();

	class Offachieves extends warrior.mdb.TTable<Long, xbean.lobby.OfflinePlayerAchieve> {
		@Override
		public String getName() {
			return "offachieves";
		}

		@Override
		protected xbean.lobby.OfflinePlayerAchieve newValue() {
			xbean.lobby.OfflinePlayerAchieve value = new xbean.lobby.OfflinePlayerAchieve();
			return value;
		}

		xbean.lobby.OfflinePlayerAchieve insert(Long key, xbean.lobby.OfflinePlayerAchieve value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.OfflinePlayerAchieve insert(Long key, xbean.lobby.OfflinePlayerAchieve value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.OfflinePlayerAchieve deepCopy(xbean.lobby.OfflinePlayerAchieve v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.OfflinePlayerAchieve value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.OfflinePlayerAchieve unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.OfflinePlayerAchieve.class);
		}

	};

	Offachieves offachieves = new Offachieves();

	class Troop extends warrior.mdb.TTable<Long, xbean.lobby.Troop> {
		@Override
		public String getName() {
			return "troop";
		}

		@Override
		protected xbean.lobby.Troop newValue() {
			xbean.lobby.Troop value = new xbean.lobby.Troop();
			return value;
		}

		xbean.lobby.Troop insert(Long key, xbean.lobby.Troop value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.Troop insert(Long key, xbean.lobby.Troop value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.Troop deepCopy(xbean.lobby.Troop v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.Troop value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.Troop unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.Troop.class);
		}

	};

	Troop troop = new Troop();

	class Gensdepot extends warrior.mdb.TTable<Long, xbean.lobby.GensDepot> {
		@Override
		public String getName() {
			return "gensdepot";
		}

		@Override
		protected xbean.lobby.GensDepot newValue() {
			xbean.lobby.GensDepot value = new xbean.lobby.GensDepot();
			return value;
		}

		xbean.lobby.GensDepot insert(Long key, xbean.lobby.GensDepot value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GensDepot insert(Long key, xbean.lobby.GensDepot value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GensDepot deepCopy(xbean.lobby.GensDepot v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GensDepot value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GensDepot unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GensDepot.class);
		}

	};

	Gensdepot gensdepot = new Gensdepot();

	class Lbwildfrontline extends warrior.mdb.TTable<Integer, xbean.lobby.LBWildFrontLine> {
		@Override
		public String getName() {
			return "lbwildfrontline";
		}

		@Override
		protected xbean.lobby.LBWildFrontLine newValue() {
			xbean.lobby.LBWildFrontLine value = new xbean.lobby.LBWildFrontLine();
			return value;
		}

		xbean.lobby.LBWildFrontLine insert(Integer key, xbean.lobby.LBWildFrontLine value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LBWildFrontLine insert(Integer key, xbean.lobby.LBWildFrontLine value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LBWildFrontLine deepCopy(xbean.lobby.LBWildFrontLine v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LBWildFrontLine value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.LBWildFrontLine unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LBWildFrontLine.class);
		}

	};

	Lbwildfrontline lbwildfrontline = new Lbwildfrontline();

	class Playerrankinfo extends warrior.mdb.TTable<Long, xbean.lobby.PlayerRankData> {
		@Override
		public String getName() {
			return "playerrankinfo";
		}

		@Override
		protected xbean.lobby.PlayerRankData newValue() {
			xbean.lobby.PlayerRankData value = new xbean.lobby.PlayerRankData();
			return value;
		}

		xbean.lobby.PlayerRankData insert(Long key, xbean.lobby.PlayerRankData value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerRankData insert(Long key, xbean.lobby.PlayerRankData value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerRankData deepCopy(xbean.lobby.PlayerRankData v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerRankData value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerRankData unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerRankData.class);
		}

	};

	Playerrankinfo playerrankinfo = new Playerrankinfo();

	class Playertroop extends warrior.mdb.TTable<Long, xbean.lobby.PlayerTroop> {
		@Override
		public String getName() {
			return "playertroop";
		}

		@Override
		protected xbean.lobby.PlayerTroop newValue() {
			xbean.lobby.PlayerTroop value = new xbean.lobby.PlayerTroop();
			return value;
		}

		xbean.lobby.PlayerTroop insert(Long key, xbean.lobby.PlayerTroop value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerTroop insert(Long key, xbean.lobby.PlayerTroop value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerTroop deepCopy(xbean.lobby.PlayerTroop v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerTroop value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerTroop unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerTroop.class);
		}

	};

	Playertroop playertroop = new Playertroop();

	class Playermajorzombie extends warrior.mdb.TTable<Long, xbean.lobby.PlayerMajorZombie> {
		@Override
		public String getName() {
			return "playermajorzombie";
		}

		@Override
		protected xbean.lobby.PlayerMajorZombie newValue() {
			xbean.lobby.PlayerMajorZombie value = new xbean.lobby.PlayerMajorZombie();
			return value;
		}

		xbean.lobby.PlayerMajorZombie insert(Long key, xbean.lobby.PlayerMajorZombie value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerMajorZombie insert(Long key, xbean.lobby.PlayerMajorZombie value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerMajorZombie deepCopy(xbean.lobby.PlayerMajorZombie v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerMajorZombie value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerMajorZombie unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerMajorZombie.class);
		}

	};

	Playermajorzombie playermajorzombie = new Playermajorzombie();

	class Factiondepotrecordcollect extends warrior.mdb.TTable<Integer, xbean.lobby.FactionDepotRecordCollect> {
		@Override
		public String getName() {
			return "factiondepotrecordcollect";
		}

		@Override
		protected xbean.lobby.FactionDepotRecordCollect newValue() {
			xbean.lobby.FactionDepotRecordCollect value = new xbean.lobby.FactionDepotRecordCollect();
			return value;
		}

		xbean.lobby.FactionDepotRecordCollect insert(Integer key, xbean.lobby.FactionDepotRecordCollect value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.FactionDepotRecordCollect insert(Integer key, xbean.lobby.FactionDepotRecordCollect value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.FactionDepotRecordCollect deepCopy(xbean.lobby.FactionDepotRecordCollect v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.FactionDepotRecordCollect value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.FactionDepotRecordCollect unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.FactionDepotRecordCollect.class);
		}

	};

	Factiondepotrecordcollect factiondepotrecordcollect = new Factiondepotrecordcollect();

	class Allianceids extends warrior.mdb.TTable<Integer, xbean.lobby.AllianceIds> {
		@Override
		public String getName() {
			return "allianceids";
		}

		@Override
		protected xbean.lobby.AllianceIds newValue() {
			xbean.lobby.AllianceIds value = new xbean.lobby.AllianceIds();
			return value;
		}

		xbean.lobby.AllianceIds insert(Integer key, xbean.lobby.AllianceIds value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.AllianceIds insert(Integer key, xbean.lobby.AllianceIds value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.AllianceIds deepCopy(xbean.lobby.AllianceIds v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.AllianceIds value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.AllianceIds unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.AllianceIds.class);
		}

	};

	Allianceids allianceids = new Allianceids();

	class Playernewbiehelp extends warrior.mdb.TTable<Long, xbean.lobby.PlayerNewbieHelp> {
		@Override
		public String getName() {
			return "playernewbiehelp";
		}

		@Override
		protected xbean.lobby.PlayerNewbieHelp newValue() {
			xbean.lobby.PlayerNewbieHelp value = new xbean.lobby.PlayerNewbieHelp();
			return value;
		}

		xbean.lobby.PlayerNewbieHelp insert(Long key, xbean.lobby.PlayerNewbieHelp value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerNewbieHelp insert(Long key, xbean.lobby.PlayerNewbieHelp value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerNewbieHelp deepCopy(xbean.lobby.PlayerNewbieHelp v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerNewbieHelp value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerNewbieHelp unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerNewbieHelp.class);
		}

	};

	Playernewbiehelp playernewbiehelp = new Playernewbiehelp();

	class Chargeorderhistory extends warrior.mdb.TTable<Long, xbean.lobby.OrderInfo> {
		@Override
		public String getName() {
			return "chargeorderhistory";
		}

		@Override
		protected xbean.lobby.OrderInfo newValue() {
			xbean.lobby.OrderInfo value = new xbean.lobby.OrderInfo();
			return value;
		}

		xbean.lobby.OrderInfo insert(Long key, xbean.lobby.OrderInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.OrderInfo insert(Long key, xbean.lobby.OrderInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.OrderInfo deepCopy(xbean.lobby.OrderInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.OrderInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.OrderInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.OrderInfo.class);
		}

	};

	Chargeorderhistory chargeorderhistory = new Chargeorderhistory();

	class Genscontest extends warrior.mdb.TTable<Long, xbean.lobby.GensContest> {
		@Override
		public String getName() {
			return "genscontest";
		}

		@Override
		protected xbean.lobby.GensContest newValue() {
			xbean.lobby.GensContest value = new xbean.lobby.GensContest();
			return value;
		}

		xbean.lobby.GensContest insert(Long key, xbean.lobby.GensContest value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GensContest insert(Long key, xbean.lobby.GensContest value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GensContest deepCopy(xbean.lobby.GensContest v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GensContest value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GensContest unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GensContest.class);
		}

	};

	Genscontest genscontest = new Genscontest();

	class Playerblacklist extends warrior.mdb.TTable<Long, xbean.lobby.PlayerBlackList> {
		@Override
		public String getName() {
			return "playerblacklist";
		}

		@Override
		protected xbean.lobby.PlayerBlackList newValue() {
			xbean.lobby.PlayerBlackList value = new xbean.lobby.PlayerBlackList();
			return value;
		}

		xbean.lobby.PlayerBlackList insert(Long key, xbean.lobby.PlayerBlackList value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerBlackList insert(Long key, xbean.lobby.PlayerBlackList value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerBlackList deepCopy(xbean.lobby.PlayerBlackList v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerBlackList value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerBlackList unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerBlackList.class);
		}

	};

	Playerblacklist playerblacklist = new Playerblacklist();

	class Gentes extends warrior.mdb.TTable<Long, xbean.lobby.Gens> {
		@Override
		public String getName() {
			return "gentes";
		}

		@Override
		protected xbean.lobby.Gens newValue() {
			xbean.lobby.Gens value = new xbean.lobby.Gens();
			return value;
		}

		xbean.lobby.Gens insert(Long key, xbean.lobby.Gens value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.Gens insert(Long key, xbean.lobby.Gens value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.Gens deepCopy(xbean.lobby.Gens v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.Gens value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.Gens unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.Gens.class);
		}

	};

	Gentes gentes = new Gentes();

	class Gentesname extends warrior.mdb.TTable<String, Long> {
		@Override
		public String getName() {
			return "gentesname";
		}

		@Override
		protected Long newValue() {
			Long value = 0L;
			return value;
		}

		Long insert(String key, Long value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		Long insert(String key, Long value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected Long deepCopy(Long v){
            throw new RuntimeException("need impl");
		}

		boolean delete(String key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(String key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(Long value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected String unmarshalKey(String os) {
			return FastJson.parseObject(os, String.class);
		}

		@Override
		protected Long unmarshalValue(String os) {
			return FastJson.parseObject(os, Long.class);
		}

	};

	Gentesname gentesname = new Gentesname();

	class Lbcarrierrentinglist extends warrior.mdb.TTable<Integer, xbean.lobby.LbCarrierRentingList> {
		@Override
		public String getName() {
			return "lbcarrierrentinglist";
		}

		@Override
		protected xbean.lobby.LbCarrierRentingList newValue() {
			xbean.lobby.LbCarrierRentingList value = new xbean.lobby.LbCarrierRentingList();
			return value;
		}

		xbean.lobby.LbCarrierRentingList insert(Integer key, xbean.lobby.LbCarrierRentingList value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LbCarrierRentingList insert(Integer key, xbean.lobby.LbCarrierRentingList value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LbCarrierRentingList deepCopy(xbean.lobby.LbCarrierRentingList v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LbCarrierRentingList value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.LbCarrierRentingList unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LbCarrierRentingList.class);
		}

	};

	Lbcarrierrentinglist lbcarrierrentinglist = new Lbcarrierrentinglist();

	class Factionarmamentdonate extends warrior.mdb.TTable<Integer, xbean.lobby.FactionArmamentDonate> {
		@Override
		public String getName() {
			return "factionarmamentdonate";
		}

		@Override
		protected xbean.lobby.FactionArmamentDonate newValue() {
			xbean.lobby.FactionArmamentDonate value = new xbean.lobby.FactionArmamentDonate();
			return value;
		}

		xbean.lobby.FactionArmamentDonate insert(Integer key, xbean.lobby.FactionArmamentDonate value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.FactionArmamentDonate insert(Integer key, xbean.lobby.FactionArmamentDonate value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.FactionArmamentDonate deepCopy(xbean.lobby.FactionArmamentDonate v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.FactionArmamentDonate value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.FactionArmamentDonate unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.FactionArmamentDonate.class);
		}

	};

	Factionarmamentdonate factionarmamentdonate = new Factionarmamentdonate();

	class Singleachieve extends warrior.mdb.TTable<Integer, xbean.lobby.SingleAchieve> {
		@Override
		public String getName() {
			return "singleachieve";
		}

		@Override
		protected xbean.lobby.SingleAchieve newValue() {
			xbean.lobby.SingleAchieve value = new xbean.lobby.SingleAchieve();
			return value;
		}

		xbean.lobby.SingleAchieve insert(Integer key, xbean.lobby.SingleAchieve value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.SingleAchieve insert(Integer key, xbean.lobby.SingleAchieve value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.SingleAchieve deepCopy(xbean.lobby.SingleAchieve v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.SingleAchieve value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.SingleAchieve unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.SingleAchieve.class);
		}

	};

	Singleachieve singleachieve = new Singleachieve();

	class Playerstatisticslist extends warrior.mdb.TTable<Long, xbean.lobby.PlayerStatisticsList> {
		@Override
		public String getName() {
			return "playerstatisticslist";
		}

		@Override
		protected xbean.lobby.PlayerStatisticsList newValue() {
			xbean.lobby.PlayerStatisticsList value = new xbean.lobby.PlayerStatisticsList();
			return value;
		}

		xbean.lobby.PlayerStatisticsList insert(Long key, xbean.lobby.PlayerStatisticsList value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerStatisticsList insert(Long key, xbean.lobby.PlayerStatisticsList value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerStatisticsList deepCopy(xbean.lobby.PlayerStatisticsList v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerStatisticsList value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerStatisticsList unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerStatisticsList.class);
		}

	};

	Playerstatisticslist playerstatisticslist = new Playerstatisticslist();

	class Playerstatisticslog extends warrior.mdb.TTable<Long, xbean.lobby.PlayerStatisticsLog> {
		@Override
		public String getName() {
			return "playerstatisticslog";
		}

		@Override
		protected xbean.lobby.PlayerStatisticsLog newValue() {
			xbean.lobby.PlayerStatisticsLog value = new xbean.lobby.PlayerStatisticsLog();
			return value;
		}

		xbean.lobby.PlayerStatisticsLog insert(Long key, xbean.lobby.PlayerStatisticsLog value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerStatisticsLog insert(Long key, xbean.lobby.PlayerStatisticsLog value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerStatisticsLog deepCopy(xbean.lobby.PlayerStatisticsLog v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerStatisticsLog value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerStatisticsLog unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerStatisticsLog.class);
		}

	};

	Playerstatisticslog playerstatisticslog = new Playerstatisticslog();

	class Factiondepotmakeinfo extends warrior.mdb.TTable<Integer, xbean.lobby.FactionDepotMakeInfo> {
		@Override
		public String getName() {
			return "factiondepotmakeinfo";
		}

		@Override
		protected xbean.lobby.FactionDepotMakeInfo newValue() {
			xbean.lobby.FactionDepotMakeInfo value = new xbean.lobby.FactionDepotMakeInfo();
			return value;
		}

		xbean.lobby.FactionDepotMakeInfo insert(Integer key, xbean.lobby.FactionDepotMakeInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.FactionDepotMakeInfo insert(Integer key, xbean.lobby.FactionDepotMakeInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.FactionDepotMakeInfo deepCopy(xbean.lobby.FactionDepotMakeInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.FactionDepotMakeInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.FactionDepotMakeInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.FactionDepotMakeInfo.class);
		}

	};

	Factiondepotmakeinfo factiondepotmakeinfo = new Factiondepotmakeinfo();

	class Nationinfo extends warrior.mdb.TTable<Long, xbean.lobby.NationInfo> {
		@Override
		public String getName() {
			return "nationinfo";
		}

		@Override
		protected xbean.lobby.NationInfo newValue() {
			xbean.lobby.NationInfo value = new xbean.lobby.NationInfo();
			return value;
		}

		xbean.lobby.NationInfo insert(Long key, xbean.lobby.NationInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.NationInfo insert(Long key, xbean.lobby.NationInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.NationInfo deepCopy(xbean.lobby.NationInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.NationInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.NationInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.NationInfo.class);
		}

	};

	Nationinfo nationinfo = new Nationinfo();

	class Lguser extends warrior.mdb.TTable<String, xbean.lobby.LgUser> {
		@Override
		public String getName() {
			return "lguser";
		}

		@Override
		protected xbean.lobby.LgUser newValue() {
			xbean.lobby.LgUser value = new xbean.lobby.LgUser();
			return value;
		}

		xbean.lobby.LgUser insert(String key, xbean.lobby.LgUser value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LgUser insert(String key, xbean.lobby.LgUser value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LgUser deepCopy(xbean.lobby.LgUser v){
            throw new RuntimeException("need impl");
		}

		boolean delete(String key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(String key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LgUser value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected String unmarshalKey(String os) {
			return FastJson.parseObject(os, String.class);
		}

		@Override
		protected xbean.lobby.LgUser unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LgUser.class);
		}

	};

	Lguser lguser = new Lguser();

	class Limitinfo extends warrior.mdb.TTable<Integer, xbean.lobby.LimitUidIp> {
		@Override
		public String getName() {
			return "limitinfo";
		}

		@Override
		protected xbean.lobby.LimitUidIp newValue() {
			xbean.lobby.LimitUidIp value = new xbean.lobby.LimitUidIp();
			return value;
		}

		xbean.lobby.LimitUidIp insert(Integer key, xbean.lobby.LimitUidIp value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LimitUidIp insert(Integer key, xbean.lobby.LimitUidIp value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LimitUidIp deepCopy(xbean.lobby.LimitUidIp v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LimitUidIp value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.LimitUidIp unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LimitUidIp.class);
		}

	};

	Limitinfo limitinfo = new Limitinfo();

	class Playerreportinfo extends warrior.mdb.TTable<Long, xbean.lobby.PlayerReportInfo> {
		@Override
		public String getName() {
			return "playerreportinfo";
		}

		@Override
		protected xbean.lobby.PlayerReportInfo newValue() {
			xbean.lobby.PlayerReportInfo value = new xbean.lobby.PlayerReportInfo();
			return value;
		}

		xbean.lobby.PlayerReportInfo insert(Long key, xbean.lobby.PlayerReportInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerReportInfo insert(Long key, xbean.lobby.PlayerReportInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerReportInfo deepCopy(xbean.lobby.PlayerReportInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerReportInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerReportInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerReportInfo.class);
		}

	};

	Playerreportinfo playerreportinfo = new Playerreportinfo();

	class Actfightscore extends warrior.mdb.TTable<Long, xbean.lobby.ActFightScore> {
		@Override
		public String getName() {
			return "actfightscore";
		}

		@Override
		protected xbean.lobby.ActFightScore newValue() {
			xbean.lobby.ActFightScore value = new xbean.lobby.ActFightScore();
			return value;
		}

		xbean.lobby.ActFightScore insert(Long key, xbean.lobby.ActFightScore value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.ActFightScore insert(Long key, xbean.lobby.ActFightScore value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.ActFightScore deepCopy(xbean.lobby.ActFightScore v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.ActFightScore value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.ActFightScore unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.ActFightScore.class);
		}

	};

	Actfightscore actfightscore = new Actfightscore();

	class Actcityfightenroll extends warrior.mdb.TTable<Long, xbean.lobby.ActCityFightEnroll> {
		@Override
		public String getName() {
			return "actcityfightenroll";
		}

		@Override
		protected xbean.lobby.ActCityFightEnroll newValue() {
			xbean.lobby.ActCityFightEnroll value = new xbean.lobby.ActCityFightEnroll();
			return value;
		}

		xbean.lobby.ActCityFightEnroll insert(Long key, xbean.lobby.ActCityFightEnroll value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.ActCityFightEnroll insert(Long key, xbean.lobby.ActCityFightEnroll value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.ActCityFightEnroll deepCopy(xbean.lobby.ActCityFightEnroll v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.ActCityFightEnroll value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.ActCityFightEnroll unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.ActCityFightEnroll.class);
		}

	};

	Actcityfightenroll actcityfightenroll = new Actcityfightenroll();

	class Gensrent extends warrior.mdb.TTable<Long, xbean.lobby.GensRentInfo> {
		@Override
		public String getName() {
			return "gensrent";
		}

		@Override
		protected xbean.lobby.GensRentInfo newValue() {
			xbean.lobby.GensRentInfo value = new xbean.lobby.GensRentInfo();
			return value;
		}

		xbean.lobby.GensRentInfo insert(Long key, xbean.lobby.GensRentInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.GensRentInfo insert(Long key, xbean.lobby.GensRentInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.GensRentInfo deepCopy(xbean.lobby.GensRentInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.GensRentInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.GensRentInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.GensRentInfo.class);
		}

	};

	Gensrent gensrent = new Gensrent();

	class Factiondepot extends warrior.mdb.TTable<Integer, xbean.lobby.FactionDepot> {
		@Override
		public String getName() {
			return "factiondepot";
		}

		@Override
		protected xbean.lobby.FactionDepot newValue() {
			xbean.lobby.FactionDepot value = new xbean.lobby.FactionDepot();
			return value;
		}

		xbean.lobby.FactionDepot insert(Integer key, xbean.lobby.FactionDepot value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.FactionDepot insert(Integer key, xbean.lobby.FactionDepot value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.FactionDepot deepCopy(xbean.lobby.FactionDepot v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.FactionDepot value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.FactionDepot unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.FactionDepot.class);
		}

	};

	Factiondepot factiondepot = new Factiondepot();

	class Troopform extends warrior.mdb.TTable<Long, xbean.lobby.TroopForm> {
		@Override
		public String getName() {
			return "troopform";
		}

		@Override
		protected xbean.lobby.TroopForm newValue() {
			xbean.lobby.TroopForm value = new xbean.lobby.TroopForm();
			return value;
		}

		xbean.lobby.TroopForm insert(Long key, xbean.lobby.TroopForm value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.TroopForm insert(Long key, xbean.lobby.TroopForm value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.TroopForm deepCopy(xbean.lobby.TroopForm v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.TroopForm value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.TroopForm unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.TroopForm.class);
		}

	};

	Troopform troopform = new Troopform();

	class Lgplayerinfoname extends warrior.mdb.TTable<String, xbean.lobby.PlayerUserInfo> {
		@Override
		public String getName() {
			return "lgplayerinfoname";
		}

		@Override
		protected xbean.lobby.PlayerUserInfo newValue() {
			xbean.lobby.PlayerUserInfo value = new xbean.lobby.PlayerUserInfo();
			return value;
		}

		xbean.lobby.PlayerUserInfo insert(String key, xbean.lobby.PlayerUserInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerUserInfo insert(String key, xbean.lobby.PlayerUserInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerUserInfo deepCopy(xbean.lobby.PlayerUserInfo v){
            throw new RuntimeException("need impl");
		}

		boolean delete(String key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(String key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerUserInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected String unmarshalKey(String os) {
			return FastJson.parseObject(os, String.class);
		}

		@Override
		protected xbean.lobby.PlayerUserInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerUserInfo.class);
		}

	};

	Lgplayerinfoname lgplayerinfoname = new Lgplayerinfoname();

	class Faction extends warrior.mdb.TTable<Integer, xbean.lobby.Faction> {
		@Override
		public String getName() {
			return "faction";
		}

		@Override
		protected xbean.lobby.Faction newValue() {
			xbean.lobby.Faction value = new xbean.lobby.Faction();
			return value;
		}

		xbean.lobby.Faction insert(Integer key, xbean.lobby.Faction value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.Faction insert(Integer key, xbean.lobby.Faction value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.Faction deepCopy(xbean.lobby.Faction v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Integer key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Integer key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.Faction value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.lobby.Faction unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.Faction.class);
		}

	};

	Faction faction = new Faction();

	class Playerinvitenew extends warrior.mdb.TTable<Long, xbean.lobby.PlayerInviteNew> {
		@Override
		public String getName() {
			return "playerinvitenew";
		}

		@Override
		protected xbean.lobby.PlayerInviteNew newValue() {
			xbean.lobby.PlayerInviteNew value = new xbean.lobby.PlayerInviteNew();
			return value;
		}

		xbean.lobby.PlayerInviteNew insert(Long key, xbean.lobby.PlayerInviteNew value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerInviteNew insert(Long key, xbean.lobby.PlayerInviteNew value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerInviteNew deepCopy(xbean.lobby.PlayerInviteNew v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerInviteNew value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerInviteNew unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerInviteNew.class);
		}

	};

	Playerinvitenew playerinvitenew = new Playerinvitenew();

	class Lballareareportdetail extends warrior.mdb.TTable<Long, xbean.lobby.LBAllAreaReportDetail> {
		@Override
		public String getName() {
			return "lballareareportdetail";
		}

		@Override
		protected xbean.lobby.LBAllAreaReportDetail newValue() {
			xbean.lobby.LBAllAreaReportDetail value = new xbean.lobby.LBAllAreaReportDetail();
			return value;
		}

		xbean.lobby.LBAllAreaReportDetail insert(Long key, xbean.lobby.LBAllAreaReportDetail value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.LBAllAreaReportDetail insert(Long key, xbean.lobby.LBAllAreaReportDetail value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.LBAllAreaReportDetail deepCopy(xbean.lobby.LBAllAreaReportDetail v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.LBAllAreaReportDetail value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.LBAllAreaReportDetail unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.LBAllAreaReportDetail.class);
		}

	};

	Lballareareportdetail lballareareportdetail = new Lballareareportdetail();

	class Playerfriend extends warrior.mdb.TTable<Long, xbean.lobby.PlayerFriend> {
		@Override
		public String getName() {
			return "playerfriend";
		}

		@Override
		protected xbean.lobby.PlayerFriend newValue() {
			xbean.lobby.PlayerFriend value = new xbean.lobby.PlayerFriend();
			return value;
		}

		xbean.lobby.PlayerFriend insert(Long key, xbean.lobby.PlayerFriend value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.PlayerFriend insert(Long key, xbean.lobby.PlayerFriend value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.PlayerFriend deepCopy(xbean.lobby.PlayerFriend v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.PlayerFriend value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.PlayerFriend unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.PlayerFriend.class);
		}

	};

	Playerfriend playerfriend = new Playerfriend();

	class Serverinfolist extends warrior.mdb.TTable<Long, xbean.lobby.ServerInfoList> {
		@Override
		public String getName() {
			return "serverinfolist";
		}

		@Override
		protected xbean.lobby.ServerInfoList newValue() {
			xbean.lobby.ServerInfoList value = new xbean.lobby.ServerInfoList();
			return value;
		}

		xbean.lobby.ServerInfoList insert(Long key, xbean.lobby.ServerInfoList value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.ServerInfoList insert(Long key, xbean.lobby.ServerInfoList value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.ServerInfoList deepCopy(xbean.lobby.ServerInfoList v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.ServerInfoList value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.ServerInfoList unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.ServerInfoList.class);
		}

	};

	Serverinfolist serverinfolist = new Serverinfolist();

	class Factioncarriermake extends warrior.mdb.TTable<Long, xbean.lobby.FactionCarrierMake> {
		@Override
		public String getName() {
			return "factioncarriermake";
		}

		@Override
		protected xbean.lobby.FactionCarrierMake newValue() {
			xbean.lobby.FactionCarrierMake value = new xbean.lobby.FactionCarrierMake();
			return value;
		}

		xbean.lobby.FactionCarrierMake insert(Long key, xbean.lobby.FactionCarrierMake value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.lobby.FactionCarrierMake insert(Long key, xbean.lobby.FactionCarrierMake value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.lobby.FactionCarrierMake deepCopy(xbean.lobby.FactionCarrierMake v){
            throw new RuntimeException("need impl");
		}

		boolean delete(Long key) {
			return remove(key);
		}

		@Override
		protected String marshalKey(Long key) {
			return FastJson.toJSONString(key);
		}

		@Override
		protected String marshalValue(xbean.lobby.FactionCarrierMake value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.lobby.FactionCarrierMake unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.lobby.FactionCarrierMake.class);
		}

	};

	Factioncarriermake factioncarriermake = new Factioncarriermake();


}
