package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class GensReportInfo extends warrior.mdb.XBean {
    private xbean.lobby.LBGensBaseInfo occupyGens;  // 占领的公会
    private java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore> scoreMap;  // 战报积分 k->区域id v:分数数据
    private java.util.concurrent.CopyOnWriteArrayList<Integer> defenseList;  // 防守成功列表
    private java.util.concurrent.CopyOnWriteArrayList<Integer> attackList;  // 进攻成功列表
    private int rank;  // 排名
    private int totalScore;  // 总积分

    GensReportInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.occupyGens = new LBGensBaseInfo(this);
		this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore>();
		this.defenseList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		this.attackList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public GensReportInfo() {
		this((warrior.mdb.XBean)null);
	}

	public GensReportInfo(GensReportInfo _o_) {
		this(_o_, null);
	}

	GensReportInfo(GensReportInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.occupyGens = new LBGensBaseInfo(_o_.occupyGens, this);
		this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore>();
		_o_.scoreMap.forEach((_k_, _v_) -> this.scoreMap.put(_k_, new AreaReportScore(_v_)));
		this.defenseList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.defenseList.forEach(_v_ -> this.defenseList.add(_v_));
		this.attackList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.attackList.forEach(_v_ -> this.attackList.add(_v_));
		this.rank = _o_.rank;
		this.totalScore = _o_.totalScore;
	}

	public void copyFrom(GensReportInfo _o_) {
        this.occupyGens = new LBGensBaseInfo(_o_.occupyGens, this);
        this.scoreMap = new java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore>();
		_o_.scoreMap.forEach((_k_, _v_) -> this.scoreMap.put(_k_, new AreaReportScore(_v_)));
        this.defenseList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.defenseList.forEach(_v_ -> this.defenseList.add(_v_));
        this.attackList = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.attackList.forEach(_v_ -> this.attackList.add(_v_));
        this.rank = _o_.rank;
        this.totalScore = _o_.totalScore;
	}


	@JSONField(name = "1")
	public xbean.lobby.LBGensBaseInfo getOccupyGens(){
		return this.occupyGens;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore> getScoreMap(){
		return this.scoreMap;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getDefenseList(){
		return this.defenseList;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getAttackList(){
		return this.attackList;
	}

	@JSONField(name = "5")
	public int getRank(){
		return this.rank;
	}

	@JSONField(name = "6")
	public int getTotalScore(){
		return this.totalScore;
	}

	@JSONField(name = "1")
	public void setOccupyGens(xbean.lobby.LBGensBaseInfo _v_){
		this.occupyGens = _v_;
	}

	@JSONField(name = "2")
	public void setScoreMap(java.util.concurrent.ConcurrentHashMap<Integer, AreaReportScore> _v_){
		this.scoreMap = _v_;
	}

	@JSONField(name = "3")
	public void setDefenseList(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.defenseList = _v_;
	}

	@JSONField(name = "4")
	public void setAttackList(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.attackList = _v_;
	}

	@JSONField(name = "5")
	public void setRank(int _v_){
		this.rank = _v_;
	}

	@JSONField(name = "6")
	public void setTotalScore(int _v_){
		this.totalScore = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GensReportInfo) {
			GensReportInfo _o_ = (GensReportInfo)_o1_;
			if (!this.occupyGens.equals(_o_.occupyGens)) return false;
			if (!this.scoreMap.equals(_o_.scoreMap)) return false;
			if (!this.defenseList.equals(_o_.defenseList)) return false;
			if (!this.attackList.equals(_o_.attackList)) return false;
			if (this.rank != _o_.rank) return false;
			if (this.totalScore != _o_.totalScore) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.occupyGens.hashCode();
		_h_ += _h_ * 31 + this.scoreMap.hashCode();
		_h_ += _h_ * 31 + this.defenseList.hashCode();
		_h_ += _h_ * 31 + this.attackList.hashCode();
		_h_ += _h_ * 31 + (int)(this.rank);
		_h_ += _h_ * 31 + (int)(this.totalScore);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(occupyGens).append(",");
		_sb_.append(scoreMap).append(",");
		_sb_.append(defenseList).append(",");
		_sb_.append(attackList).append(",");
		_sb_.append(rank).append(",");
		_sb_.append(totalScore).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
