package beans.table.logic;

import warrior.mdb.util.FastJson;

final class _Tables_ {
	volatile static _Tables_ instance;

	private _Tables_ () {
		instance = this;
	}

	class Playercarrier extends warrior.mdb.TTable<Long, xbean.logic.PlayerCarrier> {
		@Override
		public String getName() {
			return "playercarrier";
		}

		@Override
		protected xbean.logic.PlayerCarrier newValue() {
			xbean.logic.PlayerCarrier value = new xbean.logic.PlayerCarrier();
			return value;
		}

		xbean.logic.PlayerCarrier insert(Long key, xbean.logic.PlayerCarrier value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerCarrier insert(Long key, xbean.logic.PlayerCarrier value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerCarrier deepCopy(xbean.logic.PlayerCarrier v){
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
		protected String marshalValue(xbean.logic.PlayerCarrier value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerCarrier unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerCarrier.class);
		}

	};

	Playercarrier playercarrier = new Playercarrier();

	class Bag extends warrior.mdb.TTable<Long, xbean.logic.AllBag> {
		@Override
		public String getName() {
			return "bag";
		}

		@Override
		protected xbean.logic.AllBag newValue() {
			xbean.logic.AllBag value = new xbean.logic.AllBag();
			return value;
		}

		xbean.logic.AllBag insert(Long key, xbean.logic.AllBag value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.AllBag insert(Long key, xbean.logic.AllBag value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.AllBag deepCopy(xbean.logic.AllBag v){
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
		protected String marshalValue(xbean.logic.AllBag value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.AllBag unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.AllBag.class);
		}

	};

	Bag bag = new Bag();

	class Playerdungeonrecord extends warrior.mdb.TTable<Long, xbean.logic.PlayerDungeonRecord> {
		@Override
		public String getName() {
			return "playerdungeonrecord";
		}

		@Override
		protected xbean.logic.PlayerDungeonRecord newValue() {
			xbean.logic.PlayerDungeonRecord value = new xbean.logic.PlayerDungeonRecord();
			return value;
		}

		xbean.logic.PlayerDungeonRecord insert(Long key, xbean.logic.PlayerDungeonRecord value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerDungeonRecord insert(Long key, xbean.logic.PlayerDungeonRecord value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerDungeonRecord deepCopy(xbean.logic.PlayerDungeonRecord v){
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
		protected String marshalValue(xbean.logic.PlayerDungeonRecord value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerDungeonRecord unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerDungeonRecord.class);
		}

	};

	Playerdungeonrecord playerdungeonrecord = new Playerdungeonrecord();

	class Playergensrecruit extends warrior.mdb.TTable<Long, xbean.logic.GensRecruitInfo> {
		@Override
		public String getName() {
			return "playergensrecruit";
		}

		@Override
		protected xbean.logic.GensRecruitInfo newValue() {
			xbean.logic.GensRecruitInfo value = new xbean.logic.GensRecruitInfo();
			return value;
		}

		xbean.logic.GensRecruitInfo insert(Long key, xbean.logic.GensRecruitInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.GensRecruitInfo insert(Long key, xbean.logic.GensRecruitInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.GensRecruitInfo deepCopy(xbean.logic.GensRecruitInfo v){
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
		protected String marshalValue(xbean.logic.GensRecruitInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.GensRecruitInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.GensRecruitInfo.class);
		}

	};

	Playergensrecruit playergensrecruit = new Playergensrecruit();

	class Playerbuffinfo extends warrior.mdb.TTable<Long, xbean.logic.PlayerBuffInfo> {
		@Override
		public String getName() {
			return "playerbuffinfo";
		}

		@Override
		protected xbean.logic.PlayerBuffInfo newValue() {
			xbean.logic.PlayerBuffInfo value = new xbean.logic.PlayerBuffInfo();
			return value;
		}

		xbean.logic.PlayerBuffInfo insert(Long key, xbean.logic.PlayerBuffInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerBuffInfo insert(Long key, xbean.logic.PlayerBuffInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerBuffInfo deepCopy(xbean.logic.PlayerBuffInfo v){
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
		protected String marshalValue(xbean.logic.PlayerBuffInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerBuffInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerBuffInfo.class);
		}

	};

	Playerbuffinfo playerbuffinfo = new Playerbuffinfo();

	class Playermilitarysup extends warrior.mdb.TTable<Long, xbean.logic.PlayerMilitarySup> {
		@Override
		public String getName() {
			return "playermilitarysup";
		}

		@Override
		protected xbean.logic.PlayerMilitarySup newValue() {
			xbean.logic.PlayerMilitarySup value = new xbean.logic.PlayerMilitarySup();
			return value;
		}

		xbean.logic.PlayerMilitarySup insert(Long key, xbean.logic.PlayerMilitarySup value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerMilitarySup insert(Long key, xbean.logic.PlayerMilitarySup value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerMilitarySup deepCopy(xbean.logic.PlayerMilitarySup v){
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
		protected String marshalValue(xbean.logic.PlayerMilitarySup value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerMilitarySup unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerMilitarySup.class);
		}

	};

	Playermilitarysup playermilitarysup = new Playermilitarysup();

	class Playerquest extends warrior.mdb.TTable<Long, xbean.logic.PlayerQuest> {
		@Override
		public String getName() {
			return "playerquest";
		}

		@Override
		protected xbean.logic.PlayerQuest newValue() {
			xbean.logic.PlayerQuest value = new xbean.logic.PlayerQuest();
			return value;
		}

		xbean.logic.PlayerQuest insert(Long key, xbean.logic.PlayerQuest value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerQuest insert(Long key, xbean.logic.PlayerQuest value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerQuest deepCopy(xbean.logic.PlayerQuest v){
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
		protected String marshalValue(xbean.logic.PlayerQuest value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerQuest unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerQuest.class);
		}

	};

	Playerquest playerquest = new Playerquest();

	class Playerguide extends warrior.mdb.TTable<Long, xbean.logic.PlayerGuide> {
		@Override
		public String getName() {
			return "playerguide";
		}

		@Override
		protected xbean.logic.PlayerGuide newValue() {
			xbean.logic.PlayerGuide value = new xbean.logic.PlayerGuide();
			return value;
		}

		xbean.logic.PlayerGuide insert(Long key, xbean.logic.PlayerGuide value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerGuide insert(Long key, xbean.logic.PlayerGuide value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerGuide deepCopy(xbean.logic.PlayerGuide v){
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
		protected String marshalValue(xbean.logic.PlayerGuide value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerGuide unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerGuide.class);
		}

	};

	Playerguide playerguide = new Playerguide();

	class Playerorientation extends warrior.mdb.TTable<Long, xbean.logic.PlayerOrientation> {
		@Override
		public String getName() {
			return "playerorientation";
		}

		@Override
		protected xbean.logic.PlayerOrientation newValue() {
			xbean.logic.PlayerOrientation value = new xbean.logic.PlayerOrientation();
			return value;
		}

		xbean.logic.PlayerOrientation insert(Long key, xbean.logic.PlayerOrientation value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerOrientation insert(Long key, xbean.logic.PlayerOrientation value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerOrientation deepCopy(xbean.logic.PlayerOrientation v){
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
		protected String marshalValue(xbean.logic.PlayerOrientation value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerOrientation unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerOrientation.class);
		}

	};

	Playerorientation playerorientation = new Playerorientation();

	class Playerrandomgift extends warrior.mdb.TTable<Long, xbean.logic.PlayerRandomGift> {
		@Override
		public String getName() {
			return "playerrandomgift";
		}

		@Override
		protected xbean.logic.PlayerRandomGift newValue() {
			xbean.logic.PlayerRandomGift value = new xbean.logic.PlayerRandomGift();
			return value;
		}

		xbean.logic.PlayerRandomGift insert(Long key, xbean.logic.PlayerRandomGift value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerRandomGift insert(Long key, xbean.logic.PlayerRandomGift value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerRandomGift deepCopy(xbean.logic.PlayerRandomGift v){
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
		protected String marshalValue(xbean.logic.PlayerRandomGift value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerRandomGift unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerRandomGift.class);
		}

	};

	Playerrandomgift playerrandomgift = new Playerrandomgift();

	class Playermatchcount extends warrior.mdb.TTable<Long, xbean.logic.PlayerMatchCount> {
		@Override
		public String getName() {
			return "playermatchcount";
		}

		@Override
		protected xbean.logic.PlayerMatchCount newValue() {
			xbean.logic.PlayerMatchCount value = new xbean.logic.PlayerMatchCount();
			return value;
		}

		xbean.logic.PlayerMatchCount insert(Long key, xbean.logic.PlayerMatchCount value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerMatchCount insert(Long key, xbean.logic.PlayerMatchCount value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerMatchCount deepCopy(xbean.logic.PlayerMatchCount v){
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
		protected String marshalValue(xbean.logic.PlayerMatchCount value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerMatchCount unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerMatchCount.class);
		}

	};

	Playermatchcount playermatchcount = new Playermatchcount();

	class Playerwarmakesbar extends warrior.mdb.TTable<Long, xbean.logic.PlayerWarMakesBar> {
		@Override
		public String getName() {
			return "playerwarmakesbar";
		}

		@Override
		protected xbean.logic.PlayerWarMakesBar newValue() {
			xbean.logic.PlayerWarMakesBar value = new xbean.logic.PlayerWarMakesBar();
			return value;
		}

		xbean.logic.PlayerWarMakesBar insert(Long key, xbean.logic.PlayerWarMakesBar value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerWarMakesBar insert(Long key, xbean.logic.PlayerWarMakesBar value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerWarMakesBar deepCopy(xbean.logic.PlayerWarMakesBar v){
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
		protected String marshalValue(xbean.logic.PlayerWarMakesBar value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerWarMakesBar unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerWarMakesBar.class);
		}

	};

	Playerwarmakesbar playerwarmakesbar = new Playerwarmakesbar();

	class Playertalentinfo extends warrior.mdb.TTable<Long, xbean.logic.PlayerTalentInfo> {
		@Override
		public String getName() {
			return "playertalentinfo";
		}

		@Override
		protected xbean.logic.PlayerTalentInfo newValue() {
			xbean.logic.PlayerTalentInfo value = new xbean.logic.PlayerTalentInfo();
			return value;
		}

		xbean.logic.PlayerTalentInfo insert(Long key, xbean.logic.PlayerTalentInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerTalentInfo insert(Long key, xbean.logic.PlayerTalentInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerTalentInfo deepCopy(xbean.logic.PlayerTalentInfo v){
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
		protected String marshalValue(xbean.logic.PlayerTalentInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerTalentInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerTalentInfo.class);
		}

	};

	Playertalentinfo playertalentinfo = new Playertalentinfo();

	class Playersevenlogin extends warrior.mdb.TTable<Long, xbean.logic.PlayerSevenLogin> {
		@Override
		public String getName() {
			return "playersevenlogin";
		}

		@Override
		protected xbean.logic.PlayerSevenLogin newValue() {
			xbean.logic.PlayerSevenLogin value = new xbean.logic.PlayerSevenLogin();
			return value;
		}

		xbean.logic.PlayerSevenLogin insert(Long key, xbean.logic.PlayerSevenLogin value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerSevenLogin insert(Long key, xbean.logic.PlayerSevenLogin value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerSevenLogin deepCopy(xbean.logic.PlayerSevenLogin v){
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
		protected String marshalValue(xbean.logic.PlayerSevenLogin value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerSevenLogin unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerSevenLogin.class);
		}

	};

	Playersevenlogin playersevenlogin = new Playersevenlogin();

	class Playerskin extends warrior.mdb.TTable<Long, xbean.logic.PlayerSkin> {
		@Override
		public String getName() {
			return "playerskin";
		}

		@Override
		protected xbean.logic.PlayerSkin newValue() {
			xbean.logic.PlayerSkin value = new xbean.logic.PlayerSkin();
			return value;
		}

		xbean.logic.PlayerSkin insert(Long key, xbean.logic.PlayerSkin value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerSkin insert(Long key, xbean.logic.PlayerSkin value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerSkin deepCopy(xbean.logic.PlayerSkin v){
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
		protected String marshalValue(xbean.logic.PlayerSkin value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerSkin unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerSkin.class);
		}

	};

	Playerskin playerskin = new Playerskin();

	class Players extends warrior.mdb.TTable<Long, xbean.logic.Player> {
		@Override
		public String getName() {
			return "players";
		}

		@Override
		protected xbean.logic.Player newValue() {
			xbean.logic.Player value = new xbean.logic.Player();
			return value;
		}

		xbean.logic.Player insert(Long key, xbean.logic.Player value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.Player insert(Long key, xbean.logic.Player value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.Player deepCopy(xbean.logic.Player v){
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
		protected String marshalValue(xbean.logic.Player value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.Player unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.Player.class);
		}

	};

	Players players = new Players();

	class Playerwildboss extends warrior.mdb.TTable<Long, xbean.logic.PlayerWildBoss> {
		@Override
		public String getName() {
			return "playerwildboss";
		}

		@Override
		protected xbean.logic.PlayerWildBoss newValue() {
			xbean.logic.PlayerWildBoss value = new xbean.logic.PlayerWildBoss();
			return value;
		}

		xbean.logic.PlayerWildBoss insert(Long key, xbean.logic.PlayerWildBoss value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerWildBoss insert(Long key, xbean.logic.PlayerWildBoss value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerWildBoss deepCopy(xbean.logic.PlayerWildBoss v){
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
		protected String marshalValue(xbean.logic.PlayerWildBoss value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerWildBoss unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerWildBoss.class);
		}

	};

	Playerwildboss playerwildboss = new Playerwildboss();

	class Playerstaticweapon extends warrior.mdb.TTable<Long, xbean.logic.PlayerStaticWeapon> {
		@Override
		public String getName() {
			return "playerstaticweapon";
		}

		@Override
		protected xbean.logic.PlayerStaticWeapon newValue() {
			xbean.logic.PlayerStaticWeapon value = new xbean.logic.PlayerStaticWeapon();
			return value;
		}

		xbean.logic.PlayerStaticWeapon insert(Long key, xbean.logic.PlayerStaticWeapon value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerStaticWeapon insert(Long key, xbean.logic.PlayerStaticWeapon value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerStaticWeapon deepCopy(xbean.logic.PlayerStaticWeapon v){
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
		protected String marshalValue(xbean.logic.PlayerStaticWeapon value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerStaticWeapon unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerStaticWeapon.class);
		}

	};

	Playerstaticweapon playerstaticweapon = new Playerstaticweapon();

	class Playershop extends warrior.mdb.TTable<Long, xbean.logic.PlayerShop> {
		@Override
		public String getName() {
			return "playershop";
		}

		@Override
		protected xbean.logic.PlayerShop newValue() {
			xbean.logic.PlayerShop value = new xbean.logic.PlayerShop();
			return value;
		}

		xbean.logic.PlayerShop insert(Long key, xbean.logic.PlayerShop value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerShop insert(Long key, xbean.logic.PlayerShop value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerShop deepCopy(xbean.logic.PlayerShop v){
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
		protected String marshalValue(xbean.logic.PlayerShop value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerShop unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerShop.class);
		}

	};

	Playershop playershop = new Playershop();

	class Playerwarorders extends warrior.mdb.TTable<Long, xbean.logic.PlayerWarOrders> {
		@Override
		public String getName() {
			return "playerwarorders";
		}

		@Override
		protected xbean.logic.PlayerWarOrders newValue() {
			xbean.logic.PlayerWarOrders value = new xbean.logic.PlayerWarOrders();
			return value;
		}

		xbean.logic.PlayerWarOrders insert(Long key, xbean.logic.PlayerWarOrders value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerWarOrders insert(Long key, xbean.logic.PlayerWarOrders value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerWarOrders deepCopy(xbean.logic.PlayerWarOrders v){
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
		protected String marshalValue(xbean.logic.PlayerWarOrders value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerWarOrders unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerWarOrders.class);
		}

	};

	Playerwarorders playerwarorders = new Playerwarorders();

	class Playerachieve extends warrior.mdb.TTable<Long, xbean.logic.PlayerAchieve> {
		@Override
		public String getName() {
			return "playerachieve";
		}

		@Override
		protected xbean.logic.PlayerAchieve newValue() {
			xbean.logic.PlayerAchieve value = new xbean.logic.PlayerAchieve();
			return value;
		}

		xbean.logic.PlayerAchieve insert(Long key, xbean.logic.PlayerAchieve value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerAchieve insert(Long key, xbean.logic.PlayerAchieve value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerAchieve deepCopy(xbean.logic.PlayerAchieve v){
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
		protected String marshalValue(xbean.logic.PlayerAchieve value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerAchieve unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerAchieve.class);
		}

	};

	Playerachieve playerachieve = new Playerachieve();

	class Playermilitary extends warrior.mdb.TTable<Long, xbean.logic.PlayerMilitary> {
		@Override
		public String getName() {
			return "playermilitary";
		}

		@Override
		protected xbean.logic.PlayerMilitary newValue() {
			xbean.logic.PlayerMilitary value = new xbean.logic.PlayerMilitary();
			return value;
		}

		xbean.logic.PlayerMilitary insert(Long key, xbean.logic.PlayerMilitary value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerMilitary insert(Long key, xbean.logic.PlayerMilitary value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerMilitary deepCopy(xbean.logic.PlayerMilitary v){
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
		protected String marshalValue(xbean.logic.PlayerMilitary value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerMilitary unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerMilitary.class);
		}

	};

	Playermilitary playermilitary = new Playermilitary();

	class Composeorder extends warrior.mdb.TTable<Long, xbean.logic.ComposeOrder> {
		@Override
		public String getName() {
			return "composeorder";
		}

		@Override
		protected xbean.logic.ComposeOrder newValue() {
			xbean.logic.ComposeOrder value = new xbean.logic.ComposeOrder();
			return value;
		}

		xbean.logic.ComposeOrder insert(Long key, xbean.logic.ComposeOrder value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.ComposeOrder insert(Long key, xbean.logic.ComposeOrder value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.ComposeOrder deepCopy(xbean.logic.ComposeOrder v){
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
		protected String marshalValue(xbean.logic.ComposeOrder value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.ComposeOrder unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.ComposeOrder.class);
		}

	};

	Composeorder composeorder = new Composeorder();

	class Playerhome extends warrior.mdb.TTable<Long, xbean.logic.PlayerHome> {
		@Override
		public String getName() {
			return "playerhome";
		}

		@Override
		protected xbean.logic.PlayerHome newValue() {
			xbean.logic.PlayerHome value = new xbean.logic.PlayerHome();
			return value;
		}

		xbean.logic.PlayerHome insert(Long key, xbean.logic.PlayerHome value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerHome insert(Long key, xbean.logic.PlayerHome value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerHome deepCopy(xbean.logic.PlayerHome v){
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
		protected String marshalValue(xbean.logic.PlayerHome value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerHome unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerHome.class);
		}

	};

	Playerhome playerhome = new Playerhome();

	class Newbieguide extends warrior.mdb.TTable<Long, xbean.logic.NewbieGuide> {
		@Override
		public String getName() {
			return "newbieguide";
		}

		@Override
		protected xbean.logic.NewbieGuide newValue() {
			xbean.logic.NewbieGuide value = new xbean.logic.NewbieGuide();
			return value;
		}

		xbean.logic.NewbieGuide insert(Long key, xbean.logic.NewbieGuide value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.NewbieGuide insert(Long key, xbean.logic.NewbieGuide value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.NewbieGuide deepCopy(xbean.logic.NewbieGuide v){
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
		protected String marshalValue(xbean.logic.NewbieGuide value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.NewbieGuide unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.NewbieGuide.class);
		}

	};

	Newbieguide newbieguide = new Newbieguide();

	class Playercharge extends warrior.mdb.TTable<Long, xbean.logic.PlayerCharge> {
		@Override
		public String getName() {
			return "playercharge";
		}

		@Override
		protected xbean.logic.PlayerCharge newValue() {
			xbean.logic.PlayerCharge value = new xbean.logic.PlayerCharge();
			return value;
		}

		xbean.logic.PlayerCharge insert(Long key, xbean.logic.PlayerCharge value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerCharge insert(Long key, xbean.logic.PlayerCharge value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerCharge deepCopy(xbean.logic.PlayerCharge v){
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
		protected String marshalValue(xbean.logic.PlayerCharge value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerCharge unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerCharge.class);
		}

	};

	Playercharge playercharge = new Playercharge();

	class Playernewhandtarget extends warrior.mdb.TTable<Long, xbean.logic.PlayerNewHandTarget> {
		@Override
		public String getName() {
			return "playernewhandtarget";
		}

		@Override
		protected xbean.logic.PlayerNewHandTarget newValue() {
			xbean.logic.PlayerNewHandTarget value = new xbean.logic.PlayerNewHandTarget();
			return value;
		}

		xbean.logic.PlayerNewHandTarget insert(Long key, xbean.logic.PlayerNewHandTarget value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerNewHandTarget insert(Long key, xbean.logic.PlayerNewHandTarget value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerNewHandTarget deepCopy(xbean.logic.PlayerNewHandTarget v){
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
		protected String marshalValue(xbean.logic.PlayerNewHandTarget value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerNewHandTarget unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerNewHandTarget.class);
		}

	};

	Playernewhandtarget playernewhandtarget = new Playernewhandtarget();

	class Playerguard extends warrior.mdb.TTable<Long, xbean.logic.PlayerGuard> {
		@Override
		public String getName() {
			return "playerguard";
		}

		@Override
		protected xbean.logic.PlayerGuard newValue() {
			xbean.logic.PlayerGuard value = new xbean.logic.PlayerGuard();
			return value;
		}

		xbean.logic.PlayerGuard insert(Long key, xbean.logic.PlayerGuard value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerGuard insert(Long key, xbean.logic.PlayerGuard value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerGuard deepCopy(xbean.logic.PlayerGuard v){
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
		protected String marshalValue(xbean.logic.PlayerGuard value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerGuard unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerGuard.class);
		}

	};

	Playerguard playerguard = new Playerguard();

	class Playernewbie extends warrior.mdb.TTable<Long, xbean.logic.PlayerNewbie> {
		@Override
		public String getName() {
			return "playernewbie";
		}

		@Override
		protected xbean.logic.PlayerNewbie newValue() {
			xbean.logic.PlayerNewbie value = new xbean.logic.PlayerNewbie();
			return value;
		}

		xbean.logic.PlayerNewbie insert(Long key, xbean.logic.PlayerNewbie value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerNewbie insert(Long key, xbean.logic.PlayerNewbie value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerNewbie deepCopy(xbean.logic.PlayerNewbie v){
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
		protected String marshalValue(xbean.logic.PlayerNewbie value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerNewbie unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerNewbie.class);
		}

	};

	Playernewbie playernewbie = new Playernewbie();

	class Playerhonorfalloff extends warrior.mdb.TTable<Long, xbean.logic.PlayerHonorFalloff> {
		@Override
		public String getName() {
			return "playerhonorfalloff";
		}

		@Override
		protected xbean.logic.PlayerHonorFalloff newValue() {
			xbean.logic.PlayerHonorFalloff value = new xbean.logic.PlayerHonorFalloff();
			return value;
		}

		xbean.logic.PlayerHonorFalloff insert(Long key, xbean.logic.PlayerHonorFalloff value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerHonorFalloff insert(Long key, xbean.logic.PlayerHonorFalloff value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerHonorFalloff deepCopy(xbean.logic.PlayerHonorFalloff v){
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
		protected String marshalValue(xbean.logic.PlayerHonorFalloff value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerHonorFalloff unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerHonorFalloff.class);
		}

	};

	Playerhonorfalloff playerhonorfalloff = new Playerhonorfalloff();

	class Playeractweaponkill extends warrior.mdb.TTable<Long, xbean.logic.PlayerActWeaponKill> {
		@Override
		public String getName() {
			return "playeractweaponkill";
		}

		@Override
		protected xbean.logic.PlayerActWeaponKill newValue() {
			xbean.logic.PlayerActWeaponKill value = new xbean.logic.PlayerActWeaponKill();
			return value;
		}

		xbean.logic.PlayerActWeaponKill insert(Long key, xbean.logic.PlayerActWeaponKill value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerActWeaponKill insert(Long key, xbean.logic.PlayerActWeaponKill value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerActWeaponKill deepCopy(xbean.logic.PlayerActWeaponKill v){
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
		protected String marshalValue(xbean.logic.PlayerActWeaponKill value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerActWeaponKill unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerActWeaponKill.class);
		}

	};

	Playeractweaponkill playeractweaponkill = new Playeractweaponkill();

	class Playerfaction extends warrior.mdb.TTable<Long, xbean.logic.PlayerFaction> {
		@Override
		public String getName() {
			return "playerfaction";
		}

		@Override
		protected xbean.logic.PlayerFaction newValue() {
			xbean.logic.PlayerFaction value = new xbean.logic.PlayerFaction();
			return value;
		}

		xbean.logic.PlayerFaction insert(Long key, xbean.logic.PlayerFaction value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerFaction insert(Long key, xbean.logic.PlayerFaction value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerFaction deepCopy(xbean.logic.PlayerFaction v){
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
		protected String marshalValue(xbean.logic.PlayerFaction value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerFaction unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerFaction.class);
		}

	};

	Playerfaction playerfaction = new Playerfaction();

	class Playerskilled extends warrior.mdb.TTable<Long, xbean.logic.PlayerSkilledExpInfo> {
		@Override
		public String getName() {
			return "playerskilled";
		}

		@Override
		protected xbean.logic.PlayerSkilledExpInfo newValue() {
			xbean.logic.PlayerSkilledExpInfo value = new xbean.logic.PlayerSkilledExpInfo();
			return value;
		}

		xbean.logic.PlayerSkilledExpInfo insert(Long key, xbean.logic.PlayerSkilledExpInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerSkilledExpInfo insert(Long key, xbean.logic.PlayerSkilledExpInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerSkilledExpInfo deepCopy(xbean.logic.PlayerSkilledExpInfo v){
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
		protected String marshalValue(xbean.logic.PlayerSkilledExpInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerSkilledExpInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerSkilledExpInfo.class);
		}

	};

	Playerskilled playerskilled = new Playerskilled();

	class Playerchat extends warrior.mdb.TTable<Long, xbean.logic.PlayerChat> {
		@Override
		public String getName() {
			return "playerchat";
		}

		@Override
		protected xbean.logic.PlayerChat newValue() {
			xbean.logic.PlayerChat value = new xbean.logic.PlayerChat();
			return value;
		}

		xbean.logic.PlayerChat insert(Long key, xbean.logic.PlayerChat value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerChat insert(Long key, xbean.logic.PlayerChat value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerChat deepCopy(xbean.logic.PlayerChat v){
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
		protected String marshalValue(xbean.logic.PlayerChat value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerChat unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerChat.class);
		}

	};

	Playerchat playerchat = new Playerchat();

	class Playerpraise extends warrior.mdb.TTable<Long, xbean.logic.PlayerPraise> {
		@Override
		public String getName() {
			return "playerpraise";
		}

		@Override
		protected xbean.logic.PlayerPraise newValue() {
			xbean.logic.PlayerPraise value = new xbean.logic.PlayerPraise();
			return value;
		}

		xbean.logic.PlayerPraise insert(Long key, xbean.logic.PlayerPraise value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerPraise insert(Long key, xbean.logic.PlayerPraise value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerPraise deepCopy(xbean.logic.PlayerPraise v){
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
		protected String marshalValue(xbean.logic.PlayerPraise value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerPraise unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerPraise.class);
		}

	};

	Playerpraise playerpraise = new Playerpraise();

	class Playercollectlimit extends warrior.mdb.TTable<Long, xbean.logic.PlayerCollectLimit> {
		@Override
		public String getName() {
			return "playercollectlimit";
		}

		@Override
		protected xbean.logic.PlayerCollectLimit newValue() {
			xbean.logic.PlayerCollectLimit value = new xbean.logic.PlayerCollectLimit();
			return value;
		}

		xbean.logic.PlayerCollectLimit insert(Long key, xbean.logic.PlayerCollectLimit value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerCollectLimit insert(Long key, xbean.logic.PlayerCollectLimit value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerCollectLimit deepCopy(xbean.logic.PlayerCollectLimit v){
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
		protected String marshalValue(xbean.logic.PlayerCollectLimit value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerCollectLimit unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerCollectLimit.class);
		}

	};

	Playercollectlimit playercollectlimit = new Playercollectlimit();

	class Playerdungeonmirror extends warrior.mdb.TTable<Long, xbean.logic.PlayerDungeonMirror> {
		@Override
		public String getName() {
			return "playerdungeonmirror";
		}

		@Override
		protected xbean.logic.PlayerDungeonMirror newValue() {
			xbean.logic.PlayerDungeonMirror value = new xbean.logic.PlayerDungeonMirror();
			return value;
		}

		xbean.logic.PlayerDungeonMirror insert(Long key, xbean.logic.PlayerDungeonMirror value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerDungeonMirror insert(Long key, xbean.logic.PlayerDungeonMirror value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerDungeonMirror deepCopy(xbean.logic.PlayerDungeonMirror v){
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
		protected String marshalValue(xbean.logic.PlayerDungeonMirror value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerDungeonMirror unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerDungeonMirror.class);
		}

	};

	Playerdungeonmirror playerdungeonmirror = new Playerdungeonmirror();

	class Playertitle extends warrior.mdb.TTable<Long, xbean.logic.PlayerTitle> {
		@Override
		public String getName() {
			return "playertitle";
		}

		@Override
		protected xbean.logic.PlayerTitle newValue() {
			xbean.logic.PlayerTitle value = new xbean.logic.PlayerTitle();
			return value;
		}

		xbean.logic.PlayerTitle insert(Long key, xbean.logic.PlayerTitle value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerTitle insert(Long key, xbean.logic.PlayerTitle value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerTitle deepCopy(xbean.logic.PlayerTitle v){
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
		protected String marshalValue(xbean.logic.PlayerTitle value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerTitle unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerTitle.class);
		}

	};

	Playertitle playertitle = new Playertitle();

	class Playereatchicken extends warrior.mdb.TTable<Long, xbean.logic.PlayerEatChicken> {
		@Override
		public String getName() {
			return "playereatchicken";
		}

		@Override
		protected xbean.logic.PlayerEatChicken newValue() {
			xbean.logic.PlayerEatChicken value = new xbean.logic.PlayerEatChicken();
			return value;
		}

		xbean.logic.PlayerEatChicken insert(Long key, xbean.logic.PlayerEatChicken value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerEatChicken insert(Long key, xbean.logic.PlayerEatChicken value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerEatChicken deepCopy(xbean.logic.PlayerEatChicken v){
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
		protected String marshalValue(xbean.logic.PlayerEatChicken value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerEatChicken unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerEatChicken.class);
		}

	};

	Playereatchicken playereatchicken = new Playereatchicken();

	class Playerfightproperty extends warrior.mdb.TTable<Long, xbean.logic.PlayerFightProperty> {
		@Override
		public String getName() {
			return "playerfightproperty";
		}

		@Override
		protected xbean.logic.PlayerFightProperty newValue() {
			xbean.logic.PlayerFightProperty value = new xbean.logic.PlayerFightProperty();
			return value;
		}

		xbean.logic.PlayerFightProperty insert(Long key, xbean.logic.PlayerFightProperty value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerFightProperty insert(Long key, xbean.logic.PlayerFightProperty value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerFightProperty deepCopy(xbean.logic.PlayerFightProperty v){
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
		protected String marshalValue(xbean.logic.PlayerFightProperty value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerFightProperty unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerFightProperty.class);
		}

	};

	Playerfightproperty playerfightproperty = new Playerfightproperty();

	class Playerequipwarehouse extends warrior.mdb.TTable<Long, xbean.logic.PlayerEquipWarehouse> {
		@Override
		public String getName() {
			return "playerequipwarehouse";
		}

		@Override
		protected xbean.logic.PlayerEquipWarehouse newValue() {
			xbean.logic.PlayerEquipWarehouse value = new xbean.logic.PlayerEquipWarehouse();
			return value;
		}

		xbean.logic.PlayerEquipWarehouse insert(Long key, xbean.logic.PlayerEquipWarehouse value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerEquipWarehouse insert(Long key, xbean.logic.PlayerEquipWarehouse value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerEquipWarehouse deepCopy(xbean.logic.PlayerEquipWarehouse v){
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
		protected String marshalValue(xbean.logic.PlayerEquipWarehouse value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerEquipWarehouse unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerEquipWarehouse.class);
		}

	};

	Playerequipwarehouse playerequipwarehouse = new Playerequipwarehouse();

	class Systemsetting extends warrior.mdb.TTable<Long, xbean.logic.SystemSetting> {
		@Override
		public String getName() {
			return "systemsetting";
		}

		@Override
		protected xbean.logic.SystemSetting newValue() {
			xbean.logic.SystemSetting value = new xbean.logic.SystemSetting();
			return value;
		}

		xbean.logic.SystemSetting insert(Long key, xbean.logic.SystemSetting value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.SystemSetting insert(Long key, xbean.logic.SystemSetting value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.SystemSetting deepCopy(xbean.logic.SystemSetting v){
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
		protected String marshalValue(xbean.logic.SystemSetting value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.SystemSetting unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.SystemSetting.class);
		}

	};

	Systemsetting systemsetting = new Systemsetting();

	class Drawings extends warrior.mdb.TTable<Long, xbean.logic.PlayerDrawings> {
		@Override
		public String getName() {
			return "drawings";
		}

		@Override
		protected xbean.logic.PlayerDrawings newValue() {
			xbean.logic.PlayerDrawings value = new xbean.logic.PlayerDrawings();
			return value;
		}

		xbean.logic.PlayerDrawings insert(Long key, xbean.logic.PlayerDrawings value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerDrawings insert(Long key, xbean.logic.PlayerDrawings value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerDrawings deepCopy(xbean.logic.PlayerDrawings v){
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
		protected String marshalValue(xbean.logic.PlayerDrawings value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerDrawings unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerDrawings.class);
		}

	};

	Drawings drawings = new Drawings();

	class Playercollect extends warrior.mdb.TTable<Long, xbean.logic.PlayerCollect> {
		@Override
		public String getName() {
			return "playercollect";
		}

		@Override
		protected xbean.logic.PlayerCollect newValue() {
			xbean.logic.PlayerCollect value = new xbean.logic.PlayerCollect();
			return value;
		}

		xbean.logic.PlayerCollect insert(Long key, xbean.logic.PlayerCollect value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerCollect insert(Long key, xbean.logic.PlayerCollect value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerCollect deepCopy(xbean.logic.PlayerCollect v){
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
		protected String marshalValue(xbean.logic.PlayerCollect value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerCollect unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerCollect.class);
		}

	};

	Playercollect playercollect = new Playercollect();

	class Playermarkequippos extends warrior.mdb.TTable<Long, xbean.logic.PlayerMarkEquipPos> {
		@Override
		public String getName() {
			return "playermarkequippos";
		}

		@Override
		protected xbean.logic.PlayerMarkEquipPos newValue() {
			xbean.logic.PlayerMarkEquipPos value = new xbean.logic.PlayerMarkEquipPos();
			return value;
		}

		xbean.logic.PlayerMarkEquipPos insert(Long key, xbean.logic.PlayerMarkEquipPos value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerMarkEquipPos insert(Long key, xbean.logic.PlayerMarkEquipPos value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerMarkEquipPos deepCopy(xbean.logic.PlayerMarkEquipPos v){
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
		protected String marshalValue(xbean.logic.PlayerMarkEquipPos value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerMarkEquipPos unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerMarkEquipPos.class);
		}

	};

	Playermarkequippos playermarkequippos = new Playermarkequippos();

	class Playermapinfo extends warrior.mdb.TTable<Long, xbean.logic.PlayerMapInfo> {
		@Override
		public String getName() {
			return "playermapinfo";
		}

		@Override
		protected xbean.logic.PlayerMapInfo newValue() {
			xbean.logic.PlayerMapInfo value = new xbean.logic.PlayerMapInfo();
			return value;
		}

		xbean.logic.PlayerMapInfo insert(Long key, xbean.logic.PlayerMapInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerMapInfo insert(Long key, xbean.logic.PlayerMapInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerMapInfo deepCopy(xbean.logic.PlayerMapInfo v){
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
		protected String marshalValue(xbean.logic.PlayerMapInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerMapInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerMapInfo.class);
		}

	};

	Playermapinfo playermapinfo = new Playermapinfo();

	class Playerexploresave extends warrior.mdb.TTable<Long, xbean.logic.PlayerExploreSave> {
		@Override
		public String getName() {
			return "playerexploresave";
		}

		@Override
		protected xbean.logic.PlayerExploreSave newValue() {
			xbean.logic.PlayerExploreSave value = new xbean.logic.PlayerExploreSave();
			return value;
		}

		xbean.logic.PlayerExploreSave insert(Long key, xbean.logic.PlayerExploreSave value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerExploreSave insert(Long key, xbean.logic.PlayerExploreSave value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerExploreSave deepCopy(xbean.logic.PlayerExploreSave v){
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
		protected String marshalValue(xbean.logic.PlayerExploreSave value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerExploreSave unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerExploreSave.class);
		}

	};

	Playerexploresave playerexploresave = new Playerexploresave();

	class Playercrewdepot extends warrior.mdb.TTable<Long, xbean.logic.PlayerCrewDepot> {
		@Override
		public String getName() {
			return "playercrewdepot";
		}

		@Override
		protected xbean.logic.PlayerCrewDepot newValue() {
			xbean.logic.PlayerCrewDepot value = new xbean.logic.PlayerCrewDepot();
			return value;
		}

		xbean.logic.PlayerCrewDepot insert(Long key, xbean.logic.PlayerCrewDepot value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerCrewDepot insert(Long key, xbean.logic.PlayerCrewDepot value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerCrewDepot deepCopy(xbean.logic.PlayerCrewDepot v){
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
		protected String marshalValue(xbean.logic.PlayerCrewDepot value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerCrewDepot unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerCrewDepot.class);
		}

	};

	Playercrewdepot playercrewdepot = new Playercrewdepot();

	class Email extends warrior.mdb.TTable<Long, xbean.logic.PlayerEmails> {
		@Override
		public String getName() {
			return "email";
		}

		@Override
		protected xbean.logic.PlayerEmails newValue() {
			xbean.logic.PlayerEmails value = new xbean.logic.PlayerEmails();
			return value;
		}

		xbean.logic.PlayerEmails insert(Long key, xbean.logic.PlayerEmails value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerEmails insert(Long key, xbean.logic.PlayerEmails value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerEmails deepCopy(xbean.logic.PlayerEmails v){
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
		protected String marshalValue(xbean.logic.PlayerEmails value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerEmails unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerEmails.class);
		}

	};

	Email email = new Email();

	class Playermentor extends warrior.mdb.TTable<Long, xbean.logic.PlayerMentor> {
		@Override
		public String getName() {
			return "playermentor";
		}

		@Override
		protected xbean.logic.PlayerMentor newValue() {
			xbean.logic.PlayerMentor value = new xbean.logic.PlayerMentor();
			return value;
		}

		xbean.logic.PlayerMentor insert(Long key, xbean.logic.PlayerMentor value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerMentor insert(Long key, xbean.logic.PlayerMentor value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerMentor deepCopy(xbean.logic.PlayerMentor v){
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
		protected String marshalValue(xbean.logic.PlayerMentor value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerMentor unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerMentor.class);
		}

	};

	Playermentor playermentor = new Playermentor();

	class Playerwildrescollect extends warrior.mdb.TTable<Long, xbean.logic.PlayerWildResCollect> {
		@Override
		public String getName() {
			return "playerwildrescollect";
		}

		@Override
		protected xbean.logic.PlayerWildResCollect newValue() {
			xbean.logic.PlayerWildResCollect value = new xbean.logic.PlayerWildResCollect();
			return value;
		}

		xbean.logic.PlayerWildResCollect insert(Long key, xbean.logic.PlayerWildResCollect value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerWildResCollect insert(Long key, xbean.logic.PlayerWildResCollect value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerWildResCollect deepCopy(xbean.logic.PlayerWildResCollect v){
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
		protected String marshalValue(xbean.logic.PlayerWildResCollect value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerWildResCollect unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerWildResCollect.class);
		}

	};

	Playerwildrescollect playerwildrescollect = new Playerwildrescollect();

	class Playerupaward extends warrior.mdb.TTable<Long, xbean.logic.PlayerUpAward> {
		@Override
		public String getName() {
			return "playerupaward";
		}

		@Override
		protected xbean.logic.PlayerUpAward newValue() {
			xbean.logic.PlayerUpAward value = new xbean.logic.PlayerUpAward();
			return value;
		}

		xbean.logic.PlayerUpAward insert(Long key, xbean.logic.PlayerUpAward value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerUpAward insert(Long key, xbean.logic.PlayerUpAward value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerUpAward deepCopy(xbean.logic.PlayerUpAward v){
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
		protected String marshalValue(xbean.logic.PlayerUpAward value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerUpAward unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerUpAward.class);
		}

	};

	Playerupaward playerupaward = new Playerupaward();

	class Playerwarmakes extends warrior.mdb.TTable<Long, xbean.logic.PlayerWarMakes> {
		@Override
		public String getName() {
			return "playerwarmakes";
		}

		@Override
		protected xbean.logic.PlayerWarMakes newValue() {
			xbean.logic.PlayerWarMakes value = new xbean.logic.PlayerWarMakes();
			return value;
		}

		xbean.logic.PlayerWarMakes insert(Long key, xbean.logic.PlayerWarMakes value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerWarMakes insert(Long key, xbean.logic.PlayerWarMakes value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerWarMakes deepCopy(xbean.logic.PlayerWarMakes v){
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
		protected String marshalValue(xbean.logic.PlayerWarMakes value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerWarMakes unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerWarMakes.class);
		}

	};

	Playerwarmakes playerwarmakes = new Playerwarmakes();

	class Btplayerinfo extends warrior.mdb.TTable<Long, xbean.logic.BTPlayerInfo> {
		@Override
		public String getName() {
			return "btplayerinfo";
		}

		@Override
		protected xbean.logic.BTPlayerInfo newValue() {
			xbean.logic.BTPlayerInfo value = new xbean.logic.BTPlayerInfo();
			return value;
		}

		xbean.logic.BTPlayerInfo insert(Long key, xbean.logic.BTPlayerInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.BTPlayerInfo insert(Long key, xbean.logic.BTPlayerInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.BTPlayerInfo deepCopy(xbean.logic.BTPlayerInfo v){
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
		protected String marshalValue(xbean.logic.BTPlayerInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.BTPlayerInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.BTPlayerInfo.class);
		}

	};

	Btplayerinfo btplayerinfo = new Btplayerinfo();

	class Lbplayerinfo extends warrior.mdb.TTable<Long, xbean.logic.LBPlayerInfo> {
		@Override
		public String getName() {
			return "lbplayerinfo";
		}

		@Override
		protected xbean.logic.LBPlayerInfo newValue() {
			xbean.logic.LBPlayerInfo value = new xbean.logic.LBPlayerInfo();
			return value;
		}

		xbean.logic.LBPlayerInfo insert(Long key, xbean.logic.LBPlayerInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.LBPlayerInfo insert(Long key, xbean.logic.LBPlayerInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.LBPlayerInfo deepCopy(xbean.logic.LBPlayerInfo v){
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
		protected String marshalValue(xbean.logic.LBPlayerInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.LBPlayerInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.LBPlayerInfo.class);
		}

	};

	Lbplayerinfo lbplayerinfo = new Lbplayerinfo();

	class Citydepot extends warrior.mdb.TTable<Long, xbean.logic.CityDepot> {
		@Override
		public String getName() {
			return "citydepot";
		}

		@Override
		protected xbean.logic.CityDepot newValue() {
			xbean.logic.CityDepot value = new xbean.logic.CityDepot();
			return value;
		}

		xbean.logic.CityDepot insert(Long key, xbean.logic.CityDepot value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.CityDepot insert(Long key, xbean.logic.CityDepot value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.CityDepot deepCopy(xbean.logic.CityDepot v){
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
		protected String marshalValue(xbean.logic.CityDepot value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.CityDepot unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.CityDepot.class);
		}

	};

	Citydepot citydepot = new Citydepot();

	class Playerpvpbattle extends warrior.mdb.TTable<Long, xbean.logic.PlayerPvpBattle> {
		@Override
		public String getName() {
			return "playerpvpbattle";
		}

		@Override
		protected xbean.logic.PlayerPvpBattle newValue() {
			xbean.logic.PlayerPvpBattle value = new xbean.logic.PlayerPvpBattle();
			return value;
		}

		xbean.logic.PlayerPvpBattle insert(Long key, xbean.logic.PlayerPvpBattle value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerPvpBattle insert(Long key, xbean.logic.PlayerPvpBattle value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerPvpBattle deepCopy(xbean.logic.PlayerPvpBattle v){
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
		protected String marshalValue(xbean.logic.PlayerPvpBattle value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerPvpBattle unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerPvpBattle.class);
		}

	};

	Playerpvpbattle playerpvpbattle = new Playerpvpbattle();

	class Playerwarfare extends warrior.mdb.TTable<Long, xbean.logic.PlayerWarfare> {
		@Override
		public String getName() {
			return "playerwarfare";
		}

		@Override
		protected xbean.logic.PlayerWarfare newValue() {
			xbean.logic.PlayerWarfare value = new xbean.logic.PlayerWarfare();
			return value;
		}

		xbean.logic.PlayerWarfare insert(Long key, xbean.logic.PlayerWarfare value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.logic.PlayerWarfare insert(Long key, xbean.logic.PlayerWarfare value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.logic.PlayerWarfare deepCopy(xbean.logic.PlayerWarfare v){
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
		protected String marshalValue(xbean.logic.PlayerWarfare value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.logic.PlayerWarfare unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.logic.PlayerWarfare.class);
		}

	};

	Playerwarfare playerwarfare = new Playerwarfare();


}
