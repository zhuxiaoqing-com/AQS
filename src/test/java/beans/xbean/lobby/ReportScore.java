package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ReportScore extends warrior.mdb.XBean {
    private int score;  // score 分数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dataMap;  // 计数数据
    private java.util.concurrent.ConcurrentHashMap<Integer, Int2IntMap> scoreExtraMap;  // 积分额外数据

    ReportScore(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.scoreExtraMap = new java.util.concurrent.ConcurrentHashMap<Integer, Int2IntMap>();
	}

	public ReportScore() {
		this((warrior.mdb.XBean)null);
	}

	public ReportScore(ReportScore _o_) {
		this(_o_, null);
	}

	ReportScore(ReportScore _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.score = _o_.score;
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
		this.scoreExtraMap = new java.util.concurrent.ConcurrentHashMap<Integer, Int2IntMap>();
		_o_.scoreExtraMap.forEach((_k_, _v_) -> this.scoreExtraMap.put(_k_, new Int2IntMap(_v_)));
	}

	public void copyFrom(ReportScore _o_) {
        this.score = _o_.score;
        this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
        this.scoreExtraMap = new java.util.concurrent.ConcurrentHashMap<Integer, Int2IntMap>();
		_o_.scoreExtraMap.forEach((_k_, _v_) -> this.scoreExtraMap.put(_k_, new Int2IntMap(_v_)));
	}


	@JSONField(name = "1")
	public int getScore(){
		return this.score;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDataMap(){
		return this.dataMap;
	}

	@JSONField(name = "9")
	public java.util.concurrent.ConcurrentHashMap<Integer, Int2IntMap> getScoreExtraMap(){
		return this.scoreExtraMap;
	}

	@JSONField(name = "1")
	public void setScore(int _v_){
		this.score = _v_;
	}

	@JSONField(name = "2")
	public void setDataMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dataMap = _v_;
	}

	@JSONField(name = "9")
	public void setScoreExtraMap(java.util.concurrent.ConcurrentHashMap<Integer, Int2IntMap> _v_){
		this.scoreExtraMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ReportScore) {
			ReportScore _o_ = (ReportScore)_o1_;
			if (this.score != _o_.score) return false;
			if (!this.dataMap.equals(_o_.dataMap)) return false;
			if (!this.scoreExtraMap.equals(_o_.scoreExtraMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.score);
		_h_ += _h_ * 31 + this.dataMap.hashCode();
		_h_ += _h_ * 31 + this.scoreExtraMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(score).append(",");
		_sb_.append(dataMap).append(",");
		_sb_.append(scoreExtraMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
