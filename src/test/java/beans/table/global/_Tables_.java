package beans.table.global;

import warrior.mdb.util.FastJson;

final class _Tables_ {
	volatile static _Tables_ instance;

	private _Tables_ () {
		instance = this;
	}

	class Gsplayerinfo extends warrior.mdb.TTable<Long, xbean.global.GsPlayerInfo> {
		@Override
		public String getName() {
			return "gsplayerinfo";
		}

		@Override
		protected xbean.global.GsPlayerInfo newValue() {
			xbean.global.GsPlayerInfo value = new xbean.global.GsPlayerInfo();
			return value;
		}

		xbean.global.GsPlayerInfo insert(Long key, xbean.global.GsPlayerInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.GsPlayerInfo insert(Long key, xbean.global.GsPlayerInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.GsPlayerInfo deepCopy(xbean.global.GsPlayerInfo v){
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
		protected String marshalValue(xbean.global.GsPlayerInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.global.GsPlayerInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.GsPlayerInfo.class);
		}

	};

	Gsplayerinfo gsplayerinfo = new Gsplayerinfo();

	class Gsfactioninfo extends warrior.mdb.TTable<Integer, xbean.global.GsFactionInfo> {
		@Override
		public String getName() {
			return "gsfactioninfo";
		}

		@Override
		protected xbean.global.GsFactionInfo newValue() {
			xbean.global.GsFactionInfo value = new xbean.global.GsFactionInfo();
			return value;
		}

		xbean.global.GsFactionInfo insert(Integer key, xbean.global.GsFactionInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.GsFactionInfo insert(Integer key, xbean.global.GsFactionInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.GsFactionInfo deepCopy(xbean.global.GsFactionInfo v){
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
		protected String marshalValue(xbean.global.GsFactionInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.global.GsFactionInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.GsFactionInfo.class);
		}

	};

	Gsfactioninfo gsfactioninfo = new Gsfactioninfo();

	class Gsrankinfo extends warrior.mdb.TTable<Long, xbean.global.RankInfo> {
		@Override
		public String getName() {
			return "gsrankinfo";
		}

		@Override
		protected xbean.global.RankInfo newValue() {
			xbean.global.RankInfo value = new xbean.global.RankInfo();
			return value;
		}

		xbean.global.RankInfo insert(Long key, xbean.global.RankInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.RankInfo insert(Long key, xbean.global.RankInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.RankInfo deepCopy(xbean.global.RankInfo v){
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
		protected String marshalValue(xbean.global.RankInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.global.RankInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.RankInfo.class);
		}

	};

	Gsrankinfo gsrankinfo = new Gsrankinfo();

	class Crossgenswar extends warrior.mdb.TTable<Long, xbean.global.CrossGensWar> {
		@Override
		public String getName() {
			return "crossgenswar";
		}

		@Override
		protected xbean.global.CrossGensWar newValue() {
			xbean.global.CrossGensWar value = new xbean.global.CrossGensWar();
			return value;
		}

		xbean.global.CrossGensWar insert(Long key, xbean.global.CrossGensWar value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.CrossGensWar insert(Long key, xbean.global.CrossGensWar value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.CrossGensWar deepCopy(xbean.global.CrossGensWar v){
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
		protected String marshalValue(xbean.global.CrossGensWar value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.global.CrossGensWar unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.CrossGensWar.class);
		}

	};

	Crossgenswar crossgenswar = new Crossgenswar();

	class Crossfactionwarmapinfo extends warrior.mdb.TTable<Integer, xbean.global.CrossFactionWarMapInfo> {
		@Override
		public String getName() {
			return "crossfactionwarmapinfo";
		}

		@Override
		protected xbean.global.CrossFactionWarMapInfo newValue() {
			xbean.global.CrossFactionWarMapInfo value = new xbean.global.CrossFactionWarMapInfo();
			return value;
		}

		xbean.global.CrossFactionWarMapInfo insert(Integer key, xbean.global.CrossFactionWarMapInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.CrossFactionWarMapInfo insert(Integer key, xbean.global.CrossFactionWarMapInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.CrossFactionWarMapInfo deepCopy(xbean.global.CrossFactionWarMapInfo v){
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
		protected String marshalValue(xbean.global.CrossFactionWarMapInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.global.CrossFactionWarMapInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.CrossFactionWarMapInfo.class);
		}

	};

	Crossfactionwarmapinfo crossfactionwarmapinfo = new Crossfactionwarmapinfo();

	class Gsactivitymap extends warrior.mdb.TTable<Long, xbean.global.GSActivityMap> {
		@Override
		public String getName() {
			return "gsactivitymap";
		}

		@Override
		protected xbean.global.GSActivityMap newValue() {
			xbean.global.GSActivityMap value = new xbean.global.GSActivityMap();
			return value;
		}

		xbean.global.GSActivityMap insert(Long key, xbean.global.GSActivityMap value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.GSActivityMap insert(Long key, xbean.global.GSActivityMap value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.GSActivityMap deepCopy(xbean.global.GSActivityMap v){
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
		protected String marshalValue(xbean.global.GSActivityMap value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.global.GSActivityMap unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.GSActivityMap.class);
		}

	};

	Gsactivitymap gsactivitymap = new Gsactivitymap();

	class Crossservergenswildinfo extends warrior.mdb.TTable<Integer, xbean.global.CrossServerGensWildInfo> {
		@Override
		public String getName() {
			return "crossservergenswildinfo";
		}

		@Override
		protected xbean.global.CrossServerGensWildInfo newValue() {
			xbean.global.CrossServerGensWildInfo value = new xbean.global.CrossServerGensWildInfo();
			return value;
		}

		xbean.global.CrossServerGensWildInfo insert(Integer key, xbean.global.CrossServerGensWildInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.CrossServerGensWildInfo insert(Integer key, xbean.global.CrossServerGensWildInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.CrossServerGensWildInfo deepCopy(xbean.global.CrossServerGensWildInfo v){
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
		protected String marshalValue(xbean.global.CrossServerGensWildInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.global.CrossServerGensWildInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.CrossServerGensWildInfo.class);
		}

	};

	Crossservergenswildinfo crossservergenswildinfo = new Crossservergenswildinfo();

	class Playerreportinfo extends warrior.mdb.TTable<Long, xbean.global.PlayerReportInfo> {
		@Override
		public String getName() {
			return "playerreportinfo";
		}

		@Override
		protected xbean.global.PlayerReportInfo newValue() {
			xbean.global.PlayerReportInfo value = new xbean.global.PlayerReportInfo();
			return value;
		}

		xbean.global.PlayerReportInfo insert(Long key, xbean.global.PlayerReportInfo value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.PlayerReportInfo insert(Long key, xbean.global.PlayerReportInfo value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.PlayerReportInfo deepCopy(xbean.global.PlayerReportInfo v){
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
		protected String marshalValue(xbean.global.PlayerReportInfo value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Long unmarshalKey(String os) {
			return FastJson.parseObject(os, Long.class);
		}

		@Override
		protected xbean.global.PlayerReportInfo unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.PlayerReportInfo.class);
		}

	};

	Playerreportinfo playerreportinfo = new Playerreportinfo();

	class Crossservergenswarmap extends warrior.mdb.TTable<Integer, xbean.global.CrossServerGensWarMap> {
		@Override
		public String getName() {
			return "crossservergenswarmap";
		}

		@Override
		protected xbean.global.CrossServerGensWarMap newValue() {
			xbean.global.CrossServerGensWarMap value = new xbean.global.CrossServerGensWarMap();
			return value;
		}

		xbean.global.CrossServerGensWarMap insert(Integer key, xbean.global.CrossServerGensWarMap value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.CrossServerGensWarMap insert(Integer key, xbean.global.CrossServerGensWarMap value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.CrossServerGensWarMap deepCopy(xbean.global.CrossServerGensWarMap v){
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
		protected String marshalValue(xbean.global.CrossServerGensWarMap value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.global.CrossServerGensWarMap unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.CrossServerGensWarMap.class);
		}

	};

	Crossservergenswarmap crossservergenswarmap = new Crossservergenswarmap();

	class Cfwtotallog extends warrior.mdb.TTable<Integer, xbean.global.CFWTotalLog> {
		@Override
		public String getName() {
			return "cfwtotallog";
		}

		@Override
		protected xbean.global.CFWTotalLog newValue() {
			xbean.global.CFWTotalLog value = new xbean.global.CFWTotalLog();
			return value;
		}

		xbean.global.CFWTotalLog insert(Integer key, xbean.global.CFWTotalLog value, boolean immediately) {
			return add(key, value, immediately) ? value : null;
		}

		xbean.global.CFWTotalLog insert(Integer key, xbean.global.CFWTotalLog value) {
			return add(key, value) ? value : null;
		}

		@Override
		protected xbean.global.CFWTotalLog deepCopy(xbean.global.CFWTotalLog v){
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
		protected String marshalValue(xbean.global.CFWTotalLog value) {
			return FastJson.toJSONString(value);
		}

		@Override
		protected Integer unmarshalKey(String os) {
			return FastJson.parseObject(os, Integer.class);
		}

		@Override
		protected xbean.global.CFWTotalLog unmarshalValue(String os) {
			return FastJson.parseObject(os, xbean.global.CFWTotalLog.class);
		}

	};

	Cfwtotallog cfwtotallog = new Cfwtotallog();


}
