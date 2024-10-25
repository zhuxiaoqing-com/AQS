package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class AreaReportScore extends warrior.mdb.XBean {
    private xbean.lobby.ReportScore score;  // score 分数
    private int areaId;  // 区域id
    private int rank;  // rank

    AreaReportScore(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.score = new ReportScore(this);
	}

	public AreaReportScore() {
		this((warrior.mdb.XBean)null);
	}

	public AreaReportScore(AreaReportScore _o_) {
		this(_o_, null);
	}

	AreaReportScore(AreaReportScore _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.score = new ReportScore(_o_.score, this);
		this.areaId = _o_.areaId;
		this.rank = _o_.rank;
	}

	public void copyFrom(AreaReportScore _o_) {
        this.score = new ReportScore(_o_.score, this);
        this.areaId = _o_.areaId;
        this.rank = _o_.rank;
	}


	@JSONField(name = "1")
	public xbean.lobby.ReportScore getScore(){
		return this.score;
	}

	@JSONField(name = "2")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "3")
	public int getRank(){
		return this.rank;
	}

	@JSONField(name = "1")
	public void setScore(xbean.lobby.ReportScore _v_){
		this.score = _v_;
	}

	@JSONField(name = "2")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "3")
	public void setRank(int _v_){
		this.rank = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AreaReportScore) {
			AreaReportScore _o_ = (AreaReportScore)_o1_;
			if (!this.score.equals(_o_.score)) return false;
			if (this.areaId != _o_.areaId) return false;
			if (this.rank != _o_.rank) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.score.hashCode();
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + (int)(this.rank);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(score).append(",");
		_sb_.append(areaId).append(",");
		_sb_.append(rank).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
